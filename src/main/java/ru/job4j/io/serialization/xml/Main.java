package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Train train = new Train();
        train.setAge(12);
        train.setName("name");
        StringWriter write = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Train.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(train, write);
        System.out.println(write);

        StringReader read = new StringReader(write.toString());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Train train1 = (Train) unmarshaller.unmarshal(read);
        System.out.println(train1);
    }
}
