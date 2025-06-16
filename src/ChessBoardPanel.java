import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoardPanel extends JLayeredPane {
    private final int rows = 8;
    private final int cols = 8;
    public final JPanel gridPanel;
    private int squareSize;
    SquarePanel[][] casas = new SquarePanel[rows][cols];
    private ArrayList<PieceLabel> pieceLabels = new ArrayList<>();
    private Board tabu;
    public Board getTabu() {
        return tabu;
    }

    public ChessBoardPanel() {
        this.tabu = new Board();
        setLayout(null);

        squareSize = 600 / 8;

        gridPanel = new JPanel(new GridLayout(rows, cols));
        gridPanel.setBounds(0, 0, 600, 600); // posição e tamanho inicial do tabuleiro

        this.add(gridPanel, JLayeredPane.DEFAULT_LAYER);

        // Preenche o tabuleiro com as casas

        for (int i = 0; i < rows * cols; i++) {
            SquarePanel casa = new SquarePanel();
            int y = i / cols;
            int x = i % cols;

            if ((y + x) % 2 == 0) {
                casa.setBackground(Color.WHITE);
                casa.setOriginalCor(Color.WHITE);
            } else {
                casa.setBackground(Color.DARK_GRAY);
                casa.setOriginalCor(Color.DARK_GRAY);
            }
            casas[y][x] = casa;
            gridPanel.add(casa);

        }

        // Aguarda o layout ser feito antes de adicionar as peças

    }

    // Adiciona uma peça exemplo (piece preta)

    // coloque uma imagem real aqui

    // Atualiza posição ao redimensionar
    public void colocarPecas() {

        ImageIcon peaoBranco = new ImageIcon("imgs/white-pawn.png");
        ImageIcon torreBranca = new ImageIcon("imgs/white-rook.png");
        ImageIcon cavaloBranco = new ImageIcon("imgs/white-knight.png");
        ImageIcon bispoBranco = new ImageIcon("imgs/white-bishop.png");
        ImageIcon damaBranca = new ImageIcon("imgs/white-queen.png");
        ImageIcon reiBranco = new ImageIcon("imgs/white-king.png");

        ImageIcon peaoPreto = new ImageIcon("imgs/black-pawn.png");
        ImageIcon torrePreta = new ImageIcon("imgs/black-rook.png");
        ImageIcon cavaloPreto = new ImageIcon("imgs/black-knight.png");
        ImageIcon bispoPreto = new ImageIcon("imgs/black-bishop.png");
        ImageIcon damaPreta = new ImageIcon("imgs/black-queen.png");
        ImageIcon reiPreto = new ImageIcon("imgs/black-king.png");

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (y == 1 || y == 6 || y == 0 || y == 7) {
                    ImageIcon peca = null;

                    if (y == 1) {
                        peca = peaoPreto;
                    } else if (y == 6) {
                        peca = peaoBranco;
                    } else if (y == 0 || y == 7) {
                        boolean branco = (y == 7); // linha 7: branco, linha 0: preto

                        switch (x) {
                            case 0:
                            case 7:
                                peca = branco ? torreBranca : torrePreta;
                                break;
                            case 1:
                            case 6:
                                peca = branco ? cavaloBranco : cavaloPreto;
                                break;
                            case 2:
                            case 5:
                                peca = branco ? bispoBranco : bispoPreto;
                                break;
                            case 3:
                                peca = branco ? damaBranca : damaPreta;
                                break;
                            case 4:
                                peca = branco ? reiBranco : reiPreto;
                                break;
                        }
                    }

                    PieceLabel piece = new PieceLabel(peca, this);

                    SquarePanel casa = casas[y][x];
                    piece.updateSize((int) (casa.getWidth() * 0.8));
                    casa.setPiece(true);
                    // Converte posição da casa para o coordenadas do JLayeredPane
                    Point posicao = SwingUtilities.convertPoint(gridPanel,
                            casa.getLocation(), this);
                    posicao.setLocation(posicao.x + (casa.getWidth() - piece.getWidth()) / 2,
                            posicao.y + (casa.getHeight() - piece.getHeight()) / 2);
                    piece.setLocation(posicao.x, posicao.y);

                    this.add(piece, JLayeredPane.DRAG_LAYER);
                    pieceLabels.add(piece);
                }
            }
        }
    }

    public void atualizarTamanhoGrid(int w, int h) {
        this.gridPanel.setBounds(0, 0, w, h);
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public void atualizarPecas(int square) {
        SwingUtilities.invokeLater(() -> {

            int j = 0;
            for (int i = 0; i < rows * cols; i++) {
                int y = i / cols;
                int x = i % cols;
                SquarePanel casa = casas[y][x];

                // Calcula posição da casa relativa ao ChessBoardPanel
                Point posicao = SwingUtilities.convertPoint(gridPanel, casa.getLocation(), this);

                // Exemplo: reposicionar peça se houver
                // erro aqui

                if (casa.getPiece()) {
                    PieceLabel peca = this.pieceLabels.get(j);

                    peca.updateSize((int) (casa.getWidth() * 0.8));

                    posicao.setLocation(posicao.x + (casa.getWidth() - peca.getWidth()) / 2,
                            posicao.y + (casa.getHeight() - peca.getHeight()) / 2);
                    j++;

                    peca.setLocation(posicao.x, posicao.y);

                }

            }
        });
    }

    public void atualizaTamanhoPeca(int w, int h) {
        for (int i = 0; i < rows * cols; i++) {
            int x = i % cols;
            int y = i / cols;

        }

    }

    public SquarePanel squareMaisProxima(Point m) {
        for (int i = 0; i < rows * cols; i++) {
            int y1 = i / cols;
            int x1 = i % cols;
            SquarePanel casa = casas[y1][x1];
            if (isPieceIn(m.x, m.y, casa)) {
                return casa;
            }
        }
        return new SquarePanel();
    }

    public void placePieceInSquare(SquarePanel casa, PieceLabel peca) {
        Point posicao = SwingUtilities.convertPoint(gridPanel, casa.getLocation(), this);
        peca.updateSize((int) (casa.getWidth() * 0.8));

        posicao.setLocation(posicao.x + (casa.getWidth() - peca.getWidth()) / 2,
                posicao.y + (casa.getHeight() - peca.getHeight()) / 2);
        peca.setLocation(posicao.x, posicao.y);

    }

    public void relocarPecaAumentada(int novoTamanho, Dimension d, PieceLabel peca) {
        int larguraAntiga =(int) d.getWidth();
        int alturaAntiga = (int) d.getHeight();

        int deltaX = (novoTamanho - larguraAntiga) / 2;
        int deltaY = (novoTamanho - alturaAntiga) / 2;

        peca.setLocation(peca.getX() - deltaX, peca.getY() - deltaY);
      
   

    }

    public boolean isPieceIn(int x, int y, SquarePanel casa) {
        Point posicao = SwingUtilities.convertPoint(gridPanel, casa.getLocation(), this);
        if (x > posicao.x && x < (posicao.x + casa.getWidth())
                && y > posicao.y && y < (posicao.y + casa.getWidth())) {
            return true;
        } else
            return false;
    }

}
