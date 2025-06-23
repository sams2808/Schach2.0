package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private GSchachbrett GSchachbrett;
    private Schachbrett logikBrett = new Schachbrett();

    public GUI() {
        setTitle("Schach");
        setSize(TILE_SIZE * COLS, TILE_SIZE * ROWS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Startmenü als Dialog anzeigen
        while (true) {
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
                JOptionPane.showMessageDialog(this, "Lade Spielstand... (Funktion noch nicht implementiert)", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else if (auswahl == 1) {
                break;
            } else if (auswahl == 2) {
                System.exit(0);
            }
        }
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Spiel");
        JMenuItem resetItem = new JMenuItem("Neues Spiel");
        menu.add(resetItem);
        menuBar.add(menu);
        setJMenuBar(menuBar); // Menüleiste korrekt setzen
        GSchachbrett = new GSchachbrett();

        setContentPane(new BoardPanel());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }

    private class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {
        private int selectedRow = -1;
        private int selectedCol = -1;

        public BoardPanel() {
            setPreferredSize(new Dimension(TILE_SIZE * COLS, TILE_SIZE * ROWS));
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    boolean isLight = (row + col) % 2 == 0;
                    g.setColor(isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    Feld feld = GSchachbrett.getFeld(row, col);
                    Figur figur = feld.figurGeben();
                    if (figur != null) {
                        g.setColor(figur.getFarbe() == Figur.Farbe.WEISS ? Color.WHITE : Color.BLACK);
                        g.setFont(new Font("Serif", Font.PLAIN, 36));
                        g.drawString(figur.getSymbol(), col * TILE_SIZE + TILE_SIZE / 3, row * TILE_SIZE + 2 * TILE_SIZE / 3);
                    }
                    // Auswahl hervorheben
                    if (row == selectedRow && col == selectedCol) {
                        g.setColor(Color.RED);
                        g.drawRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        g.drawRect(col * TILE_SIZE + 1, row * TILE_SIZE + 1, TILE_SIZE - 2, TILE_SIZE - 2);
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;
            if (row < 0 || row >= ROWS || col < 0 || col >= COLS) return;
            Feld feld = GSchachbrett.getFeld(row, col);
            if (selectedRow == -1 && selectedCol == -1) {
                // Auswahl einer Figur
                if (feld.figurGeben() != null) {
                    selectedRow = row;
                    selectedCol = col;
                    repaint();
                }
            } else {
                // Zielauswahl und Zug ausführen
                GSchachbrett.moveFigur(selectedRow, selectedCol, row, col);
                // Logikbrett synchronisieren
                String von = FeldNameFromCoords(selectedRow, selectedCol);
                String nach = FeldNameFromCoords(row, col);
                Feld logikVon = getLogikFeldByName(von);
                Feld logikNach = getLogikFeldByName(nach);
                if (logikVon != null && logikNach != null && logikVon.figurGeben() != null) {
                    logikVon.figurGeben().Ziehen(logikNach);
                    System.out.println("[Logik] Zug von " + von + " nach " + nach);
                }
                selectedRow = selectedCol = -1;
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}

        // Hilfsmethode: Koordinaten zu Feldname
        private String FeldNameFromCoords(int row, int col) {
            char colChar = (char)('A' + col);
            int rowNum = 8 - row;
            return "" + colChar + rowNum;
        }

        // Hilfsmethode: Logikfeld nach Name suchen
        private Feld getLogikFeldByName(String name) {
            for (Feld f : logikBrett.felder) {
                if (f.getName().equalsIgnoreCase(name)) return f;
            }
            return null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
