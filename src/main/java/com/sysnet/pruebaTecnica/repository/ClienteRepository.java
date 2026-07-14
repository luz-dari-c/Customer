package com.sysnet.pruebaTecnica.repository;


import com.sysnet.pruebaTecnica.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByIdentificacion(String identificacion);
    Optional<Cliente> findByEmail(String email);
    boolean existsByIdentificacion(String identificacion);
    boolean existsByEmail(String email);
    boolean existsByIdentificacionAndIdNot(String identificacion, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);

}
