package com.trevorwhitney.helloworld;


import com.trevorwhitney.helloworld.config.ApplicationConfiguration;
import com.trevorwhitney.helloworld.config.RootDaggerModule;
import com.trevorwhitney.helloworld.helloworld.HelloWorldResource;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.configuration.UrlConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.URL;
import java.util.Optional;

public class ApplicationRunner extends Application<ApplicationConfiguration> {
    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> serviceConfigurationBootstrap) {
        serviceConfigurationBootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        new UrlConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

    }

    @Override
    public void run(ApplicationConfiguration applicationConfiguration, Environment environment) throws Exception {
        applicationConfiguration.setEnvironment("development");
        getApplicationEnvironment().ifPresent(dropEnv ->
                applicationConfiguration.setEnvironment(dropEnv.toLowerCase())
        );

        ObjectGraph objectGraph = ObjectGraph.create(new RootDaggerModule(applicationConfiguration));
        environment.jersey().register(objectGraph.get(HelloWorldResource.class));
    }

    public static void main(String... args) throws Exception {
        ClassLoader classLoader = ApplicationRunner.class.getClassLoader();
        URL configuration = loadEnvironmentSpecifcConfiguration(classLoader);
        new ApplicationRunner().run("server", configuration.toString());
    }

    private static URL loadEnvironmentSpecifcConfiguration(ClassLoader classLoader) {
        String dropEnv = getApplicationEnvironment().orElse("development").toLowerCase();

        switch (dropEnv) {
            case "production":
                return classLoader.getResource("config-production.yml");
            case "test":
                return classLoader.getResource("config-test.yml");
            default:
                return classLoader.getResource("config.yml");
        }
    }

    private static Optional<String> getApplicationEnvironment() {
        return Optional.ofNullable(System.getenv("DROP_ENV"));
    }
}
