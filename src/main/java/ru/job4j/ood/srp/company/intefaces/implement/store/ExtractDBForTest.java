package ru.job4j.ood.srp.company.intefaces.implement.store;

import ru.job4j.ood.srp.company.intefaces.Store;
import ru.job4j.ood.srp.company.model.Employer;

import java.util.List;

public class ExtractDBForTest implements Store {

    private final Store getDataForTest;

    public ExtractDBForTest(String fileProperties) {
        getDataForTest = new GetDataForReport(fileProperties);
    }

    @Override
    public List<Employer> findByDepartment(String department) {
        return getDataForTest.findByDepartment(department);
    }
}
