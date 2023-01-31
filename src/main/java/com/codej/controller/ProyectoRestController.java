package com.codej.controller;

import com.codej.model.Image;
import com.codej.model.Proyecto;
import com.codej.services.IProyectoService;
import com.codej.services.IUploadService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProyectoRestController {

    private final IProyectoService proyectoService;
    private  final IUploadService uploadService;


    @GetMapping("/proyecto")
    public List<Proyecto> get(){
       return proyectoService.findAll();
    }

    @GetMapping("/proyecto/{id}")
    public Proyecto getPorId(@PathVariable Integer id){
       return proyectoService.findById(id);
    }

    @PostMapping("/proyecto")
    public ResponseEntity<?> create(@RequestBody Proyecto proyecto){
        Map<String, Object> response = new HashMap<>();
        Proyecto proyectoNew = null;
        try {
            proyectoNew = proyectoService.save(proyecto);
        }catch (Exception e){
            response.put("mensaje", "Error al crear el proyecto");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.put("mensaje", "El proyecto ha sido creado con éxito");
        response.put("proyecto", proyectoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/proyecto/{id}")
    public ResponseEntity<?> update(@RequestBody Proyecto proyecto, @PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Proyecto proyectoActual = proyectoService.findById(id);
        Proyecto proyectoUpdated = null;
        if (proyectoActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el proyecto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return ResponseEntity.badRequest().body(response);
        }
        try {
            proyectoActual.setNombre(proyecto.getNombre());
            proyectoActual.setDescripcion(proyecto.getDescripcion());
            proyectoActual.setFechaInicio(proyecto.getFechaInicio());
            proyectoActual.setFechaFin(proyecto.getFechaFin());
            proyectoActual.setEstado(proyecto.getEstado());
            proyectoActual.setFoto(proyecto.getFoto());


            proyectoUpdated = proyectoService.save(proyectoActual);
        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el proyecto");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.put("mensaje", "El proyecto ha sido actualizado con éxito");
        response.put("proyecto", proyectoUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


    @DeleteMapping("/proyecto/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            proyectoService.delete(id);
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar el proyecto");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        response.put("mensaje", "El proyecto ha sido eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("upload/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        Resource recurso= null;
        try{
            recurso= new UrlResource(rutaArchivo.toUri());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        if (!recurso.exists() && !recurso.isReadable()){
            throw new RuntimeException("Error: no se puede cargar la imagen: "+ nombreFoto);
        }
        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+ "\"");
        return  new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

    @PostMapping("/proyecto/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile archivo, @RequestParam("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        Proyecto proyecto = proyectoService.findById(id);
        if(!archivo.isEmpty()){
            String nombreArchivo= null;
            try {
                nombreArchivo= uploadService.copiar(archivo);
            }catch (IOException e){
                response.put("mensaje", "Error al subir la imagen del producto ");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnt= proyecto.getFoto();
            uploadService.eliminar(nombreFotoAnt);
            proyecto.setFoto(nombreArchivo);
            proyectoService.save(proyecto);
            response.put("proyecto", proyecto);
            response.put("mensaje", "Has subido corectamente la imagen"+nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("proyecto/galeria")
    public ResponseEntity<?> uploadGaleria(@RequestParam("file")MultipartFile archivo,
                                           @RequestParam("galeria")String galeria ){
        Map<String,Object> response= new HashMap<>();
        Gson gson = new Gson();
        Image image= gson.fromJson(galeria, Image.class);
        if(!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del proyecto");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            System.out.println(image.getId());
            System.out.println(image.getNombre());
            System.out.println(image.getUrl());
            System.out.println(image.getProyecto());

            image.setUrl(nombreArchivo);
            image.setNombre(image.getNombre());
            image.setProyecto(image.getProyecto());

            proyectoService.guardar(image);
            response.put("image", image);
            response.put("mensaje","Has subido correctamente la imagen"+nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);

    }


}
