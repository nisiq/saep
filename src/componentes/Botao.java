package componentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Botao extends JButton {
    public Object AddActionListener;

    public Botao(String text){
        super(text);
        setBackground(Color.decode("#00008b"));
        setForeground(Color.WHITE);
    }

    public void AddActionListener(ActionListener actionListener) {
    }
}