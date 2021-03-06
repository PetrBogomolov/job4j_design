package ru.job4j.ood.srp.company.intefaces.implement.report;

import ru.job4j.ood.srp.company.intefaces.Report;
import ru.job4j.ood.srp.company.intefaces.Store;

public class ReportTXT implements Report {

    private final Store store;

    public ReportTXT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(String nameDepartment) {
        StringBuilder result = new StringBuilder();
        store.findByDepartment(nameDepartment)
                .forEach(result::append);
        return result.toString();
    }
}
