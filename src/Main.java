import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public  Main(){
        JFrame janela = new JFrame("Xadrez");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
    public static void  main(String[] args){
        Main m = new Main();
        
    }
}
