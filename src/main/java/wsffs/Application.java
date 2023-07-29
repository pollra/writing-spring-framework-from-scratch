package wsffs;

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
//    public static void main(String[] args) {
//        final AnnotationConfigServletWebServerApplicationContext annotationConfigServletWebServerApplicationContext =
//            new AnnotationConfigServletWebServerApplicationContext(
//                Application.class.getPackageName()
//            );
//
//        annotationConfigServletWebServerApplicationContext.refresh();
//    }
//}
