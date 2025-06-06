package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private GSchachbrett GSchachbrett;
    private int dragStartRow = -1;
    private int dragStartCol = -1;
    private int dragCurrentX = -1;
    private int dragCurrentY = -1;
    private GFigur draggedFigur = null;

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
        GSchachbrett = new GSchachbrett();
        setContentPane(new BoardPanel());
        setVisible(true);
    }

    private class BoardPanel extends JPanel implements MouseListener, MouseMotionListener {
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
                    GFeld feld = GSchachbrett.getFeld(row, col);
                    GFigur figur = feld.getFigur();
                    if (figur != null && (draggedFigur == null || row != dragStartRow || col != dragStartCol)) {
                        g.setColor(figur.getFarbe() == GFigur.Farbe.WEISS ? Color.WHITE : Color.BLACK);
                        g.setFont(new Font("Serif", Font.PLAIN, 36));
                        g.drawString(figur.getSymbol(), col * TILE_SIZE + TILE_SIZE / 3, row * TILE_SIZE + 2 * TILE_SIZE / 3);
                    }
                }
            }
            // Ziehende Figur zeichnen
            if (draggedFigur != null && dragCurrentX >= 0 && dragCurrentY >= 0) {
                g.setColor(draggedFigur.getFarbe() == GFigur.Farbe.WEISS ? Color.WHITE : Color.BLACK);
                g.setFont(new Font("Serif", Font.PLAIN, 36));
                g.drawString(draggedFigur.getSymbol(), dragCurrentX - TILE_SIZE / 2, dragCurrentY + TILE_SIZE / 3);
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                GFeld feld = GSchachbrett.getFeld(row, col);
                if (feld.getFigur() != null) {
                    dragStartRow = row;
                    dragStartCol = col;
                    draggedFigur = feld.getFigur();
                    dragCurrentX = e.getX();
                    dragCurrentY = e.getY();
                    repaint();
                }
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (draggedFigur != null) {
                int col = e.getX() / TILE_SIZE;
                int row = e.getY() / TILE_SIZE;
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
                    GSchachbrett.moveFigur(dragStartRow, dragStartCol, row, col);
                }
                draggedFigur = null;
                dragStartRow = dragStartCol = -1;
                dragCurrentX = dragCurrentY = -1;
                repaint();
            }
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            if (draggedFigur != null) {
                dragCurrentX = e.getX();
                dragCurrentY = e.getY();
                repaint();
            }
        }
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
