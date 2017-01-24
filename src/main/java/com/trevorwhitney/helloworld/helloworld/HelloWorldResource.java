package com.trevorwhitney.helloworld.helloworld;

import com.codahale.metrics.annotation.Timed;
import com.trevorwhitney.helloworld.config.ApplicationConfiguration;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final UserBean userBean;
    private final ApplicationConfiguration configuration;

    @Inject
    public HelloWorldResource(UserBean userBean, ApplicationConfiguration configuration) {
        this.userBean = userBean;
        this.configuration = configuration;
    }

    @GET
    @Timed
    public String sayHello() {
        return "Hello There From Dropwizard using Dagger. \n" +
                "You are in the " + configuration.getEnvironment() + " environment.\n" +
                "The envSpecific variable is: " + configuration.getEnvSpecific() + "\n" +
                "userBean Data: " + userBean.getName() +
                " with the id: " + userBean.randomId;
    }
}
