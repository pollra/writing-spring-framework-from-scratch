package wsffs;

import org.eclipse.jetty.server.Server;
import wsffs.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import wsffs.springframework.context.ApplicationContext;
import wsffs.springframework.boot.web.servlet.controller.ServletFrontController;
import wsffs.web.DummyObject;
import wsffs.web.DummyService;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigServletWebServerApplicationContext(Application.class);
        applicationContext.refresh();

        DummyService dummyService = applicationContext.getBean("dummyService", DummyService.class);
        DummyObject dummy = dummyService.get();
        System.out.println("dummy = " + dummy);

        ServletFrontController servletFrontController = new ServletFrontController(applicationContext);
        Server server = new Server(8080);

        try {
            server.setHandler(servletFrontController);

            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
//public class Application {
//
//    public static void main(String[] args) {
//        final AnnotationConfigServletWebServerApplicationContext annotationConfigServletWebServerApplicationContext =
//            new AnnotationConfigServletWebServerApplicationContext(
//                Application.class.getPackageName()
//            );
//
//        annotationConfigServletWebServerApplicationContext.refresh();
//    }
//}
