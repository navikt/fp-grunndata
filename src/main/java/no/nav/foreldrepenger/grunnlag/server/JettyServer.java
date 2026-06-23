package no.nav.foreldrepenger.grunnlag.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import no.nav.foreldrepenger.konfig.Environment;
import no.nav.foreldrepenger.grunnlag.server.konfig.ApiConfig;
import no.nav.foreldrepenger.grunnlag.server.konfig.InternalApiConfig;
import no.nav.vedtak.server.jetty.JettyServerBuilder;

public class JettyServer {
    private static final Logger LOG = LoggerFactory.getLogger(JettyServer.class);
    private static final Environment ENV = Environment.current();

    private static final String CONTEXT_PATH = ENV.getProperty("context.path", "/fpgrunndata");

    private final Integer serverPort;

    JettyServer(int serverPort) {
        this.serverPort = serverPort;
    }

    static void main() throws Exception {
        jettyServer().start();
    }

    private static JettyServer jettyServer() {
        return new JettyServer(ENV.getProperty("server.port", Integer.class, 8080));
    }

    /**
     * Vi bruker SLF4J + logback, Jersey bruker JUL for logging.
     * Setter opp en bridge for å få Jersey til å logge gjennom Logback også.
     */
    private static void konfigurerLogging() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    void start() throws Exception {
        konfigurerLogging();
        LOG.info("Starter server");
        System.setProperty("task.manager.runner.threads", "4");
        var server = JettyServerBuilder.builder()
            .port(serverPort)
            .contextPath(CONTEXT_PATH)
            .registerRestApp(InternalApiConfig.API_URI, InternalApiConfig.class)
            .registerRestApp(ApiConfig.API_URI, ApiConfig.class)
            .build();
        server.start();
        LOG.info("Server startet på port: {}", serverPort);
        server.join();
    }
}
