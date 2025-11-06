package no.nav.foreldrepenger.grunnlag.server;

class JettyDevServer extends JettyServer {

    JettyDevServer() {
        super(9191);
    }

    static void main() throws Exception {
        new JettyDevServer().start();
    }
}
