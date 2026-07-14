package com.sysnet.pruebaTecnica.entity;


import com.sysnet.pruebaTecnica.entity.Enum.TipoIdentificacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String identificacion;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "Por favor solo ingrese letras")
    @Column(nullable = false, length = 60)
    private String primerNombre;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]*$", message = "Por favor solo ingrese letras")
    private String segundoNombre;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "Por favor solo ingrese letras")
    @Column(nullable = false, length = 60)
    private String primerApellido;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]*$", message = "Por favor solo ingrese letras")
    private String segundoApellido;

    private String direccion;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "Por favor solo ingrese numeros, ejemplo: 3200029292")
    private String telefono;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String ocupacion;

    @NotNull
    private LocalDate fechaNacimiento;

    @Column(length = 255)
    private String foto;


}
