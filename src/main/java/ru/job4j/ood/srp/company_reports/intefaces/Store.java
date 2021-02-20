package ru.job4j.ood.srp.company_reports.intefaces;

import ru.job4j.ood.srp.company_reports.model.Employer;

import java.util.List;

public interface Store {

    List<Employer> findByDepartment(String department);
}
