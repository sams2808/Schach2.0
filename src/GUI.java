

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public GUI() {
        setTitle("Schachbrett");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel feld = new JPanel();
                if ((i + j) % 2 == 0) {
                    feld.setBackground(Color.WHITE);
                } else {
                    feld.setBackground(Color.DARK_GRAY);
                }
                panel.add(feld);
            }
        }
        add(panel);
    }

    // ...optional: main-Methode zum Testen...
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}
