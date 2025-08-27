package com.example.edufood.exceptions;

public class ApplicantNotFoundException extends RuntimeException {
    public ApplicantNotFoundException() {
        super("applicant not found");
    }
}
