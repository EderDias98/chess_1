import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private static final int BOARD_SIZE = 8;
    private JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

    public Main() {
        setTitle("Xadrez Swing");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();
                button.setBackground((row + col) % 2 == 0 ? Color.WHITE : new Color(0, 100, 0));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFont(new Font("Arial", Font.BOLD, 30));

                // Exemplo de peças iniciais - apenas peões
                if (row == 1) {
                    button.setText("♟");
                } else if (row == 6) {
                    button.setText("♙");
                }

                button.addActionListener(new ButtonClickListener(row, col));
                board[row][col] = button;
                add(button);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            System.out.println("Clique em: (" + row + ", " + col + ")");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
