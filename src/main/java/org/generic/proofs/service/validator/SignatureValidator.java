package org.generic.proofs.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.generic.proofs.dto.SignatureItem;

@Slf4j
public class SignatureValidator implements Validator {

    @Override
    public boolean supports(Object data) {
        return data instanceof SignatureItem;
    }

    @Override
    public ValidationResult validate(ValidatorChain validationChain, ValidationContext validationContext) {

        SignatureItem signatureItem = validationContext.getData(SignatureItem.class);
        log.info("Labels Validator, getting in input the label item: {}", signatureItem);

        if ("WRONG_FOR_THE_SIGNATURE_STEP".equalsIgnoreCase(signatureItem.signature())) {
            return new ValidationResult(ValidationResponse.INVALID, "Signature is not valid");
        }

        return validationChain.proceed(new ValidationContext("Signature validation is successful"));
    }
}
