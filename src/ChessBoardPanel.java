import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class ChessBoardPanel extends JLayeredPane {
    private final int rows = 8;
    private final int cols = 8;
    public final JPanel gridPanel;
    private int squareSize;
    JPanel[][] casas = new JPanel[rows][cols];
    private ArrayList<PieceLabel> pieceLabels = new ArrayList<>();
  

    public ChessBoardPanel() {
          setLayout(null);

        squareSize = 600 / 8;

        gridPanel = new JPanel(new GridLayout(rows, cols));
        gridPanel.setBounds(0, 0, 600, 600); // posição e tamanho inicial do tabuleiro

        this.add(gridPanel, JLayeredPane.DEFAULT_LAYER);

        // Preenche o tabuleiro com as casas

        for (int i = 0; i < rows * cols; i++) {
            JPanel casa = new JPanel();
            int x = i / cols;
            int y = i % cols;

            if ((x + y) % 2 == 0)
                casa.setBackground(Color.WHITE);
            else
                casa.setBackground(Color.DARK_GRAY);

            casas[x][y] = casa;
            gridPanel.add(casa);
        }

        // Aguarda o layout ser feito antes de adicionar as peças


        

}


 
// Adiciona uma peça exemplo (peao preta)

         // coloque uma imagem real aqui


        // Atualiza posição ao redimensionar
public void colocarPecas(){

            ImageIcon peaoBranco = new ImageIcon("imgs/white_pawn.png");
            ImageIcon peaoPreto = new ImageIcon("imgs/black_pawn.png");

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    if (y == 1 || y == 6) {
                        PieceLabel peao = new PieceLabel(y == 1 ? peaoBranco : peaoPreto);
                        peao.updateSize(45);

                        // Converte posição da casa para o coordenadas do JLayeredPane
                        Point posicao = SwingUtilities.convertPoint(gridPanel, casas[y][x].getLocation(), this);
                        peao.setLocation(posicao.x,posicao.y);

                        this.add(peao, JLayeredPane.DRAG_LAYER);
                        pieceLabels.add(peao);
                    }
                }
            }}





    

    public void atualizarTamanhoGrid(int w, int h){
        this.gridPanel.setBounds(0,0, w,h);
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    public void atualizarPecas(int square){
        SwingUtilities.invokeLater(() -> {
    Component[] casas = gridPanel.getComponents();
            int j=0;
    for (int i = 0; i < casas.length; i++) {
        Component casa = casas[i];

        // Calcula posição da casa relativa ao ChessBoardPanel
        Point posicaoAbsoluta = SwingUtilities.convertPoint(gridPanel, casa.getLocation(), this);

        // Exemplo: reposicionar peça se houver
   
            PieceLabel peca = this.pieceLabels.get(j);
            j++;
            
            peca.updateSize(45); // pequeno padding
            peca.setLocation(posicaoAbsoluta.x, posicaoAbsoluta.y);
        
    }
});
        }
    
    public void atualizaTamanhoPeca(int w, int h){
        for (int i = 0; i < rows * cols; i++) {
            int x = i % cols;
            int y = i / cols;
            
        }

    }

    
}
