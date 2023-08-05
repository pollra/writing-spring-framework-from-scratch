package wsffs.springframework.context.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import wsffs.springframework.beans.annotation.Component;

public class JettyEmbeddedWebServer implements EmbeddedWebServer {

  private final Server server;

  public JettyEmbeddedWebServer(int port) {
    this.server = new Server(port);
  }

  @Override
  public void setHandler(SessionHandler sessionHandler) {
    this.server.setHandler(sessionHandler);
  }

  @Override
  public void start() throws Exception {
    server.start();
    server.join();
  }

  @Override
  public void stop() {

  }

  @Override
  public boolean isRunning() {
    return false;
  }
}
