package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.exception.ValidationException;
import com.es.segurosinseguros.mapper.Mapper;
import com.es.segurosinseguros.model.Seguro;
import com.es.segurosinseguros.repository.SeguroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SeguroService {
    @Autowired
    private SeguroRepository seguroRepository;

    public SeguroDTO getById(String id){

        long idL ;
        try {
            idL = Long.parseLong(id);// lanzar excepcion generica o NumberFormat
        }catch (NumberFormatException e){
            throw new BadRequestException("No se ha formateado bien el ID: "+id);
        }

        Seguro s = seguroRepository.findById(idL).orElse(null);

        if (s != null){
            return Mapper.getDTOfromSeguro(s);
        }else {
            throw new NotFoundException("No se ha encontrado el seguro con ese ID");
        }
    }


    public List<SeguroDTO> getSeguros(){
        List<Seguro> listaSeguros = seguroRepository.findAll();
        List<SeguroDTO> listaDTO = new ArrayList<>();

        for(Seguro s : listaSeguros){
            SeguroDTO se = Mapper.getDTOfromSeguro(s);
            listaDTO.add(se);
        }

        return listaDTO;
    }


    public SeguroDTO insert(SeguroDTO seguroDTO){
        // validaciones
        if (!Mapper.comprobarNif(seguroDTO.getNif()))
            throw new ValidationException("El campo NIF no tiene un formato válido.");

        if (seguroDTO.getNombre().isEmpty() || seguroDTO.getApe1().isEmpty() || seguroDTO.getNombre().isBlank() || seguroDTO.getApe1().isBlank() )
            throw new ValidationException("El campo {nombre/ape1} no puede estar vacío.");

        if(seguroDTO.getEdad()<18 )
            throw new ValidationException("No es posible ser menor de edad para hacer un seguro.");

        if(!seguroDTO.isCasado() && seguroDTO.getNumHijos() != 0)
            throw new ValidationException("Un seguro no puede registrar hijos si no está casado.");

        if(seguroDTO.getSexo().equals("hombre") || seguroDTO.isEmbarazada())
            throw new ValidationException("El campo embarazada no puede ser true si el asegurado es hombre.");

        Seguro s = Mapper.getSeguroFromDTO(seguroDTO);

        seguroRepository.save(s);

        return seguroDTO;
    }


    public SeguroDTO edit(String id, SeguroDTO seguroDTO){
        if (!Mapper.comprobarNif(seguroDTO.getNif()))
            throw new ValidationException("El campo NIF no tiene un formato válido.");

        if (seguroDTO.getNombre().isEmpty() || seguroDTO.getApe1().isEmpty() || seguroDTO.getNombre().isBlank() || seguroDTO.getApe1().isBlank() )
            throw new ValidationException("El campo {nombre/ape1} no puede estar vacío.");

        if(seguroDTO.getEdad()<18 )
            throw new ValidationException("No es posible ser menor de edad para hacer un seguro.");

        if(!seguroDTO.isCasado() && seguroDTO.getNumHijos() != 0)
            throw new ValidationException("Un seguro no puede registrar hijos si no está casado.");

        if(seguroDTO.getSexo().equals("hombre") || seguroDTO.isEmbarazada())
            throw new ValidationException("El campo embarazada no puede ser true si el asegurado es hombre.");

        Seguro s = seguroRepository.findById(Long.parseLong(id)).orElse(null);
        if (s == null) throw new NotFoundException("El seguro no ha sido encontrado");

        s = Mapper.editSeguroFromDTO(seguroDTO,s);

        seguroRepository.save(s);

        return null;
    }

}
