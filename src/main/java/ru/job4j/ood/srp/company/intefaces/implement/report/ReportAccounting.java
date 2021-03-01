package ru.job4j.ood.srp.company.intefaces.implement.report;

import ru.job4j.ood.srp.company.intefaces.Store;
import ru.job4j.ood.srp.company.intefaces.Report;
import java.text.SimpleDateFormat;

public class ReportAccounting implements Report {

    private static final SimpleDateFormat PATTERN_OF_DATA_FOR_CONVERT =
            new SimpleDateFormat("dd.MM.yyyy");

    private final Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(String department) {
        StringBuilder report = new StringBuilder();
        report.append("Name; Hired; Fired; Salary; Department;").append(System.lineSeparator());
        store.findByDepartment(department).stream()
                .map(employer -> String.format(
                        "%s; %s; %s; %s; %s",
                        employer.getName(),
                        PATTERN_OF_DATA_FOR_CONVERT.format(employer.getHired().getTime()),
                        employer.getFired() != null
                        ? PATTERN_OF_DATA_FOR_CONVERT.format(employer.getFired().getTime()) : null,
                        (int) employer.getSalary(),
                        employer.getDepartment() + System.lineSeparator()))
                .forEach(report::append);
        return report.toString();
    }
}
