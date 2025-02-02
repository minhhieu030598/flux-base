package com.minhhieu.commons.model.response;

import com.minhhieu.commons.exception.FieldViolation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meta {
    String code;
    String message;
    List<FieldViolation> errors;
    Integer page;
    Integer size;
    Long total;
}
