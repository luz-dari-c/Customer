package com.sysnet.pruebaTecnica.services.impl;

import com.sysnet.pruebaTecnica.dto.request.ClienteRequest;
import com.sysnet.pruebaTecnica.dto.response.ClienteResponse;
import com.sysnet.pruebaTecnica.entity.Cliente;
import com.sysnet.pruebaTecnica.entity.Enum.TipoIdentificacion;
import com.sysnet.pruebaTecnica.exceptions.ResourceNotFoundException;
import com.sysnet.pruebaTecnica.mapper.ClienteMapper;
import com.sysnet.pruebaTecnica.repository.ClienteRepository;
import com.sysnet.pruebaTecnica.services.ClienteService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponse guardar(ClienteRequest request) {

        validarCliente(request, null);

        Cliente cliente = clienteMapper.toEntity(request);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return clienteMapper.toResponse(clienteGuardado);
    }

    @Override
    public List<ClienteResponse> listar() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    @Override
    public ClienteResponse buscarPorId(Long id) {

        Cliente cliente = obtenerCliente(id);

        return clienteMapper.toResponse(cliente);
    }

    @Override
    public ClienteResponse actualizar(Long id, ClienteRequest request) {

        Cliente cliente = obtenerCliente(id);

        validarCliente(request, id);

        clienteMapper.updateEntity(request, cliente);

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return clienteMapper.toResponse(clienteActualizado);
    }

    @Override
    public void eliminar(Long id) {

        Cliente cliente = obtenerCliente(id);

        clienteRepository.delete(cliente);
    }

    private Cliente obtenerCliente(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado."));
    }

    private void validarCliente(ClienteRequest request, Long id) {

        if (id == null) {

            if (clienteRepository.existsByIdentificacion(request.getIdentificacion())) {
                throw new IllegalArgumentException("La identificación ya se encuentra registrada.");
            }

            if (clienteRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico ya se encuentra registrado.");
            }

        } else {

            if (clienteRepository.existsByIdentificacionAndIdNot(request.getIdentificacion(), id)) {
                throw new IllegalArgumentException("La identificación ya se encuentra registrada.");
            }

            if (clienteRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
                throw new IllegalArgumentException("El correo electrónico ya se encuentra registrado.");
            }

        }

        validarEdad(request.getTipoIdentificacion(), request.getFechaNacimiento());
    }

    private void validarEdad(TipoIdentificacion tipoIdentificacion, LocalDate fechaNacimiento) {

        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser mayor a la fecha actual.");
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        switch (tipoIdentificacion) {

            case RC:
                if (edad >= 7) {
                    throw new IllegalArgumentException("Ha seleccionado Registro Civil la edad debe ser menor a 7 años.");
                }
                break;

            case TI:
                if (edad < 7 || edad >= 18) {
                    throw new IllegalArgumentException("Ha seleccionado Tarjeta de Identidad la edad debe ser igual o mayor a 7 años y menor a 18 años.");
                }
                break;

            case CC:
                if (edad < 18) {
                    throw new IllegalArgumentException("Ha seleccionado Cédula de Ciudadanía la edad debe ser igual o mayor a 18 años.");
                }
                break;
        }
    }

    @Override
    public byte[] exportarExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet hoja = workbook.createSheet("Clientes");
            int fila = 0;
            Row encabezado = hoja.createRow(fila++);

            encabezado.createCell(0).setCellValue("Identificación");
            encabezado.createCell(1).setCellValue("Tipo");
            encabezado.createCell(2).setCellValue("Primer Nombre");
            encabezado.createCell(3).setCellValue("Segundo Nombre");
            encabezado.createCell(4).setCellValue("Primer Apellido");
            encabezado.createCell(5).setCellValue("Segundo Apellido");
            encabezado.createCell(6).setCellValue("Correo");
            encabezado.createCell(7).setCellValue("Teléfono");
            encabezado.createCell(8).setCellValue("Dirección");
            encabezado.createCell(9).setCellValue("Ocupación");
            encabezado.createCell(10).setCellValue("Fecha Nacimiento");

            List<Cliente> clientes = clienteRepository.findAll();

            for (Cliente cliente : clientes) {

                Row row = hoja.createRow(fila++);

                row.createCell(0).setCellValue(cliente.getIdentificacion());
                row.createCell(1).setCellValue(cliente.getTipoIdentificacion().name());
                row.createCell(2).setCellValue(cliente.getPrimerNombre());
                row.createCell(3).setCellValue(cliente.getSegundoNombre() == null ? "" : cliente.getSegundoNombre());
                row.createCell(4).setCellValue(cliente.getPrimerApellido());
                row.createCell(5).setCellValue(cliente.getSegundoApellido() == null ? "" : cliente.getSegundoApellido());
                row.createCell(6).setCellValue(cliente.getEmail());
                row.createCell(7).setCellValue(cliente.getTelefono());
                row.createCell(8).setCellValue(cliente.getDireccion() == null ? "" : cliente.getDireccion());
                row.createCell(9).setCellValue(cliente.getOcupacion() == null ? "" : cliente.getOcupacion());
                row.createCell(10).setCellValue(cliente.getFechaNacimiento().toString());
            }

            for (int i = 0; i <= 10; i++) {
                hoja.autoSizeColumn(i);
            }

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            workbook.write(output);
            return output.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el archivo Excel.");
        }

    }
}