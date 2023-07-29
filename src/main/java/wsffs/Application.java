package wsffs;

import org.eclipse.jetty.server.Server;
import wsffs.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import wsffs.springframework.context.ApplicationContext;
import wsffs.web.DummyObject;
import wsffs.web.DummyRepository;
import wsffs.web.DummyService;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigServletWebServerApplicationContext(Application.class);
        applicationContext.refresh();

        DummyService dummyService = applicationContext.getBean("dummyService", DummyService.class);
        DummyObject dummy = dummyService.get();
        System.out.println("dummy = " + dummy);
    }
}

//public class Application {
//
//  public static void main(String[] args) throws Exception {
//    final AnnotationConfigServletWebServerApplicationContext annotationConfigServletWebServerApplicationContext =
//        new AnnotationConfigServletWebServerApplicationContext(
//            Application.class.getPackageName()
//        );
//
//    annotationConfigServletWebServerApplicationContext.refresh();
//
//    final Server server = new Server(8080);
//    final ServletContextHandler contextHandler = new ServletContextHandler();
//    final DispatcherServlet dispatcherServlet = new DispatcherServlet();
//    final ServletHolder dispatcherServletHolder = new ServletHolder(dispatcherServlet);
//    contextHandler.setContextPath("/");
//    contextHandler.addServlet(dispatcherServletHolder, "/*");
//    server.setHandler(contextHandler);
//    server.start();
//  }
//}
