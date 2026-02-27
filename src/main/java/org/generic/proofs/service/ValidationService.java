package org.generic.proofs.service;

import org.generic.proofs.service.validator.ValidationResult;

public interface ValidationService {
    ValidationResult validate(Object metadata);
}
