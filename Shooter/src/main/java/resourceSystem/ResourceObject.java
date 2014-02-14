package resourceSystem;

import java.io.Serializable;

/**
 * Created by alexandr on 29.01.14.
 */
public class ResourceObject implements Serializable {

    private static final long serialVersionUID = -3895203507200457732L;
    private String name;
    private int age;

    public ResourceObject() {
        this.name = "Nobody";
        this.age = 0;
    }

    public ResourceObject(String name, int age) {
        this.setAge(age);
        this.setName(name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Name: " + name + " age: " + age;
    }
}
