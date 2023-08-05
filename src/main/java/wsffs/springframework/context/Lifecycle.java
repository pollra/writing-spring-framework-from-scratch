package wsffs.springframework.context;

public interface Lifecycle {

    void start() throws Exception;

    void stop();

    boolean isRunning();
}
