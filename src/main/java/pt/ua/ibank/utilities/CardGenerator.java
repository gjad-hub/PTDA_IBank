package pt.ua.ibank.utilities;

import java.util.Random;

public class CardGenerator {

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            accountNumberBuilder.append(random.nextInt(10));
        }

        return "4001" + accountNumberBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateCardNumber());
    }
}
