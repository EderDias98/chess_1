import java.awt.Color;
import java.awt.Point;

public class Board {
    Piece mat[][] = new Piece[8][8];

    public Board() {

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                Point p = new Point(x, y);
                Piece peca = new EmptyPiece();
                if (y == 1 || y == 6 || y == 0 || y == 7) {
                    if (y == 1) {
                        peca = new Pawn(PieceType.BLACK_PAWN, p, this, Color.BLACK);
                    } else if (y == 6) {
                        peca = new Pawn(PieceType.WHITE_PAWN, p, this, Color.WHITE);
                    } else if (y == 0 || y == 7) {
                        boolean branco = (y == 7); // linha 7: branco, linha 0: preto

                        switch (x) {
                            case 0:
                            case 7:
                                peca = (branco) ? new Rook(PieceType.WHITE_ROOK, p, this, Color.WHITE)
                                        : new Rook(PieceType.BLACK_ROOK, p, this, Color.BLACK);
                                break;
                            case 1:
                            case 6:
                                peca = (branco) ? new Knight(PieceType.WHITE_KNIGHT, p, this, Color.WHITE)
                                        : new Knight(PieceType.BLACK_KNIGHT, p, this, Color.BLACK);
                                break;
                            case 2:
                            case 5:
                                peca = (branco) ? new Bishop(PieceType.WHITE_BISHOP, p, this, Color.WHITE)
                                        : new Bishop(PieceType.BLACK_BISHOP, p, this, Color.BLACK);

                                break;
                            case 3:
                                peca = (branco) ? new Queen(PieceType.WHITE_QUEEN, p, this, Color.WHITE)
                                        : new Queen(PieceType.BLACK_QUEEN, p, this, Color.BLACK);
                                break;
                            case 4:
                                peca = (branco) ? new King(PieceType.WHITE_KING, p, this, Color.WHITE)
                                        : new King(PieceType.BLACK_KING, p, this, Color.BLACK);
                                break;
                        }
                    }
                }
                this.mat[x][y] = peca;
            }

        }
        // acaba a inicializaçao do tabuleiro inicial

    }

    public Piece[][] getMat() {
        return mat;
    }

    public boolean posicaoValida(Point p) {
        if (p.x < 0 || p.x > 7 || p.y < 0 || p.y > 7)
            return false;
        return true;
    }

    public Piece getPiece(Point p) {
        if (!posicaoValida(p))
            return null;
        return this.mat[p.y][p.x];
    }

    public void setPiece(Piece p, Point pf) {
        this.mat[pf.y][pf.x] = p;
        p.setPosicao(pf);
    }

}
