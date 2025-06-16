
import java.awt.Color;
import java.awt.Point;

public class Bishop extends Piece {
    public Bishop(PieceType tipo, Point pos, Board tab, Color cor) {
        super(tipo, pos, tab, cor);
    }

    @Override
    public boolean movimentoValido(Point pf) {

        Point pi = this.getPosicao();
        boolean valido = false;
        Board tab = this.getTab();

        if ( Math.abs(pi.x - pf.x) == Math.abs(pi.y-pf.y)) {
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
