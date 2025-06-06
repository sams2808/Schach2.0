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
    private int dragStartRow = -1;
    private int dragStartCol = -1;
    private int dragCurrentX = -1;
    private int dragCurrentY = -1;
    private Figur draggedFigur = null;

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
                    Feld feld = GSchachbrett.getFeld(row, col);
                    Figur figur = feld.figurGeben();
                    if (figur != null && (draggedFigur == null || row != dragStartRow || col != dragStartCol)) {
                        g.setColor(figur.getFarbe() ==
