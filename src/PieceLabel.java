import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieceLabel extends JLabel {
    private Point offset;
    private boolean dragging;
    private ChessBoardPanel tabu;
    private boolean flagSquare;
    private SquarePanel squareAtual;
    private boolean flagAumento;
    private ImageIcon imagenOriginal;

    public PieceLabel(ImageIcon icon, ChessBoardPanel tabu) {
        super(icon);
        this.dragging = false;
        this.tabu = tabu;
        this.flagSquare = false;
        this.squareAtual = new SquarePanel();
        this.flagAumento = true;
        this.imagenOriginal = icon;

        setSize(icon.getIconWidth(), icon.getIconHeight());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
                dragging = true;

                PieceLabel piece = (PieceLabel) e.getComponent();
                SquarePanel s = tabu.squareMaisProxima(piece.getMidllePoint());
                s.changeColor(SquarePanel::puxarParaAmarelo);
                tabu.setLayer(piece, 500);
                flagAumento = true;

                // quero amarelar a casa aqui// como fazer isso

            }

            public void mouseReleased(MouseEvent e) {
                if (dragging) {
                    dragging = false;

                    PieceLabel piece = (PieceLabel) e.getComponent();
                    SquarePanel s = tabu.squareMaisProxima(piece.getMidllePoint());

                    s.changeColor(s::puxarParaOriginal);
                    tabu.setLayer(piece, 400);
                    piece.updateSize((int) (s.getWidth() * 0.8));
                    tabu.placePieceInSquare(s, piece);
                    // Calcula a casa mais próxima
                    flagAumento = false;

                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                PieceLabel piece = (PieceLabel) e.getComponent();
                Point p = piece.getLocation();



                SquarePanel s = tabu.squareMaisProxima(piece.getMidllePoint());

                if (s == squareAtual) {
                    flagSquare = false;
                } else {
                    flagSquare = true;
                    squareAtual.changeColor(squareAtual::puxarParaOriginal);
                }

                squareAtual = s;

                if (flagSquare == true) {

                    s.changeColor(SquarePanel::puxarParaAmarelo);
                }
                if (flagAumento == true) {
                    int novoTamanho = (int) (piece.getWidth() * 2);
                    Dimension tamanhoAntigo = piece.getSize();
                    piece.updateSize(novoTamanho);

                    // Atualiza tamanho

                    // Corrige posição para manter centralização
                    int deltaX = (novoTamanho - tamanhoAntigo.width) ;
                    int deltaY = (novoTamanho - tamanhoAntigo.height) ;

                    offset = new Point(deltaX,deltaY);
                    flagAumento = false;
                            // tabu.relocarPecaAumentada(10, dp, piece);
                           
                } 
                
                int x = p.x + e.getX() - offset.x;
                int y = p.y + e.getY() - offset.y;
                
                piece.setLocation(x, y);
              
                    
                
            }
        });
    }

    // Atualiza tamanho da peça
    public void updateSize(int newSize) {
        if (newSize <= 0)
            return;
        Image img = this.imagenOriginal.getImage().getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(img));
        this.setSize(newSize, newSize);
    }

    public Point getMidllePoint() {
        Point po = this.getLocation();
        Point m = new Point();
        m.x = po.x + this.getWidth() / 2;
        m.y = po.y + this.getHeight() / 2;
        return m;
    }
}
