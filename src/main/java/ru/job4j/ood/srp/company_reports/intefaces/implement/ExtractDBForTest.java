package ru.job4j.ood.srp.company_reports.intefaces.implement;

import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.model.Employer;

import java.util.List;

public class ExtractDBForTest implements Store {

    private Store getDataForTest;

    public ExtractDBForTest(String fileProperties) {
        getDataForTest = new GetDataForReport(fileProperties);
    }

    @Override
    public List<Employer> findByDepartment(String department) {
        return getDataForTest.findByDepartment(department);
    }
}
