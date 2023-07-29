package wsffs.web;

import wsffs.springframework.beans.annotation.Component;

@Component
public class DummyService {

    private final DummyRepository dummyRepository;

    public DummyService(DummyRepository dummyRepository) {
        this.dummyRepository = dummyRepository;
    }

    public DummyObject get() {
        return dummyRepository.getDummy();
    }
}
