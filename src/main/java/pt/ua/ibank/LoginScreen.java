package pt.ua.ibank;

import pt.ua.ibank.interfaces.LoginClientInterface;

public class LoginScreen {

    public static void main(String[] args) {
        LoginClientInterface lclient = new LoginClientInterface();
        lclient.setVisible(true);
    }
}
