package com.blzb.service;

/**
 * Created by apimentel on 4/26/17.
 */
public class IdTakenException extends RuntimeException {
    public IdTakenException() {
        super("The Id is already taken");
    }
}
