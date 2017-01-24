package com.trevorwhitney.helloworld.helloworld;

import dagger.Module;
import dagger.Provides;

@Module(injects = {HelloWorldResource.class}, complete = false)
public class HelloWorldModule {

    @Provides
    UserBean provideUserBean() {
        return new UserBean();
    }
}
