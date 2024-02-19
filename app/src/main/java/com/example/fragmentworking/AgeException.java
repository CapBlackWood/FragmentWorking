package com.example.fragmentworking;

public class AgeException extends Exception {

    public AgeException(String message) {
        super(message);
        System.err.println("Ошибка" + message);
        printStackTrace();
    }
}
