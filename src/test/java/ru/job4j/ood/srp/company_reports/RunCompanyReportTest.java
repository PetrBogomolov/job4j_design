package ru.job4j.ood.srp.company_reports;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.implement.report.*;
import ru.job4j.ood.srp.company_reports.intefaces.implement.store.ExtractDBForTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RunCompanyReportTest {

    private static final String HR = "HR";
    private static final String DEVELOPER = "developer";
    private static final String ACCOUNTING = "accounting";
    private Store dbForTest;

    @Before
    public void setup() {
        dbForTest = new ExtractDBForTest("reportstest.properties");
    }

    @Test
    public void whenSpecialHRReportCreateSuccessful() {
        Report hr = new ReportHR(dbForTest);
        String report = hr.generate(HR);
        String expected = "Name - salary;" + System.lineSeparator()
                + "nastya - 70000.0;" + System.lineSeparator()
                + "dima - 60000.0;" + System.lineSeparator();
        assertThat(report, is(expected));
    }

    @Test
    public void whenJSONHRReportCreateSuccessful() {
        Report hrJson = new ReportJSON(dbForTest);
        String report = hrJson.generate(HR);
        String expected = "{" +
                "\"id\":3," +
                "\"name\":\"nastya\"," +
                "\"hired\":{" +
                "\"year\":2020," +
                "\"month\":11," +
                "\"dayOfMonth\":13," +
                "\"hourOfDay\":0," +
                "\"minute\":0," +
                "\"second\":0" +
                "}," +
                "\"fired\":{" +
                "\"year\":2020," +
                "\"month\":11," +
                "\"dayOfMonth\":13," +
                "\"hourOfDay\":0," +
                "\"minute\":0," +
                "\"second\":0" +
                "}," +
                "" +
                "\"salary\":70000.0," +
                "\"department\":\"HR\"" +
                "}";
        assertThat(report, is(expected));
    }

    @Test
    public void whenProgrammerHTMLReportCreateSuccessful() {
        Report programmer = new ReportHTML(dbForTest);
        String report = programmer.generate(DEVELOPER);
        String expected = "<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01//EN http://www.w3.org/TR/html4/strict.dtd>"
                + System.lineSeparator()
                + "<html>" + System.lineSeparator()
                + " <head>" + System.lineSeparator()
                + "  <title>Employee</title>" + System.lineSeparator()
                + " </head>" + System.lineSeparator()
                + " <body>" + System.lineSeparator()
                + "  <h1>misha</h1>" + System.lineSeparator()
                + "   <p>13.12.2020</p>" + System.lineSeparator()
                + "   <p>13.12.2020</p>" + System.lineSeparator()
                + "   <p>50000.0</p>" + System.lineSeparator()
                + " </body>" + System.lineSeparator()
                + "</html>";
        assertThat(report, is(expected));
    }

    /*
     *  хотел использовать платформо-независимый метод System.lineSeparator(),
     *  но он, помимо переноса строки, вызывает еще и возврат коретки \r
     *  среда на это ругается
     */
    @Test
    public void whenProgrammerXMLReportCreateSuccessful() {
        Report programmer = new ReportXML(dbForTest);
        String report = programmer.generate(DEVELOPER);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employer>\n" +
                "    <department>developer</department>\n" +
                "    <fired>2020-12-13T00:00:00+03:00</fired>\n" +
                "    <hired>2020-12-13T00:00:00+03:00</hired>\n" +
                "    <id>1</id>\n" +
                "    <name>misha</name>\n" +
                "    <salary>50000.0</salary>\n" +
                "</employer>\n";
        assertThat(report, is(expected));
    }

    @Test
    public void whenAccountingReportCreateSuccessful() {
        Report accounting = new ReportAccounting(dbForTest);
        String report = accounting.generate(ACCOUNTING);
        String expected = "Name; Hired; Fired; Salary; Department;" + System.lineSeparator()
                + "roma; 13.12.2020; 13.12.2020; 80000; accounting" + System.lineSeparator();
        assertThat(report, is(expected));
    }
}
