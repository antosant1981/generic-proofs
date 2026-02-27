package org.generic.proofs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "validation.chain")
public record ValidatorChainSettings(ValidatorsSettings validators) {}
