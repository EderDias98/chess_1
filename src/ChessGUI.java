import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class ChessGUI extends JFrame {




    public ChessGUI() {
        setTitle("Xadrez Swing");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new chessPainel());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
       

   
    }
    public ImageIcon escalaImagemIcon(ImageIcon icon){
        Image img = icon.getImage(); // Obtém a imagem
        Image novaImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Redimensiona a imagem
        return new ImageIcon(novaImg);
   }

    public class chessPainel extends JPanel{
        private static final int BOARD_SIZE = 8;
        private static final int SQUARE_SIZE = 75;
        ImageIcon peao_branco;
        ImageIcon peao_preto;
    
        public chessPainel() {
            peao_branco = new ImageIcon("./imgs/white_pawn.png");
            peao_preto = new ImageIcon("./imgs/black_pawn.png");
    
            peao_branco = escalaImagemIcon(peao_branco);
            peao_preto = escalaImagemIcon(peao_preto);


        }
        // Cria o novo ícone com a imagem redimensionada
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
    
            // Percorre linhas e colunas
            for (int linha = 0; linha < BOARD_SIZE; linha++) {
                for (int coluna = 0; coluna < BOARD_SIZE; coluna++) {
    
                    // Define a cor da casa (preto/branco alternado)
                    if ((linha + coluna) % 2 == 0) {
                        g.setColor(Color.WHITE); // casa clara
                    } else {
                        g.setColor(Color.DARK_GRAY); // casa escura
                    }
    
                    // Desenha o quadrado (casa)
                    int x = coluna * SQUARE_SIZE;
                    int y = linha * SQUARE_SIZE;
                    g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

    }
   
}
