package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class GUI extends JFrame {

    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private Schachbrett schachbrett;

    public GUI() {
        setTitle("Schach");
        setSize(TILE_SIZE * COLS, TILE_SIZE * ROWS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Startmenü als Dialog anzeigen
        Object[] options = {"Laden", "Starten", "Beenden"};
        int auswahl = JOptionPane.showOptionDialog(
                this,
                "Willkommen zum Schachspiel! Was möchtest du tun?",
                "Start-Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]
        );
        if (auswahl == 0) {
            System.out.println("Laden gewählt");
            // Ladefunktionalität hier ergänzen
        } else if (auswahl == 1) {
            System.out.println("Starten gewählt");
            // Spielstart, einfach fortfahren
        } else if (auswahl == 2) {
            System.exit(0);
        }


        JMenu spielMenu = new JMenu("Spiel");
        JMenuItem speichernItem = new JMenuItem("Speichern");
        JMenuItem neustartItem = new JMenuItem("Neustarten");
        spielMenu.add(speichernItem);
        spielMenu.add(neustartItem);
        setContentPane(spielMenu);
        schachbrett = new Schachbrett();
        setLayout(new GridLayout(ROWS, COLS));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JPanel cell = new JPanel(new BorderLayout());
                boolean isLight = (row + col) % 2 == 0;
                Color bgColor = isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY;
                cell.setBackground(bgColor);

                Feld feld = schachbrett.getFeld(row, col);
                Figur figur = feld.getFigur();
                JLabel label = new JLabel(figur != null ? figur.getSymbol() : "", SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.PLAIN, 36));
                if (figur != null) {
                    label.setForeground(figur.getFarbe() == Figur.Farbe.WEISS ? Color.WHITE : Color.BLACK);
                }
                cell.add(label, BorderLayout.CENTER);
                final int clickedRow = row;
                final int clickedCol = col;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        System.out.println("Feld ausgewählt: Zeile " + clickedRow + ", Spalte " + clickedCol);
                    }
                });
                add(cell);
            }
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
