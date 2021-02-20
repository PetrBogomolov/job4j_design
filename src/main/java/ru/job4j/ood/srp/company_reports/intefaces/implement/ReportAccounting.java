package ru.job4j.ood.srp.company_reports.intefaces.implement;

import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import java.text.SimpleDateFormat;

public class ReportAccounting implements Report {

    private static final SimpleDateFormat PATTERN_OF_DATA_FOR_CONVERT =
            new SimpleDateFormat("dd.MM.yyyy");
    private Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate() {
        StringBuilder report = new StringBuilder();
        report.append("Name; Hired; Fired; Salary; Department;").append(System.lineSeparator());
        store.findByDepartment("accounting ").stream()
                .map(employer -> String.format(
                        "%s; %s; %s; %s; %s",
                        employer.getName(),
                        PATTERN_OF_DATA_FOR_CONVERT.format(employer.getHired().getTime()),
                        PATTERN_OF_DATA_FOR_CONVERT.format(employer.getFired().getTime()),
                        (int)employer.getSalary(),
                        employer.getDepartment() + System.lineSeparator()))
                .forEach(report::append);
        return report.toString();
    }
}
