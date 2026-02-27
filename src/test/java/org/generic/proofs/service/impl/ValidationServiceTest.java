package org.generic.proofs.service.impl;

import org.generic.proofs.config.DefaultSettings;
import org.generic.proofs.config.ValidatorChainSettings;
import org.generic.proofs.config.ValidatorSetting;
import org.generic.proofs.config.ValidatorsSettings;
import org.generic.proofs.dto.BindingItem;
import org.generic.proofs.dto.LabelItem;
import org.generic.proofs.dto.SignatureItem;
import org.generic.proofs.service.ValidationService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationServiceTest {

    @Test
    void testValidationWithBindingLabelsAndSignatureWhenInputIsCorrect() {

        // Given
        var validatorChainSettings = validatorSettingsWithBindingLabelsAndSignature();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var bindingItem = new BindingItem("NATO", "Unclassified");

        // When
        var result = validationService.validate(bindingItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("VALID");
        assertThat(result.message()).isEqualTo("All the validation steps are complete");
    }

    @Test
    void testValidationWithBindingLabelsAndSignatureWhenInputIsNotCorrect() {

        // Given
        var validatorChainSettings = validatorSettingsWithBindingLabelsAndSignature();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var signatureItem = new SignatureItem("a_signature");

        // When
        var result = validationService.validate(signatureItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("VALID");
        assertThat(result.message()).isEqualTo("All the validation steps are complete");
    }

    @Test
    void testValidationWithLabelsOnlyWhenInputIsCorrect() {

        // Given
        var validatorChainSettings = validatorSettingsWithLabelsOnly();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var labelItem = new LabelItem("a_label");

        // When
        var result = validationService.validate(labelItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("VALID");
        assertThat(result.message()).isEqualTo("All the validation steps are complete");
    }

    @Test
    void testValidationWithBindingLabelsAndSignatureAndFailAtFirstStep() {

        // Given
        var validatorChainSettings = validatorSettingsWithBindingLabelsAndSignature();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var bindingItem = new BindingItem("WRONG_FOR_THE_BINDING_STEP", "Unclassified");

        // When
        var result = validationService.validate(bindingItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("INVALID");
        assertThat(result.message()).isEqualTo("Binding policy is not valid");
    }

    @Test
    void testValidationWithBindingLabelsAndSignatureAndFailAtTheSecondStep() {

        // Given
        var validatorChainSettings = validatorSettingsWithBindingLabelsAndSignature();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var bindingItem = new BindingItem("NATO", "WRONG_FOR_THE_LABELS_STEP");

        // When
        var result = validationService.validate(bindingItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("INVALID");
        assertThat(result.message()).isEqualTo("Confidentiality label is not valid");
    }

    @Test
    void testValidationWithBindingLabelsAndSignatureAndFailAtTheThirdStep() {

        // Given
        var validatorChainSettings = validatorSettingsWithBindingLabelsAndSignature();
        ValidationService validationService = new ValidationServiceImpl(validatorChainSettings);
        var bindingItem = new BindingItem("NATO", "WRONG_FOR_THE_SIGNATURE_STEP");

        // When
        var result = validationService.validate(bindingItem);

        // Then
        assertThat(result.validationResponse().name()).isEqualTo("INVALID");
        assertThat(result.message()).isEqualTo("Signature is not valid");
    }

    private ValidatorChainSettings validatorSettingsWithBindingLabelsAndSignature() {
        return setupChainSettingsByEnabledFlag(true,
                true,
                true);
    }

    private ValidatorChainSettings validatorSettingsWithLabelsOnly() {
        return setupChainSettingsByEnabledFlag(false,
                true,
                false);
    }

    private ValidatorChainSettings setupChainSettingsByEnabledFlag(boolean isSignatureEnabled,
                                                                   boolean isLabelsEnabled,
                                                                   boolean isBindingEnabled) {
        var signature = new ValidatorSetting(isSignatureEnabled,
                new DefaultSettings("signature-policy-id", "signature-conf"));
        var labels = new ValidatorSetting(isLabelsEnabled,
                new DefaultSettings("labels-policy-id", "labels-conf"));
        var binding = new ValidatorSetting(isBindingEnabled,
                new DefaultSettings("binding-policy-id", "binding-conf"));

        return new ValidatorChainSettings(
                new ValidatorsSettings(signature, labels, binding));
    }
}
