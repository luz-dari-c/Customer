package com.sysnet.pruebaTecnica.dto.response;

import com.sysnet.pruebaTecnica.entity.Enum.TipoIdentificacion;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteResponse {

    private Long id;
    private String identificacion;
    private TipoIdentificacion tipoIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String direccion;
    private String telefono;
    private String email;
    private String ocupacion;
    private LocalDate fechaNacimiento;
    private String foto;
}
