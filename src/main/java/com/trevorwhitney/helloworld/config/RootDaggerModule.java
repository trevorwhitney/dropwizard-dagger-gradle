package com.trevorwhitney.helloworld.config;


import com.trevorwhitney.helloworld.helloworld.HelloWorldModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = HelloWorldModule.class, library = true)
public class RootDaggerModule {
    private final ApplicationConfiguration configuration;

    public RootDaggerModule(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Provides
    ApplicationConfiguration provideConfiguration() {
        return configuration;
    }
}
