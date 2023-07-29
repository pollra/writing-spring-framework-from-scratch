package wsffs.app.controller;

import wsffs.springframework.context.Lifecycle;
import wsffs.springframework.streotype.Component;

@Component
public class TestLifecycle implements Lifecycle {

    @Override
    public void start() {
        System.out.println("Hi");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
