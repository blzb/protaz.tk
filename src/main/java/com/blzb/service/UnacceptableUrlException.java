package com.blzb.service;

/**
 * Created by apimentel on 4/26/17.
 */
public class UnacceptableUrlException extends RuntimeException {
    public UnacceptableUrlException() {
        super("The supplied URL is not valid");
    }
}
