package com.sysnet.pruebaTecnica.dto.request;

import com.sysnet.pruebaTecnica.entity.Enum.TipoIdentificacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClienteRequest {

    @NotBlank(message = "La identificación es obligatoria.")
    private String identificacion;

    @NotNull(message = "Seleccione un tipo de identificación.")
    private TipoIdentificacion tipoIdentificacion;

    @NotBlank(message = "El primer nombre es obligatorio.")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El primer nombre solo puede contener letras."
    )
    private String primerNombre;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]*$", message = "El segundo nombre solo puede contener letras"
    )
    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El primer apellido solo puede contener letras"
    )
    private String primerApellido;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]*$", message = "El segundo apellido solo puede contener letras"
    )
    private String segundoApellido;

    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "\\d+", message = "El teléfono solo debe contener números")
    private String telefono;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Ingrese un correo electrónico válido")
    private String email;

    private String ocupacion;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate fechaNacimiento;

    private String foto;

}