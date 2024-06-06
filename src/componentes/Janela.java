package componentes;

import javax.swing.*;
import java.awt.*;

public class Janela extends JFrame {
        public Janela(String title) {
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(10,10,700,600);
            setLayout(null);
            getContentPane().setBackground(Color.WHITE);
            setLocationRelativeTo(null);
            setResizable(false);
    }

    public void add(ImageIcon iconeAmpliado) {
    }
}