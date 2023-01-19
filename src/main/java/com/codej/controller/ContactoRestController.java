package com.codej.controller;

import com.codej.model.Contacto;
import com.codej.services.IContactoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ContactoRestController {

    private final IContactoService contactoService;

    @GetMapping("/contacto")
    public List<Contacto> findAll(){
        return contactoService.findAll();
    }
    @PostMapping("/contacto")
    public Contacto save(@RequestBody Contacto contacto){
        return contactoService.save(contacto);
    }
    @GetMapping("/contacto/{id}")
    public Contacto getId(@PathVariable Integer id){
        return  contactoService.findById(id);
    }
    @PutMapping("/contacto/{id}")
    public  Contacto update(@PathVariable Integer id, @RequestBody Contacto contacto){
        Contacto contactoActual = contactoService.findById(id);
        contactoActual.setNombre(contacto.getNombre());
        contactoActual.setAsunto(contacto.getAsunto());
        contactoActual.setEmail(contacto.getEmail());
        contactoActual.setTelefono(contacto.getTelefono());
        contactoActual.setMensaje(contacto.getMensaje());
        return contactoService.save(contactoActual);
    }
    @DeleteMapping("/contacto/{id}")
    public void delete(@PathVariable Integer id){
        contactoService.delete(id);
    }


}
