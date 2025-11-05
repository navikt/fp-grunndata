package no.nav.foreldrepenger.konto.server.konfig;

import java.util.HashMap;
import java.util.Map;

import no.nav.foreldrepenger.konto.server.JacksonJsonConfig;

import no.nav.foreldrepenger.konto.server.error.GeneralRestExceptionMapper;

import no.nav.foreldrepenger.konto.uttak.UttakRest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath(ApiConfig.API_URI)
public class ApiConfig extends ResourceConfig {

    public static final String API_URI ="/api";

    public ApiConfig() {
        setApplicationName(ApiConfig.class.getSimpleName());
        register(GeneralRestExceptionMapper.class); // Exception handling
        register(JacksonJsonConfig.class); // Json
        register(UttakRest.class);
        setProperties(getApplicationProperties());
    }

    static Map<String, Object> getApplicationProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Ref Jersey doc
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        properties.put(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        return properties;
    }

}
