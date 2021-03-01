package ru.job4j.ood.srp.company.intefaces;

import ru.job4j.ood.srp.company.model.Employer;

import java.util.List;

public interface Store {

    List<Employer> findByDepartment(String department);
}
