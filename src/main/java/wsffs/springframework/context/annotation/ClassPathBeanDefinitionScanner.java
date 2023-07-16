package wsffs.springframework.context.annotation;

import wsffs.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A bean definition scanner that detects bean candidates on the classpath,
 * registering corresponding bean definitions with a given registry ({@code BeanFactory}
 * or {@code ApplicationContext}).
 * <p>
 * classpath 상의 bean 후보를 탐지하는 bean definition(빈 정의) 스캐너입니다.
 * 주어진 레지스트리(BeanFactory)나 ApplicationContext로 해당하는 bean 정의들을 등록합니다.
 *
 * <p>Candidate classes are detected through configurable type filters. The
 * default filters include classes that are annotated with Spring's
 * {@link wsffs.springframework.streotype.Component @Component},
 * {@link wsffs.springframework.streotype.Repository @Repository},
 * {@link wsffs.springframework.streotype.Service @Service}, or
 * {@link wsffs.springframework.streotype.Controller @Controller} stereotype.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Chris Beams
 * @see AnnotationConfigApplicationContext#scan
 * @see wsffs.springframework.streotype.Component
 * @see wsffs.springframework.streotype.Repository
 * @see wsffs.springframework.streotype.Service
 * @see wsffs.springframework.streotype.Controller
 * @since 2.5
 */
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public int scan(String... basePackages) {
        return 0;
    }
}
