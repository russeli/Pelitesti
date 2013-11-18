package pelitesti1;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Vihollinen {
    private int alkuX;
    private int alkuY;
    private int x;
    private int y;
    
    private int leveys;
    private int korkeus;
    
    private int hp;
    private int liikeY;
    private int liikeX;
    
    private ArrayList<Ammus> ammukset;
    
    
    public Vihollinen(int x, int y, int leveys, int korkeus) {
        this.alkuX = x;
        this.alkuY = y;
        this.x = alkuX;
        this.y = alkuY;
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.liikeY = 0;
        this.hp = 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getLeveys() {
        return leveys;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }
    

    public void liiku(int painovoima) {
        this.y = this.y - this.liikeY;
        this.liikeY = this.liikeY - painovoima;
        
        if(this.liikeY < -8) {
            this.liikeY = -8;
        }
    }
    
    public void liikuKuva(int painovoima) {
        //this.y = this.y - this.liikeY;
        this.liikeY = this.liikeY - painovoima;
        
        if(this.liikeY < -8) {
            this.liikeY = -8;
        }
    }
    
    public void yritaKayttaaAlustaa(Alusta alusta) {
        Point x1=new Point(this.x,this.y+this.korkeus);                 //   |   |
        Point x2=new Point(this.x+this.leveys,this.y+this.korkeus);     //  x1--x2
        Rectangle r = new Rectangle(alusta.getX(),alusta.getY(),alusta.getLeveys(),alusta.getKorkeus());
        
        if((r.contains(x1) || r.contains(x2))&&(this.y<r.y)){
            this.y = alusta.getY() - this.korkeus;
            this.liikeY = 0;
        }
        
        Point xx1=new Point(this.x,this.y+(this.leveys/2));
        Point xx2=new Point(this.x+this.leveys,this.y+(this.leveys/2));
        if(r.contains(xx2)) {
            this.x = alusta.getX() - this.leveys-1;
        }else if(r.contains(xx1)){
            this.x = alusta.getX() + alusta.getLeveys()+1;
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    
    public int getLiikeY() {
        return liikeY;
    }
    
    public void setLiikeY(int liikeY) {
        this.liikeY=liikeY;
    }
    
    public Vihollinen getVihu(){
        return this;
    }
}