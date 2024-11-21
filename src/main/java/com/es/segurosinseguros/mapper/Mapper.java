package com.es.segurosinseguros.mapper;

import com.es.segurosinseguros.dto.AsistenciaMedicaDTO;
import com.es.segurosinseguros.dto.SeguroDTO;
import com.es.segurosinseguros.model.AsistenciaMedica;
import com.es.segurosinseguros.model.Seguro;

import java.util.regex.Pattern;

public class Mapper {

    public static SeguroDTO getDTOfromSeguro(Seguro s) {
        SeguroDTO sDTO = new SeguroDTO();
        sDTO.setNif(s.getNif());
        sDTO.setNombre(s.getNombre());
        sDTO.setApe1(s.getApe1());
        sDTO.setApe2(s.getApe2());
        sDTO.setEmbarazada(s.isEmbarazada());
        sDTO.setCasado(s.isCasado());
        sDTO.setNumHijos(s.getNumHijos());
        sDTO.setEdad(s.getEdad());
        sDTO.setSexo(s.getSexo());

        return sDTO;
    }

    public static boolean comprobarNif(String dni){
        String dniPattern = "\\d{8}[A-HJ-NP-TV-Z]";
        if (!Pattern.matches(dniPattern, dni)) {
            return false;
        }

        // Obtener la parte numérica y la letra
        String dniNumbers = dni.substring(0, 8);
        char dniLetter = dni.charAt(8);

        // Calcular la letra correspondiente
        String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int dniNumeric = Integer.parseInt(dniNumbers);
        char expectedLetter = validLetters.charAt(dniNumeric % 23);

        // Comparar la letra esperada con la letra dada
        return dniLetter == expectedLetter;
    }

    public static Seguro getSeguroFromDTO(SeguroDTO seguroDTO) {
        Seguro s = new Seguro();
        s.setNif(seguroDTO.getNif());
        s.setNombre(seguroDTO.getNombre());
        s.setApe1(seguroDTO.getApe1());
        s.setApe2(seguroDTO.getApe2());
        s.setEdad(seguroDTO.getEdad());
        s.setSexo(seguroDTO.getSexo());
        s.setCasado(seguroDTO.isCasado());
        s.setEmbarazada(seguroDTO.isEmbarazada());
        s.setNumHijos(seguroDTO.getNumHijos());

        return s;
    }

    public static Seguro editSeguroFromDTO(SeguroDTO seguroDTO, Seguro s){
        s.setNif(seguroDTO.getNif());
        s.setNombre(seguroDTO.getNombre());
        s.setApe1(seguroDTO.getApe1());
        s.setApe2(seguroDTO.getApe2());
        s.setEdad(seguroDTO.getEdad());
        s.setSexo(seguroDTO.getSexo());
        s.setCasado(seguroDTO.isCasado());
        s.setEmbarazada(seguroDTO.isEmbarazada());
        s.setNumHijos(seguroDTO.getNumHijos());
        return s;
    }

    public static AsistenciaMedicaDTO getDTOFromAsistencia(AsistenciaMedica asistenciaMedica){
        AsistenciaMedicaDTO asistenciaMedicaDTO = new AsistenciaMedicaDTO();

        asistenciaMedicaDTO.setExplicacion(asistenciaMedica.getExplicacion());
        asistenciaMedicaDTO.setTipoAsistencia(asistenciaMedica.getTipoAsistencia());
        asistenciaMedicaDTO.setBreveDescripcion(asistenciaMedica.getBreveDescripcion());
        asistenciaMedicaDTO.setFecha(asistenciaMedica.getFecha());
        asistenciaMedicaDTO.setHora(asistenciaMedica.getHora());
        asistenciaMedicaDTO.setImporte(asistenciaMedica.getImporte());
        asistenciaMedicaDTO.setLugar(asistenciaMedica.getLugar());

        return asistenciaMedicaDTO;
    }

}
