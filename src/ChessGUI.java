import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class ChessGUI extends JFrame {




    public ChessGUI() {
        this.setTitle("Xadrez Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        

        ChessBoardPanel tabuleiro = new ChessBoardPanel();
        tabuleiro.setPreferredSize(new Dimension(600, 600));
        this.add(tabuleiro, gbc);

        this.setVisible(true);
        tabuleiro.colocarPecas();
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int w = getContentPane().getWidth();
                int h = getContentPane().getHeight();
                Dimension tamanho = tabuleiro.gridPanel.getSize();
                int square =(int) (Math.min(w, h)/1.3); // mantém proporção

                tabuleiro.setPreferredSize(new Dimension(square, square));
                tabuleiro.atualizarTamanhoGrid(square, square);
                tabuleiro.atualizarPecas(square/8);
                tamanho = tabuleiro.gridPanel.getSize();
                getContentPane().revalidate(); // força relayout
          
            }
        });
           
    }

}
