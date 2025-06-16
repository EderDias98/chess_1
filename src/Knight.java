
import java.awt.Color;
import java.awt.Point;
public class Knight extends Piece{
        public Knight(PieceType tipo, Point pos, Board tab, Color cor) {
        super(tipo, pos, tab, cor);
    }

    @Override
    public boolean movimentoValido(Point pf) {

        Point pi = this.getPosicao();
        boolean valido = false;
        Board tab = this.getTab();

        if ((pi.x +3 == pf.x) && (pi.y+1 == pf.y)) {
            valido = true;
        }
        if ((pi.x +3 == pf.x) && (pi.y-1 == pf.y)) {
            valido = true;
        }
        if ((pi.x -3 == pf.x) && (pi.y-1 == pf.y)) {
            valido = true;
        }
        if ((pi.x -3 == pf.x) && (pi.y +1 == pf.y)) {
            valido = true;
        }

        if ((pi.y +3 == pf.y) && (pi.x+1 == pf.x)) {
            valido = true;
        }
        if ((pi.y +3 == pf.y) && (pi.x-1 == pf.x)) {
            valido = true;
        }
        if ((pi.y -3 == pf.y) && (pi.x-1 == pf.x)) {
            valido = true;
        }
        if ((pi.y -3 == pf.y) && (pi.x +1 == pf.x)) {
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
