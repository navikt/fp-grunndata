package no.nav.foreldrepenger.konto.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static no.nav.vedtak.log.metrics.MetricsUtil.REGISTRY;

@Path("/metrics")
@Produces(TEXT_PLAIN)
@ApplicationScoped
public class PrometheusRestService {

    public PrometheusRestService() {
        // CDI
    }

    @GET
    @Path("/prometheus")
    public String prometheus() {
        return REGISTRY.scrape();
    }
}
