
package com.ejercicios.sesion10_11_12.controller;

import com.ejercicios.sesion10_11_12.entities.Laptop;
import com.ejercicios.sesion10_11_12.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LaptopController {
    
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);
    // atributos
    private LaptopRepository laptopRepository;
    // constructor
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }
    
    /**
     * Buscar todas las Laptop (lista de Laptops)
     * http://localhost:8080/api/laptops
     * @return
     */
    @GetMapping("/api/laptops")
    @ApiOperation("Listar todas las Laptops de la base de datos")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }
    
    /**
     * Buscar una sola laptop en base de datos segun su ID
     *  http://localhost:8080/api/laptops/1
     *  http://localhost:8080/api/laptops/2
     * Request
     * Response
     * @param id
     * @return 
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una laptop por id")
    public ResponseEntity <Laptop> findOneById(@ApiParam("Clave primaria id tipo Long") @PathVariable Long id){
        
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
         
        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();// devuelve 404 Not Found    
    }
    
    /**
     * Crear una nueva laptop en base de datos
     * @param laptop
     * @return 
     */
    @PostMapping("/api/laptops")
    @ApiOperation("Crear una laptop nueva")
    public ResponseEntity<Laptop>create(@RequestBody Laptop laptop,@RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        
        if (laptop.getId() != null){
           log.warn("Logger: tratando de crear (create) una ID de laptop inexistente");
           System.out.println("Sout: trying to create a laptop whith id");
           return ResponseEntity.badRequest().build();
       }
       
       Laptop result = laptopRepository.save(laptop);
       return ResponseEntity.ok(result);
    }
    
     /**
     * Actualizar una laptop existente bd
     * @param laptops
     * @return 
     */
    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar una laptop por id")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("Logger: tratando de actualizar (update) una id de laptop inexistente");
            return ResponseEntity.badRequest().build(); // error 400
        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Logger: tratando de actualizar (update) una id de laptop inexistente");
            return ResponseEntity.notFound().build(); // error 404
        }
        // proceso de actualizacion
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);    
    }
    
    @DeleteMapping ("/api/laptops/{id}")
    @ApiOperation("Borrar una laptop por id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        
         if (!laptopRepository.existsById(id)){
            log.warn("Logger: tratando de eliminar (delete) una laptop por id inexistente");
            return ResponseEntity.notFound().build(); // error 404
        }
        
        laptopRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
    
    // Borrar todas las laptops
    @ApiIgnore // ignorar este metodo para que no aparezca en la documentacion de la api swagger
    @DeleteMapping ("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request: Ejecutando Borrar todas las laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
   
}
    
    

