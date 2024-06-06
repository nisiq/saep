package application;

import interfaces.Login;

public class Sistema {
    public static void main(String[] args) {
        // Criar uma instância da classe Login e tornar a janela visível
        Login login = new Login();
        login.mostrarJanela();
    }
}
