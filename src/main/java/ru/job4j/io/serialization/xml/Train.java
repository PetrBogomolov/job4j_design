package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "train")
@XmlRootElement
public class Train {
    private String name;
    private int age;

    public Train() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Train{"
               + "name='" + name + '\''
               + ", age=" + age
               + '}';
    }
}
