package com.example.IdentityReconciliation.exceptions;

import com.example.IdentityReconciliation.models.ErrorCode;
import com.example.IdentityReconciliation.models.ErrorKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {
    private HashMap<String, List<String>> errors;

    public ApplicationException(ErrorKey key, ErrorCode errorCode) {
        super(key + " " + errorCode);
        this.add(key.getKey(), errorCode);
    }

    public void add(String key, ErrorCode errorCode) {
        if (errors == null)
            errors = new HashMap<>();
        errors.put(key, Arrays.asList(errorCode.name()));
    }

    public void add(ErrorKey key, ErrorCode errorCode) {
        if (errors == null)
            errors = new HashMap<>();
        errors.put(key.getKey(), Arrays.asList(errorCode.name()));
    }


    public Boolean containsException() {
        return !CollectionUtils.isEmpty(errors);
    }

    public void throwIfHasError() {
        if (containsException())
            throw this;
    }

}