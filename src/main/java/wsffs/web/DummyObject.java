package wsffs.web;

public class DummyObject {

    public Long id;
    public String name;
    public String email;

    public DummyObject(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "DummyObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
