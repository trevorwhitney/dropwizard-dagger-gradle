package com.trevorwhitney.helloworld.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {

    private final String name;
    private final String envSpecific;

    private String environment;

    @JsonCreator
    public ApplicationConfiguration(
            @JsonProperty("name") String name,
            @JsonProperty("envSpecific") String envSpecific
    ) {
        this.name = name;
        this.envSpecific = envSpecific;
    }

    public String getName() {
        return name;
    }

    public String getEnvSpecific() {
        return envSpecific;
    }

    public String getEnvironment() {
        return environment;
    }

    //Only field that needs to be mutable, setup at runtime after config has been created
    public ApplicationConfiguration setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }
}
