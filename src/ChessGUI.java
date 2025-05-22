import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI extends JFrame {

    private static final int BOARD_SIZE = 8;
    private JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

    public ChessGUI() {
        setTitle("Xadrez Swing");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        ImageIcon peao_branco = new ImageIcon("./imgs/white_pawn.png");
        ImageIcon peao_preto = new ImageIcon("./imgs/black_pawn.png");
                // Redimensiona o ícone para 50x50


        // Cria o novo ícone com a imagem redimensionada
        peao_branco = escalaImagemIcon(peao_branco);
        peao_preto = escalaImagemIcon(peao_preto);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();
                button.setBackground((row + col) % 2 == 0 ? Color.WHITE : new Color(0, 100, 0));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFont(new Font("Arial", Font.BOLD, 30));
                button.setFocusable(false);

                // Exemplo de peças iniciais - apenas peões
                if (row == 1) {
                    button.setIcon(peao_branco);
                } else if (row == 6) {
                    button.setIcon(peao_preto);
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


   public ImageIcon escalaImagemIcon(ImageIcon icon){
        Image img = icon.getImage(); // Obtém a imagem
        Image novaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensiona a imagem
        return new ImageIcon(novaImg);
   }
}
