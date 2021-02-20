package ru.job4j.ood.srp.company_reports.intefaces.implement;

import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.model.Employer;

import java.util.Comparator;

public class ReportHR implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;

    }

    @Override
    public String generate() {
        StringBuilder report = new StringBuilder();
        report.append("Name - salary;").append(System.lineSeparator());
        store.findByDepartment("HR").stream()
                .sorted(Comparator.comparing(Employer::getSalary).reversed())
                .map(employer -> String.format(
                        "%s - %s;", employer.getName(), employer.getSalary()) + System.lineSeparator())
                .forEach(report::append);
        return report.toString();
    }
}
