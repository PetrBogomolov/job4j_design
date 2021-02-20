package ru.job4j.ood.srp.company_reports;

import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.implement.GetDataForReport;
import ru.job4j.ood.srp.company_reports.intefaces.implement.ReportAccounting;
import ru.job4j.ood.srp.company_reports.intefaces.implement.ReportHR;
import ru.job4j.ood.srp.company_reports.intefaces.implement.ReportProgrammer;

public class Run {
    public static void main(String[] args) {
        Report hr = new ReportHR(new GetDataForReport("reports.properties"));
        Report accounting = new ReportAccounting(new GetDataForReport("reports.properties"));
        Report programmer = new ReportProgrammer(new GetDataForReport("reports.properties"));
        System.out.println(hr.generate());
        System.out.println(accounting.generate());
        System.out.println(programmer.generate());
    }
}
