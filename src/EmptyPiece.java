    import java.awt.Point;
    
    public class EmptyPiece extends Piece {
        public EmptyPiece() {
            super(PieceType.EMPTY, null, null, null);
        }

        @Override
        public void moverPeca(Point pf) {

        }

        @Override
        public boolean movimentoValido(Point pf) {

            return false;
        }
    }