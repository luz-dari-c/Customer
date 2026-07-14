package com.sysnet.pruebaTecnica.services;

import com.sysnet.pruebaTecnica.dto.request.ClienteRequest;
import com.sysnet.pruebaTecnica.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteService {

    ClienteResponse guardar(ClienteRequest request);

    List<ClienteResponse> listar();

    ClienteResponse buscarPorId(Long id);

    ClienteResponse actualizar(Long id, ClienteRequest request);

    void eliminar(Long id);

    byte[] exportarExcel();

}
