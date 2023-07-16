package testApp;

import wsffs.springframework.streotype.Component;

@Component
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
}
