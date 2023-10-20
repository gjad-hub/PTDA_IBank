package pt.ua.ibank;

import pt.ua.ibank.interfaces.LoginClientInterface;

public class LoginScreen {

    public static void main(String[] args) {
        LoginClientInterface main = new LoginClientInterface();
        main.setVisible(true);
    }
}
