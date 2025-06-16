import java.awt.Color;
import java.awt.Point;
import java.util.function.Function;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SquarePanel extends JPanel {
    private boolean piece;
    private int offset;
    private Color original;

    public SquarePanel() {
        this.piece = false;
        this.offset = 0;
        this.original = this.getBackground();
    }

    public void setOriginalCor(Color cor) {
        this.original = cor;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setPiece(boolean piece) {
        this.piece = piece;
    }

    public boolean getPiece() {
        return this.piece;
    }

    public void changeColor(Function<Color, Color> transformador) {
        Color c = this.getBackground();
        this.setBackground(transformador.apply(c));
    }

    public  Color puxarParaAmarelo(Color cor) {
        int r = cor.getRed();
        int g = cor.getGreen();
        int b = cor.getBlue();

        // Aumenta R e G, reduz um pouco o B
        r = Math.min(255, r + 40);
        g = Math.min(255, g + 40);
        b = Math.max(0, b - 40);

        return new Color(r, g, b);
    }

    public  Color puxarParaVermelho(Color cor) {
        if (cor == null)
            return Color.RED;

        float[] hsb = Color.RGBtoHSB(
                cor.getRed(),
                cor.getGreen(),
                cor.getBlue(),
                null);

        // Ajusta a matiz para vermelho (0 no HSB) e aumenta saturação
        return Color.getHSBColor(0, Math.min(1f, hsb[1] * 1.3f), hsb[2]);
    }

    public Color puxarParaOriginal(Color color) {
        return this.original;
    }
}
