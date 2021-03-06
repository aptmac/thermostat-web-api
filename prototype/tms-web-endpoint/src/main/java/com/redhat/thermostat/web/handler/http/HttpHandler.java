package com.redhat.thermostat.web.handler.http;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.redhat.thermostat.web.handler.storage.StorageHandler;

@Path("api")
@RolesAllowed("user")
public class HttpHandler {

    private final StorageHandler handler;

    public HttpHandler(StorageHandler handler) {
        this.handler = handler;
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public void test(@Context SecurityContext securityContext,
                         @Suspended final AsyncResponse asyncResponse) {
        handler.test(securityContext, asyncResponse);
    }

    @GET
    @Path("agents/{agentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getAgent(@Context SecurityContext securityContext,
                         @Suspended final AsyncResponse asyncResponse,
                         @PathParam("agentId") String agentId,
                         @QueryParam("size") @DefaultValue("1") String count,
                         @QueryParam("sort") @DefaultValue("-1") String sort) {
        handler.getAgent(securityContext, asyncResponse, agentId, count, sort);
    }

    @PUT
    @Path("agents/{agentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void putAgent(@Context SecurityContext context,
                             @Suspended final AsyncResponse asyncResponse,
                             String body) {
        handler.putAgent(context, asyncResponse, body);
    }
}
