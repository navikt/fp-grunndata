package no.nav.foreldrepenger.grunnlag.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/health")
@ApplicationScoped
public class HealtCheckRest {

    HealtCheckRest() {
        //CDI
    }

    @GET
    @Path("/isAlive")
    public Response isAlive() {
        return Response.ok().build();
    }

    @GET
    @Path("/isReady")
    public Response isReady() {
        return Response.ok().build();
    }

    @GET
    @Path("/preStop")
    public Response preStop() {
        return Response.ok().build();
    }
}
