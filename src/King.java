
import java.awt.Color;
import java.awt.Point;

public class King extends Piece {
    public King(PieceType tipo, Point pos, Board tab, Color cor) {
        super(tipo, pos, tab, cor);
    }

    @Override
    public boolean movimentoValido(Point pf) {

        Point pi = this.getPosicao();
        boolean valido = false;
        Board tab = this.getTab();

        if(pi.x +1 == pf.x && pi.y+1 == pf.y ){
            valido = true;
        }

        if(pi.x +1 == pf.x && pi.y-1 == pf.y ){
            valido = true;
        }

        if(pi.x -1 == pf.x && pi.y+1 == pf.y ){
            valido = true;
        }

        if(pi.x -1 == pf.x && pi.y-1 == pf.y ){
            valido = true;
        }


        tab.setPiece(new EmptyPiece(), pi);
        Piece rest = tab.getPiece(pf);
        tab.setPiece(this, pf);
        tab.setPiece( new EmptyPiece(), pi);
        if (checkRei(this.getCor())) {
            valido = false;
        }
        tab.setPiece(this, pi);
        tab.setPiece(rest, pf);

        return valido;
    }
}