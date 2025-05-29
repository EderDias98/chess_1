import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieceLabel extends JLabel {
    private Point offset;


    public PieceLabel(ImageIcon icon) {
        super(icon);


        setSize(icon.getIconWidth(), icon.getIconHeight());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = getLocation();
                int x = p.x + e.getX() - offset.x;
                int y = p.y + e.getY() - offset.y;
                setLocation(x, y);
            }
        });
    }

    // Atualiza tamanho da peça
    public void updateSize(int newSize) {
        Image img = ((ImageIcon) this.getIcon()).getImage().getScaledInstance(newSize, newSize, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(img));
        this.setSize(newSize, newSize);
    }
}
