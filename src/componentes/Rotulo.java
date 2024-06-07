package componentes;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.RED;

public class Rotulo extends JLabel {
    public Rotulo(){
        this.setBounds(100, 100, 300, 200);
        this.setForeground(new Color(0xAD, 0xD8, 0xE6)); // Azul claro (#ADD8E6)
    }

    public void getText(String testre) {
    }
}