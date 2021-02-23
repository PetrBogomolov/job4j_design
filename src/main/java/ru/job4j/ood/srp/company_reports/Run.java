package ru.job4j.ood.srp.company_reports;

import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.implement.report.*;
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

    public String generateReport(Store store, String formatReport, String department) {
        String result = null;
        Report formatDoc;
        if (formatReport.equals(TXT)) {
            formatDoc = new ReportTXT(store);
            result = formatDoc.generate(department);
        }
        if (formatReport.equals(SPECIAL_TXT_FOR_HR)) {
            formatDoc = new ReportHR(store);
            result = formatDoc.generate(department);
        }
        if (formatReport.equals(SPECIAL_TXT_FOR_ACCOUNTING)) {
            formatDoc = new ReportAccounting(store);
            result = formatDoc.generate(department);
        }
        if (formatReport.equals(JSON)) {
            formatDoc = new ReportJSON(store);
            result = formatDoc.generate(department);
        }
        if (formatReport.equals(XML)) {
            formatDoc = new ReportXML(store);
            result = formatDoc.generate(department);
        }
        if (formatReport.equals(HTML)) {
            formatDoc = new ReportHTML(store);
            result = formatDoc.generate(department);
        }
        return result;
    }

    public static void main(String[] args) {
        Run run = new Run();
        Store getDataForReport = new GetDataForReport("reports.properties");
        System.out.println(run.generateReport(getDataForReport, JSON, HR));
        System.out.println(run.generateReport(getDataForReport, HTML, DEVELOPER));
        System.out.println(run.generateReport(getDataForReport, XML, ACCOUNTING));
    }
}
