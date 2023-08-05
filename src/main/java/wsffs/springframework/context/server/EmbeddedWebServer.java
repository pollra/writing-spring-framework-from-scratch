package wsffs.springframework.context.server;

import org.eclipse.jetty.server.session.SessionHandler;
import wsffs.springframework.context.Lifecycle;

public interface EmbeddedWebServer extends Lifecycle {

  void setHandler(SessionHandler sessionHandler);
}
