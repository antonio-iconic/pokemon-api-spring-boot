package com.helloiconic.pokemonAPI.Controller.ResponseTransformer;

import com.helloiconic.pokemonAPI.Controller.ResponseTransformer.ErrorInterfaces.ErrorMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class HTTPExceptionTransformer {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        String errorMessage = ex.getReason();

        // Obtener el código de estado HTTP de la excepción
        HttpStatusCode httpStatusCode = ex.getStatusCode();

        ErrorMessage e = new ErrorMessage();

        e.message = errorMessage;
        e.statusCode = httpStatusCode.value();
        // Crear un objeto ResponseEntity con el mensaje de error y el código de estado
        return ResponseEntity.status(httpStatusCode).body(e);
    }
}
