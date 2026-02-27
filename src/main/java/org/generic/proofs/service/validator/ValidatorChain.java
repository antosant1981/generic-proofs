package org.generic.proofs.service.validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.util.Optional.ofNullable;

public class ValidatorChain {
    private final Queue<Validator> validators;

    public ValidatorChain(List<Validator> validators) {
        this.validators = new LinkedList<>(validators);
    }

    public ValidationResult proceed(ValidationContext validationContext) {
        return ofNullable(validators.poll())
                .filter(validator -> validator.supports(validationContext.data()))
                .map(validator -> validator.validate(this, validationContext))
                .orElse(new ValidationResult(ValidationResponse.VALID, "All the validation steps are complete"));
    }
}