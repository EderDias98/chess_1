
import java.awt.Color;
import java.awt.Point;

public class Pawn extends Piece {
    public Pawn(PieceType tipo, Point pos, Board tab, Color cor) {
        super(tipo, pos, tab, cor);
    }

    @Override
    public boolean movimentoValido(Point pf) {

        Point pi = this.getPosicao();
        Board tab = this.getTab();

        int direcao = (this.getCor() == Color.WHITE) ? -1 : 1;
        // movimento simples
        boolean valido = false;

        // movimento simples com um passo
        if (pi.x == pf.x && tab.getPiece(pf).getTipo() == PieceType.EMPTY) {
            if (pi.y + direcao == pf.y) {
                valido = true;
            } else
            // movimento simples com dois passos
            if ((pi.y == 1 && this.getCor() == Color.BLACK) || (pi.y == 6 && this.getCor() == Color.WHITE)) {
                if (pi.y + 2 * direcao == pf.y)
                    valido = true;
            }

            // movimento de captura
        } else if ((pi.x - 1 == pf.x || pi.x + 1 == pf.x) && (pi.y + direcao == pf.y)
                && tab.getPiece(pf).getTipo() != PieceType.EMPTY
                && tab.getPiece(pf).getCor() != this.getCor()) {
            valido = true;

        }
        
        tab.setPiece(new EmptyPiece(), pi);
        if (checkRei(this.getCor())) {
            valido = false;
        }
        tab.setPiece(this, pi);

        return valido;
    }

}
