/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Nikke
 */
public class Hmm extends JPanel{
    
    private ArrayList<Point[]> suorakaiteet;
    private Point[] suorakaide;
    
    public Hmm(){
        suorakaide = new Point[0];
        suorakaiteet = new ArrayList();
        
        JFrame frame = new JFrame();
        this.setPreferredSize(new Dimension(800,600));
        addMouseListener(new OmaHiiriAdapter(this));
        addMouseMotionListener(new OmaHiiriAdapter(this));
        frame.setContentPane(this);
        frame.validate();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        new Hmm();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.LIGHT_GRAY);
        
        g.setColor(Color.GRAY);
        
        for(int x = 0; x<=800; x+=32){
            g.drawLine(x, 0, x, 600);
        }
        for(int y = 0; y<=600; y+=32){
            g.drawLine(0, y, 800, y);
        }
        
        g.setColor(Color.ORANGE);
        
        if(suorakaide.length!=0){
            if((suorakaide[1].x-suorakaide[0].x) < 0){
                if((suorakaide[1].y-suorakaide[0].y) < 0)
                    g.drawRect(suorakaide[1].x, suorakaide[1].y, suorakaide[0].x-suorakaide[1].x, suorakaide[0].y-suorakaide[1].y);
                else
                    g.drawRect(suorakaide[1].x, suorakaide[0].y, suorakaide[0].x-suorakaide[1].x, suorakaide[1].y-suorakaide[0].y);
            }
            else if((suorakaide[1].y-suorakaide[0].y) < 0)
                g.drawRect(suorakaide[0].x, suorakaide[1].y, suorakaide[1].x-suorakaide[0].x, suorakaide[0].y-suorakaide[1].y);
            else
                g.drawRect(suorakaide[0].x, suorakaide[0].y, suorakaide[1].x-suorakaide[0].x, suorakaide[1].y-suorakaide[0].y);
        }
        for(Point[] suorakaide2:suorakaiteet){
            g.drawRect(suorakaide2[0].x, suorakaide2[0].y, suorakaide2[1].x-suorakaide2[0].x, suorakaide2[1].y-suorakaide2[0].y);
        }
        
        // kutsu getToolkit().sync() varmistaa että jokainen piirtokutsu
        // hoidetaan heti
        getToolkit().sync();
    }

    void aloitaSuorakaide(int x, int y) {
        suorakaide = new Point[2];
        
        suorakaide[1] = new Point(x,y);
        
        if((x%32)<16)
            x -= (x%32);
        else
            x += (32-(x%32));
        
        if((y%32)<16)
            y -= (y%32);
        else
            y += (32-(y%32));
        
        suorakaide[0] = new Point(x,y);
        repaint();
    }

    void paivitaSuorakaide(int x, int y) {
        suorakaide[1] = new Point(x,y);
        repaint();
    }

    void lopetaSuorakaide(int x, int y) {
        if((x%32)<16)
            x -= (x%32);
        else
            x += (32-(x%32));
        
        if((y%32)<16)
            y -= (y%32);
        else
            y += (32-(y%32));
        
        suorakaide[1] = new Point(x,y);
        //suorakaiteet.add(Array.newInstance(suorakaide));
        Point[] temp = new Point[suorakaide.length];
        for (int i = 0; i < temp.length; ++i) {
            Point p = suorakaide[i];
            if (p != null) {
                temp[i] = new Point(p);
            }
        }
        if((temp[1].x-temp[0].x) < 0){
            if((temp[1].y-temp[0].y) < 0){
                Point apu = temp[0];
                temp[0] = temp[1];
                temp[1] = apu;
            }
            else{
                int apu = temp[0].x;
                temp[0].x = temp[1].x;
                temp[1].x = apu;
            }
        }
        else if((suorakaide[1].y-suorakaide[0].y) < 0){
                int apu = temp[0].y;
                temp[0].y = temp[1].y;
                temp[1].y = apu;
        }
        suorakaiteet.add(temp);
        suorakaide = new Point[0];
        repaint();
    }
}
