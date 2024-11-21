package com.es.segurosinseguros.service;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.exception.BadRequestException;
import com.es.segurosinseguros.exception.NotFoundException;
import com.es.segurosinseguros.mapper.Mapper;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.repository.AsistenciaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaService {
    @Autowired
    private AsistenciaMedicaRepository asistenciaMedicaRepository;

    public AsistenciaMedicaDTO getById(
            @PathVariable String id
    ){
        long idL ;
        try {
            idL = Long.parseLong(id);// lanzar excepcion generica o NumberFormat
        }catch (NumberFormatException e){
            throw new BadRequestException("No se ha formateado bien el ID: "+id);
        }

        AsistenciaMedica asistenciaMedica = asistenciaMedicaRepository.findById(idL).orElse(null);

        if (asistenciaMedica != null){
            return Mapper.getDTOFromAsistencia(asistenciaMedica);
        }else {
            throw new NotFoundException("No se ha encontrado la asistencia médica con ese ID");
        }
    }

    public List<AsistenciaMedicaDTO> getAll(){
        List<AsistenciaMedica> asistenciaMedicas = asistenciaMedicaRepository.findAll();
        List<AsistenciaMedicaDTO> asistenciaMedicaDTOS = new ArrayList<>();

        for(AsistenciaMedica a : asistenciaMedicas){
            asistenciaMedicaDTOS.add(Mapper.getDTOFromAsistencia(a));
        }

        return asistenciaMedicaDTOS;
    }


}
