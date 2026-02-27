package org.generic.proofs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.generic.proofs.config.ValidatorChainSettings;
import org.generic.proofs.service.ValidationService;
import org.generic.proofs.service.validator.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ValidationServiceImpl implements ValidationService {

    private final ValidatorChainSettings validatorChainSettings;

    public ValidationServiceImpl(ValidatorChainSettings validatorChainSettings) {
        this.validatorChainSettings = validatorChainSettings;
    }

    @Override
    public ValidationResult validate(Object metadata) {

        var validationContext = new ValidationContext(metadata);

        var result = validationChain().proceed(validationContext);

        log.info("Validation result: {}", result.validationResponse());

        return result;
    }

    private ValidatorChain validationChain() {
        List<Validator> validators = new ArrayList<>();

        var validatorsSettings = validatorChainSettings.validators();

        if (validatorsSettings.binding().enabled()) {
            validators.add(new BindingValidator());
        }

        if (validatorsSettings.labels().enabled()) {
            validators.add(new LabelsValidator());
        }

        if (validatorsSettings.signature().enabled()) {
            validators.add(new SignatureValidator());
        }

        return new ValidatorChain(validators);
    }
}
