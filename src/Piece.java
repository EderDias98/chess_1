import java.awt.Color;



public class Piece {
    
    
    @FunctionalInterface
    public interface Operacao {
        boolean executar(Posicao pos);
    }
    




    private PieceType tipo ;
    private Color cor;
    private Posicao posicao;
    

    public Piece(PieceType tipo, Color cor,Posicao pos){
        this.tipo = tipo;
        this.cor = cor;
        this.posicao = pos;
    }
    
    public  boolean movimentoValido(Posicao pos, Operacao operacao){
        return operacao.executar(pos);
    }

}
