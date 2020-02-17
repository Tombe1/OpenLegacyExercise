package com.OpenLegacyExercise.Exceptions;

public class ItemExistsException extends Throwable {
    public ItemExistsException() {
        super("Item doesnt exist!");
    }

}
