package no.nav.foreldrepenger.grunnlag.server.konfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import no.nav.foreldrepenger.konfig.Environment;
import no.nav.foreldrepenger.grunnlag.server.CorsResponseFilter;
import no.nav.foreldrepenger.grunnlag.server.JacksonJsonConfig;

import no.nav.foreldrepenger.grunnlag.server.error.GeneralRestExceptionMapper;

import no.nav.foreldrepenger.grunnlag.server.error.ValidationExceptionMapper;
import no.nav.foreldrepenger.grunnlag.server.konfig.swagger.OpenApiUtils;
import no.nav.foreldrepenger.grunnlag.uttak.UttakRest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath(ApiConfig.API_URI)
public class ApiConfig extends ResourceConfig {
    private static final Environment ENV = Environment.current();
    public static final String API_URI ="/api";

    public ApiConfig() {
        setApplicationName(ApiConfig.class.getSimpleName());
        register(GeneralRestExceptionMapper.class); // Exception handling
        register(ValidationExceptionMapper.class); // Exception handling
        register(JacksonJsonConfig.class); // Json
        if (!ENV.isProd()) {
            registerClasses(CorsResponseFilter.class); // CORS - allow all origins
        }
        registerClasses(getApplicationClasses());
        registerOpenApi();
        setProperties(getApplicationProperties());
    }

    private static Set<Class<?>> getApplicationClasses() {
        return Set.of(UttakRest.class);
    }

    private void registerOpenApi() {
        OpenApiUtils.openApiConfigFor("Fpsoknad - specifikasjon for typegenerering frontend", this)
            .readerClassTypegenereingFrontend()
            .registerClasses(getApplicationClasses())
            .buildOpenApiContext();
        register(OpenApiResource.class);
    }

    static Map<String, Object> getApplicationProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Ref Jersey doc
        properties.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        properties.put(ServerProperties.PROCESSING_RESPONSE_ERRORS_ENABLED, true);
        return properties;
    }

}
