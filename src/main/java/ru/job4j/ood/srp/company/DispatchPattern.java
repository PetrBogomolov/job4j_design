package ru.job4j.ood.srp.company;

import ru.job4j.ood.srp.company.intefaces.Report;
import ru.job4j.ood.srp.company.intefaces.Store;
import ru.job4j.ood.srp.company.intefaces.implement.report.*;
import java.util.HashMap;
import java.util.Map;

public class DispatchPattern {
    private static final String TXT = "txt";
    private static final String SPECIAL_TXT_FOR_HR = "hr_txt";
    private static final String SPECIAL_TXT_FOR_ACCOUNTING = "accounting_txt";
    private static final String XML = "xml";
    private static final String JSON = "json";
    private static final String HTML = "html";
    private final Map<String, Report> formatReport = new HashMap<>();
    private final Store store;

    public DispatchPattern(Store store) {
        this.store = store;
    }

    public void init() {
        loadFormatReport(TXT, new ReportTXT(store));
        loadFormatReport(SPECIAL_TXT_FOR_HR, new ReportHR(store));
        loadFormatReport(SPECIAL_TXT_FOR_ACCOUNTING, new ReportAccounting(store));
        loadFormatReport(XML, new ReportXML(store));
        loadFormatReport(JSON, new ReportJSON(store));
        loadFormatReport(HTML, new ReportHTML(store));
    }

    public String generateReport(String department, String format) {
        Report report = formatReport.get(format);
        return report.generate(department);
    }

    private void loadFormatReport(String format, Report report) {
        formatReport.put(format, report);
    }
}
