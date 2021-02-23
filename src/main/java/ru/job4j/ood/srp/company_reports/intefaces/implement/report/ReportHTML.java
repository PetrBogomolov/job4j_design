package ru.job4j.ood.srp.company_reports.intefaces.implement.report;

import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.model.Employer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportHTML implements Report {
    private final Store store;

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(String nameDepartment) {
        StringBuilder result = new StringBuilder();
        result.append(
                "<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01//EN http://www.w3.org/TR/html4/strict.dtd>"
                        + System.lineSeparator()
                        + "<html>" + System.lineSeparator()
                        + " <head>" + System.lineSeparator()
                        + "  <title>Employee</title>" + System.lineSeparator()
                        + " </head>" + System.lineSeparator()
                        + " <body>" + System.lineSeparator()
        );
        for (Employer employer : store.findByDepartment(nameDepartment)) {
            result.append("  <h1>").append(employer.getName()).append("</h1>").append(System.lineSeparator())
                    .append("   <p>").append(
                    convertCalendarToString(employer.getHired())
            ).append("</p>").append(System.lineSeparator())
                    .append("   <p>").append(
                    convertCalendarToString(employer.getFired()) != null ?
                           convertCalendarToString(employer.getFired()) : null
            ).append("</p>").append(System.lineSeparator())
                    .append("   <p>").append(employer.getSalary()).append("</p>").append(System.lineSeparator());
        }
        result.append(" </body>").append(System.lineSeparator());
        result.append("</html>");
        return result.toString();
    }

    private String convertCalendarToString(Calendar calendar) {
        String date = null;
        SimpleDateFormat pattern = new SimpleDateFormat("dd.MM.yyyy");
        if (calendar != null) {
            date = pattern.format(calendar.getTime());
        }
        return date;
    }
}
