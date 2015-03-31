package com.weatherapp.api.helper;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class RestError {

    @Expose
    private List<Error> errors = new ArrayList<Error>();

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }}