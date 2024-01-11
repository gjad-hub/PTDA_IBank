package pt.ua.ibank.utilities;

import java.util.Random;

public class EntityRefGenerator {

    public static int generateEnt() {
        Random random = new Random();
        StringBuilder entBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            entBuilder.append(random.nextInt(10));
        }

        return Integer.parseInt("2" + entBuilder.toString());
    }

    public static int generateRef() {
        Random random = new Random();
        StringBuilder refBuilder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            refBuilder.append(random.nextInt(10));
        }

        return Integer.parseInt(refBuilder.toString());
    }
}
