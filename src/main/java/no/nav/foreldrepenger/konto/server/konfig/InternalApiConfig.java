package no.nav.foreldrepenger.konto.server.konfig;


import no.nav.foreldrepenger.konto.server.HealtCheckRest;

import no.nav.foreldrepenger.konto.server.PrometheusRestService;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath(InternalApiConfig.API_URI)
public class InternalApiConfig extends ResourceConfig {

    public static final String API_URI ="/internal";

    public InternalApiConfig() {
        register(HealtCheckRest.class, PrometheusRestService.class);
    }
}
