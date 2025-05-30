import javax.swing.JPanel;

public class SquarePanel extends JPanel{
    private boolean piece;
    
    public void setPiece(boolean piece) {
        this.piece = piece;
    }
    
    public boolean getPiece() {
        return this.piece;
    }
}
