package com.minhhieu.commons.exception;

import lombok.Value;

@Value
public class FieldViolation {
    String field;
    String description;
}
