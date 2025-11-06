package no.nav.foreldrepenger.grunnlag.server.konfig;


import no.nav.foreldrepenger.grunnlag.server.HealtCheckRest;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath(InternalApiConfig.API_URI)
public class InternalApiConfig extends ResourceConfig {

    public static final String API_URI ="/internal";

    public InternalApiConfig() {
        register(HealtCheckRest.class);
    }
}
