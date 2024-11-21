package com.es.segurosinseguros.controller;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;

import com.es.segurosinseguros.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seguros")
public class SeguroController {
    // autowired
    @Autowired
    private SeguroService seguroService;


    @GetMapping("/{id}")
    public ResponseEntity<SeguroDTO> getById(
            @PathVariable String id
    ){
        // compruebo que el id no es null

        if(id == null){
            //Lanzo excepcion propia
            /*
                a) que codigo de estado devolverias -> BAD_REQUEST (400)
                b) Que informacion dariais al cliente -> un mensaje: "id no valido" -> La URI : localhost:8000/seguros/x
                c) Nombre a nuestra excepcion -> BadRequestException
             */
            throw new BadRequestException("id no válido");
        }
        SeguroDTO sDTO = seguroService.getById(id);

        return new ResponseEntity<>(sDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SeguroDTO>> getAll(){
        return new ResponseEntity<List<SeguroDTO>>(seguroService.getSeguros(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SeguroDTO> insertSeguro(
            @RequestBody SeguroDTO seguroDTO
    ){
        seguroService.insert(seguroDTO);
        return new ResponseEntity<>(seguroDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguroDTO> editSeguro(
            @PathVariable String id,@RequestBody SeguroDTO seguroDTO
    ){
        if(id == null) throw new BadRequestException("id no válido");
        if(seguroDTO == null) throw new BadRequestException("introduce un seguro para editar");

        SeguroDTO seguroDTO1 = seguroService.edit(id,seguroDTO);

        return new ResponseEntity<>(seguroDTO1,HttpStatus.OK);

    }

}
