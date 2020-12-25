package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final Computer myComputer = new Computer(true, "Acer Aspire A517-51G", 6,
                new SystemBlok("Acer S301LA", "Intel Core i7 8550U",
                                "DDR4, 6Gb", "HDD, 500 Gb", "NVIDIA® GeForce® MX150"),
                new String[] {"Headphones", "Columns" });
        System.out.println(myComputer);
        final Gson gson = new GsonBuilder().create();
        final String myComputerJson = gson.toJson(myComputer);
        System.out.println(myComputerJson);
        final Computer myComputerMod = gson.fromJson(myComputerJson, Computer.class);
        System.out.println(myComputerMod);

        /* JSONObject из json-строки строки */
        JSONObject jsonObjectFromLine = new JSONObject(myComputerJson);
        System.out.println(jsonObjectFromLine.toString());

        /* JSONObject напрямую методом put */
        JSONObject jsonObjectMethodPut = new JSONObject();
        jsonObjectMethodPut.put("Laptop", myComputer.isLaptop());
        jsonObjectMethodPut.put("Model", myComputer.getModel());
        jsonObjectMethodPut.put("additionalConnectors", myComputer.getAdditionalConnectors());
        jsonObjectMethodPut.put("SystemBlok", myComputer.getSystemBlok());
        jsonObjectMethodPut.put("ExternalHardware", myComputer.getExternalHardware());
        System.out.println(jsonObjectMethodPut.toString());
    }
}
