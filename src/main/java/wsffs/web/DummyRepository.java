package wsffs.web;

import wsffs.springframework.beans.annotation.Component;

@Component
public class DummyRepository {

    public DummyObject getDummy() {
        return new DummyObject(1L, "pollra", "pollra@gmail.com");
    }
}
