package com.sysnet.pruebaTecnica.exceptions;



public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }

}
