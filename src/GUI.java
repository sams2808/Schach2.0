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
                JPanel feld = new JPanel(new BorderLayout());
                if ((i + j) % 2 == 0) {
                    feld.setBackground(Color.WHITE);
                } else {
                    feld.setBackground(Color.DARK_GRAY);
                }
                // Schwarzes Pferd (Springer) auf Feld b8 (i==0, j==1)
                if (i == 0 && j == 1) {
                    try {
                        // Korrekte Pfadangabe für Ressourcen im Klassenpfad
                        ImageIcon icon = new ImageIcon(getClass().getResource("src/figuren/img.png"));
                        JLabel label = new JLabel(icon);
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);
                        feld.add(label, BorderLayout.CENTER);
                    } catch (Exception e) {
                        e.printStackTrace(); // Fehlerausgabe für Debugging
                    }
                }
                panel.add(feld);
            }
        }
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}
