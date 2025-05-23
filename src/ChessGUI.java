import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class ChessGUI extends JFrame {




    public ChessGUI() {
        setTitle("Xadrez Swing");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        chessPainel painel = new chessPainel();

        this.add(painel,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
       

   
    }
    public ImageIcon escalaImagemIcon(ImageIcon icon){
        Image img = icon.getImage(); // Obtém a imagem
        Image novaImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH); // Redimensiona a imagem
        return new ImageIcon(novaImg);
   }

    public class chessPainel extends JPanel{
        private static final int BOARD_SIZE = 8;
      
        ImageIcon peao_branco;
        ImageIcon peao_preto;
    
        public chessPainel() {
            peao_branco = new ImageIcon("./imgs/white_pawn.png");
            peao_preto = new ImageIcon("./imgs/black_pawn.png");
    
            // peao_branco = escalaImagemIcon(peao_branco);
            // peao_preto = escalaImagemIcon(peao_preto);


        }
        // Cria o novo ícone com a imagem redimensionada
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int larguraPainel = getWidth();
            int alturaPainel = getHeight();
            int squareSize = Math.min(larguraPainel, alturaPainel) / BOARD_SIZE;
            
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
                    int offsetX = (larguraPainel - (squareSize * BOARD_SIZE)) / 2;
                    int offsetY = (alturaPainel - (squareSize * BOARD_SIZE)) / 2;
                    int squareSizePeca = squareSize/2;

                    int offsetXp = (squareSize - (squareSizePeca)) / 2;
                    int offsetYp =(squareSize - (squareSizePeca)) / 2;

                    int x = offsetX + coluna * squareSize;
                    int y = offsetY + linha * squareSize;
                    int xp = x + offsetXp;
                    int yp = y + offsetYp;
                    g.fillRect(x, y, squareSize, squareSize);
                    
                    g.drawImage(peao_branco.getImage(), xp, yp, squareSizePeca, squareSizePeca, this);
                }
            }
        }

    }
   
}
