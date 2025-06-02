package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class GUI extends JFrame {

    private static final int TILE_SIZE = 80;
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private Feld[][] board = new Feld[ROWS][COLS];

    private void initBoard() {
        // Startaufstellung mit Figur-Objekten
        String[][] start = {
                {"\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265D", "\u265E", "\u265C"},
                {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"},
                {"\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2657", "\u2658", "\u2656"},
        };
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String s = start[row][col];
                Figur figur = null;
                if (!s.isEmpty()) {
                    Figur.Farbe farbe = (row < 2) ? Figur.Farbe.SCHWARZ : Figur.Farbe.WEISS;
                    figur = new Figur(s, farbe);
                }
                board[row][col] = new Feld(row, col, figur);
            }
        }
    }

    public GUI() {
        setTitle("Schachbrett mit korrekten Figurenfarben");
        setSize(TILE_SIZE * COLS, TILE_SIZE * ROWS);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initBoard();
        setLayout(new GridLayout(ROWS, COLS));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JPanel cell = new JPanel(new BorderLayout());
                boolean isLight = (row + col) % 2 == 0;
                Color bgColor = isLight ? Color.LIGHT_GRAY : Color.DARK_GRAY;
                cell.setBackground(bgColor);

                Feld feld = board[row][col];
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
                    // Hier kann später Spiellogik für Züge ergänzt werden
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
