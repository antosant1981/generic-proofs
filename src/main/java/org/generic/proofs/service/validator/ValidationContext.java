package org.generic.proofs.service.validator;

public record ValidationContext(Object data) {
    public <T> T getData(Class<T> type) {
        return type.cast(data);
    }
}
