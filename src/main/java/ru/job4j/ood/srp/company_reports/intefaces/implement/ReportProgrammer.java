package ru.job4j.ood.srp.company_reports.intefaces.implement;

import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.model.Employer;

import java.text.SimpleDateFormat;

public class ReportProgrammer implements Report {

    private static final SimpleDateFormat PATTERN_OF_DATA_FOR_CONVERT =
            new SimpleDateFormat("dd.MM.yyyy");
    private Store store;

    public ReportProgrammer(Store store) {
        this.store = store;
    }

    @Override
    public String generate() {
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
        for (Employer employer : store.findByDepartment("developer")) {
            result.append("  <h1>").append(employer.getName()).append("</h1>").append(System.lineSeparator())
                    .append("   <p>").append(
                            PATTERN_OF_DATA_FOR_CONVERT.format(employer.getHired().getTime())
            ).append("</p>").append(System.lineSeparator())
                    .append("   <p>").append(
                            employer.getFired() != null ?
                                    PATTERN_OF_DATA_FOR_CONVERT.format(employer.getFired().getTime()) : null
            ).append("</p>").append(System.lineSeparator())
                    .append("   <p>").append(employer.getSalary()).append("</p>").append(System.lineSeparator());
        }
        result.append(" </body>").append(System.lineSeparator());
        result.append("</html>");
        return result.toString();
    }
}
