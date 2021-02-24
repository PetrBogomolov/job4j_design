package ru.job4j.ood.srp.company_reports.intefaces.implement.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.model.Employer;

public class ReportJSON implements Report {

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(String nameDepartment) {
        Gson gson = new GsonBuilder().create();
        StringBuilder result = new StringBuilder();
        for (Employer employer : store.findByDepartment(nameDepartment)) {
            result.append(gson.toJson(employer)).append(System.lineSeparator());
        }
        return result.toString();
    }
}
