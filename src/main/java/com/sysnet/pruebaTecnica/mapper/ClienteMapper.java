package com.sysnet.pruebaTecnica.mapper;


import com.sysnet.pruebaTecnica.dto.request.ClienteRequest;
import com.sysnet.pruebaTecnica.dto.response.ClienteResponse;
import com.sysnet.pruebaTecnica.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {

        Cliente cliente = new Cliente();
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setTipoIdentificacion(request.getTipoIdentificacion());
        cliente.setPrimerNombre(request.getPrimerNombre());
        cliente.setSegundoNombre(request.getSegundoNombre());
        cliente.setPrimerApellido(request.getPrimerApellido());
        cliente.setSegundoApellido(request.getSegundoApellido());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setOcupacion(request.getOcupacion());
        cliente.setFechaNacimiento(request.getFechaNacimiento());
        cliente.setFoto(request.getFoto());

        return cliente;
    }

    public ClienteResponse toResponse(Cliente cliente) {

        ClienteResponse response = new ClienteResponse();
        response.setId(cliente.getId());
        response.setIdentificacion(cliente.getIdentificacion());
        response.setTipoIdentificacion(cliente.getTipoIdentificacion());
        response.setPrimerNombre(cliente.getPrimerNombre());
        response.setSegundoNombre(cliente.getSegundoNombre());
        response.setPrimerApellido(cliente.getPrimerApellido());
        response.setSegundoApellido(cliente.getSegundoApellido());
        response.setDireccion(cliente.getDireccion());
        response.setTelefono(cliente.getTelefono());
        response.setEmail(cliente.getEmail());
        response.setOcupacion(cliente.getOcupacion());
        response.setFechaNacimiento(cliente.getFechaNacimiento());
        response.setFoto(cliente.getFoto());

        return response;
    }

    public void updateEntity(ClienteRequest request, Cliente cliente) {
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setTipoIdentificacion(request.getTipoIdentificacion());
        cliente.setPrimerNombre(request.getPrimerNombre());
        cliente.setSegundoNombre(request.getSegundoNombre());
        cliente.setPrimerApellido(request.getPrimerApellido());
        cliente.setSegundoApellido(request.getSegundoApellido());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setOcupacion(request.getOcupacion());
        cliente.setFechaNacimiento(request.getFechaNacimiento());
        cliente.setFoto(request.getFoto());

    }

    public List<ClienteResponse> toResponseList(List<Cliente> clientes) {
        return clientes.stream().map(this::toResponse).toList();
    }

}
