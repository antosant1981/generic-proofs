package org.generic.proofs.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.generic.proofs.dto.LabelItem;
import org.generic.proofs.dto.SignatureItem;

@Slf4j
public class LabelsValidator implements Validator {

    @Override
    public boolean supports(Object data) {
        return data instanceof LabelItem;
    }

    @Override
    public ValidationResult validate(ValidatorChain validationChain, ValidationContext validationContext) {

        var labelItem = validationContext.getData(LabelItem.class);
        log.info("Labels Validator, getting in input the label item: {}", labelItem);

        var label = labelItem.label();

        if ("WRONG_FOR_THE_LABELS_STEP".equalsIgnoreCase(label)) {
            return new ValidationResult(ValidationResponse.INVALID, "Confidentiality label is not valid");
        }
        return validationChain.proceed(new ValidationContext(new SignatureItem(label)));
    }
}
