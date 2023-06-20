package com.helloiconic.pokemonAPI.Controller.ResponseTransformer;

import com.helloiconic.pokemonAPI.Controller.ResponseTransformer.ErrorInterfaces.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class InvalidInputArgumentTransformer {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationException(MethodArgumentNotValidException ex) {
        // Obtener los mensajes de error de la excepción
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map((ObjectError t) -> t.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessage e = new ErrorMessage();
        e.message = errors.toString();
        e.statusCode = 400;
        // Devolver una respuesta de error con los mensajes de error
        return ResponseEntity.badRequest().body(e);
    }

}

