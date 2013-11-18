package pelitesti1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MenuPause extends JPanel{

    private String[] valinnat = {"RESUME GAME", "OPTIONS", "EXIT GAME"};
    public MenuPause(){
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
               
        setBackground(Color.BLACK);

        g.setColor(Color.RED);
        g.fillRect(50, 50, 100, 100);
        
        g.setColor(new Color(200,200,200,150));

        g.fillRect(100, 100, 600, 400);
        
        g.setColor(Color.RED);
        setVisible(true);
        //g.fillOval(hahmo.getX(), hahmo.getY(), hahmo.getHalkaisija(), hahmo.getHalkaisija());

        // kutsu getToolkit().sync() varmistaa että jokainen piirtokutsu
        // hoidetaan heti
        getToolkit().sync();
    }
    
}
