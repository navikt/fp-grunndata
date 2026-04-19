package no.nav.foreldrepenger.grunnlag.server.konfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;
import no.nav.foreldrepenger.grunnlag.server.CorsResponseFilter;
import no.nav.foreldrepenger.grunnlag.server.konfig.swagger.TypegenereringFrontendOpenApiReader;
import no.nav.foreldrepenger.grunnlag.uttak.UttakRest;
import no.nav.foreldrepenger.konfig.Environment;
import no.nav.vedtak.openapi.OpenApiUtils;
import no.nav.vedtak.server.rest.GeneralRestExceptionMapper;
import no.nav.vedtak.server.rest.ValidationExceptionMapper;
import no.nav.vedtak.server.rest.jackson.Jackson2MapperFeature;

@ApplicationPath(ApiConfig.API_URI)
public class ApiConfig extends ResourceConfig {
    private static final Environment ENV = Environment.current();
    public static final String API_URI ="/api";

    public ApiConfig() {
        setApplicationName(ApiConfig.class.getSimpleName());
        // Standard rest-oppsett, men pga uinnloggete requests så er FpRestJackson2Feature utelukket
        register(Jackson2MapperFeature.class);
        register(ValidationExceptionMapper.class);
        register(GeneralRestExceptionMapper.class);
        // Openapi i non-prod
        if (!ENV.isProd()) {
            registerClasses(CorsResponseFilter.class); // CORS - allow all origins
            registerOpenApi();
        }
        // Businessklasser
        registerClasses(getApplicationClasses());
        setProperties(getApplicationProperties());
    }

    private static Set<Class<?>> getApplicationClasses() {
        return Set.of(UttakRest.class);
    }

    private void registerOpenApi() {
        var contextPath = ENV.getProperty("context.path", "/fpgrunndata");
        OpenApiUtils.openApiConfigFor("Fp-grunndata - specifikasjon for typegenerering frontend", contextPath, this)
            .readerClass(TypegenereringFrontendOpenApiReader.class)
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
