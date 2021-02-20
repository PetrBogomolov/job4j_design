package ru.job4j.ood.srp.company_reports;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.implement.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RunCompanyReportTest {

    private Store dbForTest;

    @Before
    public void setup() {
        dbForTest = new ExtractDBForTest("reportstest.properties");
    }

    @Test
    public void whenHRReportCreateSuccessful() {
        Report hr = new ReportHR(dbForTest);
        String report = hr.generate();
        String expected = "Name - salary;"+ System.lineSeparator()
                + "nastya - 70000.0;" + System.lineSeparator()
                + "dima - 60000.0;" + System.lineSeparator();
        assertThat(report, is(expected));
    }

    @Test
    public void whenProgrammerReportCreateSuccessful() {
        Report programmer = new ReportProgrammer(dbForTest);
        String report = programmer.generate();
        String expected = "<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01//EN http://www.w3.org/TR/html4/strict.dtd>"
               + System.lineSeparator()
               + "<html>" + System.lineSeparator()
               + " <head>" + System.lineSeparator()
               + "  <title>Employee</title>" + System.lineSeparator()
               + " </head>" + System.lineSeparator()
               + " <body>" + System.lineSeparator()
               + "  <h1>misha</h1>" + System.lineSeparator()
               + "   <p>20.02.2021</p>" + System.lineSeparator()
               + "   <p>20.02.2021</p>" + System.lineSeparator()
               + "   <p>50000.0</p>" + System.lineSeparator()
               + " </body>" + System.lineSeparator()
               + "</html>";
        assertThat(report, is(expected));
    }

    @Test
    public void whenAccountingReportCreateSuccessful() {
        Report accounting = new ReportAccounting(dbForTest);
        String report = accounting.generate();
        String expected = "Name; Hired; Fired; Salary; Department;" + System.lineSeparator()
                + "roma; 20.02.2021; 20.02.2021; 80000; accounting " + System.lineSeparator();
        assertThat(report, is(expected));
    }
}