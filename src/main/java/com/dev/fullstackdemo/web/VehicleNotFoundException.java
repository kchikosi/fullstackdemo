package com.dev.fullstackdemo.web;

public class VehicleNotFoundException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public VehicleNotFoundException() {
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param id for the detail message. The detail message is saved for
     *           later retrieval by the {@link #getMessage()} method.
     */
    public VehicleNotFoundException(long id) {
        super("Vehicle with id [" + id + "] not found");
    }
}
