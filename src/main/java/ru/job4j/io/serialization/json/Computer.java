package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Computer {
    private final boolean laptop;
    private final String model;
    private final int additionalConnectors;
    private final SystemBlok systemBlok;
    private final String[] externalHardware;

    public Computer(boolean laptop, String model, int additionalConnectors,
                    SystemBlok systemBlok, String[] externalHardware) {
        this.laptop = laptop;
        this.model = model;
        this.additionalConnectors = additionalConnectors;
        this.systemBlok = systemBlok;
        this.externalHardware = externalHardware;
    }

    public boolean isLaptop() {
        return laptop;
    }

    public String getModel() {
        return model;
    }

    public int getAdditionalConnectors() {
        return additionalConnectors;
    }

    public SystemBlok getSystemBlok() {
        return systemBlok;
    }

    public String[] getExternalHardware() {
        return externalHardware;
    }

    @Override
    public String toString() {
        return "Computer: " + System.lineSeparator()
               + "laptop - " + laptop + System.lineSeparator()
               + "model - " + model + '\'' + System.lineSeparator()
               + "additionalConnectors - " + additionalConnectors + System.lineSeparator()
               + systemBlok + System.lineSeparator()
               + "externalHardware - " + Arrays.toString(externalHardware) + System.lineSeparator();
    }
}
