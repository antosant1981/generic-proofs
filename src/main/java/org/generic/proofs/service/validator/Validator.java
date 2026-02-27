package org.generic.proofs.service.validator;

public interface Validator {

    boolean supports(Object data);

    ValidationResult validate(ValidatorChain validationChain,
                              ValidationContext validationContext);
}
