package ru.job4j.io.serialization.json;

public class SystemBlok {
    private final String mainboard;
    private final String processor;
    private final String ram;
    private final String hardDrive;
    private final String videoCard;

    public SystemBlok(String mainboard, String processor,
                      String ram, String hardDrive, String videoCard) {
        this.mainboard = mainboard;
        this.processor = processor;
        this.ram = ram;
        this.hardDrive = hardDrive;
        this.videoCard = videoCard;
    }

    @Override
    public String toString() {
        return "SystemBlok" + System.lineSeparator()
               + "{" + System.lineSeparator()
               + "mainboard - " + mainboard + System.lineSeparator()
               + "processor - " + processor + System.lineSeparator()
               + "RAM - " + ram + System.lineSeparator()
               + "hardDrive - " + hardDrive + System.lineSeparator()
               + "videoCard - " + videoCard + System.lineSeparator()
               + '}';
    }
}
