package ru.job4j.it.io.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputVariables {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutputVariables.class.getName());

    public static void main(String[] args) {
        byte b = 8;
        short s = 1612;
        int i = 128600;
        long l = 2460000L;
        float f = 34348885F;
        double d = 1232345857D;
        boolean bl = true;
        char c = 'A';
        LOGGER.debug("byte : {}, short : {}, int : {}, long : {},", b, s, i, l);
        LOGGER.debug("float : {}, double : {}, boolean : {}, char : {}",  f, d, bl, c);
    }
}
