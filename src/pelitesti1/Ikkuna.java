package pelitesti1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ikkuna extends JPanel {

    private Maailma maailma;
    private MenuValinta menuvalinta;
    private int polyx = 130;
    private int polyy = 206;
    private BufferedImage tausta;
    private BufferedImage hp;

    public Ikkuna(Maailma maailma, MenuValinta menuvalinta) {
        this.maailma = maailma;
        this.menuvalinta = menuvalinta;
        
        try {
            tausta = ImageIO.read(new File("res/tausta.png"));
            hp = ImageIO.read(new File("res/hp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        //g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(new Color(83,203,255));

        g.setColor(new Color(255,127,39));

        g.drawImage(tausta, -50, this.getHeight()-tausta.getHeight(), null);
        
        ArrayList<Alusta> alustat = maailma.getAlustat();
        for (Alusta alusta : alustat) {
            g.drawRect(alusta.getX(), alusta.getY(), alusta.getLeveys(), alusta.getKorkeus());
        }
        
        g.setColor(Color.RED);
        
        Pelihahmo hahmo = maailma.getPelihahmo();
        //g.fillOval(hahmo.getX(), hahmo.getY(), hahmo.getHalkaisija(), hahmo.getHalkaisija());
        g.drawImage(hahmo.getAnimation().getSprite(), hahmo.getX(), hahmo.getY(), null);
        
        g.setColor(Color.BLACK);
        
        ArrayList<Ammus> ammukset = hahmo.getAmmukset();
        for(Ammus ammus : ammukset){
            g.fillRect(ammus.getX(), ammus.getY(), ammus.getHalkaisija(), ammus.getHalkaisija());
        }
        
        g.setColor(Color.BLUE);
        
        ArrayList<Vihollinen> viholliset = maailma.getViholliset();
        for(Vihollinen vihu : viholliset){
            g.fillRoundRect(vihu.getX(), vihu.getY(), vihu.getLeveys(), vihu.getKorkeus(),3,3);
        }
        g.setColor(Color.RED);
        
        ListIterator iteVihu = viholliset.listIterator();
        while(iteVihu.hasNext()){
            Vihollinen vihu = (Vihollinen)iteVihu.next();
            g.drawRoundRect(vihu.getX(), vihu.getY(), vihu.getLeveys(), vihu.getKorkeus(),3,3);
        }
        
        for(int i = 0; i<maailma.getPelihahmo().getHp(); ++i){
            g.drawImage(hp, 5+i*20, 5, 16, 16, null);
        }
        
        if(maailma.getTila()==Maailma.PAUSE){
            g.setColor(new Color(0,200,200,50));
            g.fillRect(0, 0, 800, 600);
            g.setColor(new Color(175,200,200,225));
            g.fillRect(100, 100, 600, 400);
            
            Font font = new Font("Impact", Font.PLAIN, 36);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("PAUSE", 360, 140);
            g.drawString("RESUME GAME", 150, 220);
            g.drawString("EXIT GAME", 150, 260);
            
            int[] xPoints={menuvalinta.getPolyx(),menuvalinta.getPolyx()+10,menuvalinta.getPolyx(),menuvalinta.getPolyx()-10};
            int[] yPoints={menuvalinta.getPolyy()-10,menuvalinta.getPolyy(),menuvalinta.getPolyy()+10,menuvalinta.getPolyy()};
            g.setColor(Color.RED);
            g.fillPolygon(xPoints,yPoints,4);
            g.setColor(Color.BLACK);
            g.drawPolygon(xPoints,yPoints,4);
        }
        // kutsu getToolkit().sync() varmistaa että jokainen piirtokutsu
        // hoidetaan heti
        getToolkit().sync();
    }
}
