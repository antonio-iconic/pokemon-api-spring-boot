package com.helloiconic.pokemonAPI.Controller.ResponseTransformer.ErrorInterfaces;

public class ErrorMessage {
    public int statusCode;
    public String message;

    public ErrorMessage() {
    }

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
