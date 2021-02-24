package ru.job4j.ood.srp.company_reports;

import ru.job4j.ood.srp.company_reports.intefaces.implement.store.GetDataForReport;

public class Run {
    private static final String HR = "HR";
    private static final String DEVELOPER = "developer";
    private static final String ACCOUNTING = "accounting";
    private static final String TXT = "txt";
    private static final String SPECIAL_TXT_FOR_HR = "hr_txt";
    private static final String SPECIAL_TXT_FOR_ACCOUNTING = "accounting_txt";
    private static final String XML = "xml";
    private static final String JSON = "json";
    private static final String HTML = "html";

    public static void main(String[] args) {
        DispatchPattern generate = new DispatchPattern(new GetDataForReport("reports.properties"));
        generate.init();
        System.out.println(generate.generateReport(HR, SPECIAL_TXT_FOR_HR));
        System.out.println(generate.generateReport(DEVELOPER, TXT));
        System.out.println(generate.generateReport(DEVELOPER, JSON));
    }
}
