package wsffs.springframework.boot.web.servlet.context;

import wsffs.springframework.context.Lifecycle;

public class WebServerStartStopLifecycle implements Lifecycle {

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
