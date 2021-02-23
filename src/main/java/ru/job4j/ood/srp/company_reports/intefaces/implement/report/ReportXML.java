package ru.job4j.ood.srp.company_reports.intefaces.implement.report;

import ru.job4j.ood.srp.company_reports.intefaces.Report;
import ru.job4j.ood.srp.company_reports.intefaces.Store;
import ru.job4j.ood.srp.company_reports.model.Employer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class ReportXML implements Report {

    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(String nameDepartment) {
        StringWriter result = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Employer.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            for (Employer employer : store.findByDepartment(nameDepartment)) {
                marshaller.marshal(employer, result);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
