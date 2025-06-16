
import java.awt.Color;
import java.awt.Point;

public abstract class Piece {

    private PieceType tipo;
    private Point pos;
    private Board tab;
    private Color cor;

    public Piece(PieceType tipo, Point pos, Board tab, Color cor) {
        this.tipo = tipo;
        this.pos = pos;
        this.tab = tab;
        this.cor = cor;
    }

    public void setPosicao(Point pos) {
        this.pos = pos;
    }

    public Point getPosicao() {
        return pos;
    }

    public PieceType getTipo() {
        return tipo;
    }

    public Board getTab() {
        return tab;
    }

    public Color getCor() {
        return cor;
    }

    public abstract boolean movimentoValido(Point pf);

    public void moverPeca(Point pf) {
        this.tab.setPiece(new EmptyPiece(), this.getPosicao());
        this.tab.setPiece(this, pf);

    }

    public boolean checkRei(Color cor) {

        // preta 00
        // achar o rei preto;
        // direção vertica
        Piece rei = null;
        int direcao = (this.getCor() == Color.WHITE) ? -1 : 1;
        PieceType tKing = (cor == Color.WHITE) ? PieceType.WHITE_KING : PieceType.BLACK_KING;
        PieceType tQueen = (cor == Color.WHITE) ? PieceType.WHITE_QUEEN : PieceType.BLACK_QUEEN;
        PieceType tRock = (cor == Color.WHITE) ? PieceType.WHITE_ROOK : PieceType.BLACK_ROOK;
        PieceType tBishob = (cor == Color.WHITE) ? PieceType.WHITE_BISHOP : PieceType.BLACK_BISHOP;
        PieceType tKnight = (cor == Color.WHITE) ? PieceType.WHITE_KNIGHT : PieceType.BLACK_KNIGHT;
        PieceType tPawn = (cor == Color.WHITE) ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;

        for (Piece[] row : tab.getMat()) {
            for (Piece p : row) {
                if (p.tipo == tKing) {
                    rei = p;
                }
            }
        }
        Point pt = new Point(rei.pos);

        // vertica cima
        for (int i = (int) rei.pos.getY() + 1; i <= 7; i++) {
            pt.y = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tRock || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }
        // vertical baixo
        for (int i = (int) rei.pos.getY() - 1; i >= 0; i--) {
            pt.y = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tRock || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }
        }
        // lado esquerdo
        for (int i = (int) rei.pos.getX() - 1; i >= 0; i++) {
            pt.x = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tRock || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }
        // lado dirreito
        for (int i = (int) rei.pos.getX() + 1; i >= 7; i++) {
            pt.x = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tRock || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }

        // diagonal cima direito;
        for (int i = (int) rei.pos.getY() + 1, j = (int) rei.pos.getX() + 1; i <= 7; i++, j++) {
            pt.y = i;
            pt.x = j;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tBishob || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }
        // diagonal baixo diretiro
        for (int i = (int) rei.pos.getY() - 1, j = (int) rei.pos.getX() + 1; i >= 0; i--, j++) {
            pt.y = i;
            pt.x = j;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tBishob || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }
        }
        // diagonal baixo esquerdo
        for (int i = (int) rei.pos.getY() - 1, j = (int) rei.pos.getX() - 1; i >= 0; i--, j--) {
            pt.x = j;
            pt.y = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tBishob || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }
        // diagonal cima esquerdo
        for (int i = (int) rei.pos.getY() + 1, j = (int) rei.pos.getX() - 1; i <= 7; i++, j--) {
            pt.x = j;
            pt.y = i;
            Piece t = tab.getPiece(pt);
            if (t.getTipo() != PieceType.EMPTY) {
                if (t.getTipo() == tBishob || t.getTipo() == tQueen) {
                    return true;
                } else
                    break;
            }

        }

        // testar as posicoes do cavalo;
        // 1

        pt = new Point(rei.pos);
        pt.x += 1;
        pt.y += 3;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }

        pt.x -= 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }

        pt = new Point(rei.pos);

        pt.y -= 3;
        pt.x -= 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }
        pt.x += 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }

        pt = new Point(rei.pos);
        pt.y += 1;
        pt.x += 3;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }

        pt.y -= 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }

        pt = new Point(rei.pos);

        pt.x -= 3;
        pt.y -= 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }
        pt.y += 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tKnight) {
                return true;
            }
        }
        // testar as posicoesdo peao
        pt = new Point(rei.pos);
        pt.y += direcao;
        pt.x += 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tPawn) {
                return true;
            }
        }

        pt.y += direcao;
        pt.x -= 1;
        if (tab.posicaoValida(pt)) {
            if (tab.getPiece(pt).getTipo() == tPawn) {
                return true;
            }
        }
        return false;
    }

}
