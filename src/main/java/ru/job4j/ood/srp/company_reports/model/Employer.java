package ru.job4j.ood.srp.company_reports.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@XmlType(name = "employer")
@XmlRootElement
public class Employer {
    private int id;
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;
    private String department;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employer employer = (Employer) o;
        return id == employer.id && Double.compare(employer.salary, salary) == 0
                && Objects.equals(name, employer.name)
                && Objects.equals(hired, employer.hired)
                && Objects.equals(fired, employer.fired)
                && Objects.equals(department, employer.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hired, fired, salary, department);
    }

    @Override
    public String toString() {
        return "Employer:" + System.lineSeparator()
                + "id - " + id + System.lineSeparator()
                + "name - " + name + System.lineSeparator()
                + "hired - " + convertCalendarToString(getHired()) + System.lineSeparator()
                + "fired - " + convertCalendarToString(getFired()) + System.lineSeparator()
                + "salary - " + salary + System.lineSeparator()
                + "department - " + department + ';' + System.lineSeparator();
    }

    private String convertCalendarToString(Calendar calendar) {
        String date = null;
        SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy");
        if (calendar != null) {
            date = pattern.format(calendar.getTime());
        }
        return date;
    }
}
