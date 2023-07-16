package wsffs.springframework.boot.web.servlet.context;

import wsffs.springframework.beans.BeansException;
import wsffs.springframework.beans.factory.config.BeanDefinition;
import wsffs.springframework.beans.factory.support.BeanDefinitionRegistry;
import wsffs.springframework.context.ApplicationContext;
import wsffs.springframework.context.annotation.AnnotationConfigRegistry;
import wsffs.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class AnnotationConfigServletWebServerApplicationContext
        implements ApplicationContext, AnnotationConfigRegistry, BeanDefinitionRegistry {

    private final ClassPathBeanDefinitionScanner scanner;

    private String[] basePackages;

    private AnnotationConfigServletWebServerApplicationContext() {
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }

    public AnnotationConfigServletWebServerApplicationContext(String... basePackages) {
        this();
        scan(basePackages);
        refresh();
    }

    /**
     * Load or refresh the persistent representation of the configuration, which
     * might be from Java-based configuration, an XML file, a properties file, a
     * relational database schema, or some other format.
     * <p>As this is a startup method, it should destroy already created singletons
     * if it fails, to avoid dangling resources. In other words, after invocation
     * of this method, either all or no singletons at all should be instantiated.
     *
     * @throws BeansException        if the bean factory could not be initialized
     * @throws IllegalStateException if already initialized and multiple refresh
     *                               attempts are not supported
     * 설정의 영속적인 표현을 로드하거나 리프레시 할 수 있습니다. 이는 자바 기반 설정, XML 파일,
     * 프로퍼티 파일, 관계형 데이터베이스 스키마 스키마 혹은 몇 다른 포맷등에서 가져올 수 있습니다.
     * 이는 startup 메소드로서, 실패하는 경우 생성된 싱글톤들을 제거하여 댕글링 리소스를 피할 수 있습니다.
     * 바꿔말하면, 이 메소드가 호출되고 난 후에는 모든 싱글톤 객체가 인스턴스화 되거나 아무것도 인스턴스화 되어선 안됩니다.
     * (all or nothing)
     */
    public void refresh() throws BeansException {

    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public void scan(String... basePackages) {
        this.basePackages = basePackages;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }
}
