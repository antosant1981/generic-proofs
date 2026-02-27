package org.generic.proofs.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.generic.proofs.dto.BindingItem;
import org.generic.proofs.dto.LabelItem;

@Slf4j
public class BindingValidator implements Validator {

    @Override
    public boolean supports(Object data) {
        return data instanceof BindingItem;
    }

    @Override
    public ValidationResult validate(ValidatorChain validationChain, ValidationContext validationContext) {

        var bindingItem = validationContext.getData(BindingItem.class);
        log.info("Binding Validator, getting in input the binding item: {}", bindingItem);

        if ("WRONG_FOR_THE_BINDING_STEP".equalsIgnoreCase(bindingItem.policyId())) {
            return new ValidationResult(ValidationResponse.INVALID, "Binding policy is not valid");
        }

        var confidentiality = bindingItem.confidentiality();
        return validationChain.proceed(new ValidationContext(new LabelItem(confidentiality)));
    }
}
