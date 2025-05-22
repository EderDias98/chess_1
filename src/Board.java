import java.awt.Color;

public class Board {
    Piece mat[][] = new Piece[8][8];

    public Board(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i==1){
                    mat[i][j] = new Piece(PieceType.PAWN, Color.WHITE, new Posicao(i, j));
                }else if(i==6){

                }
            }
        }
    }

}
