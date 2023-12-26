package pt.ua.ibank.utilities;

import java.util.Random;

public class IbanGenerator {

    public static String generateRandomIban() {
        String accountNumber = generateRandomAccountNumber();
        String iban = generateIban(accountNumber);

        return iban;
    }

    private static String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();

        for (int i = 0; i < 19; i++) {
            accountNumberBuilder.append(random.nextInt(10));
        }

        return accountNumberBuilder.toString();
    }

    private static String generateIban(String accountNumber) {
        return "PT" + "5000" + accountNumber;
    }
}
