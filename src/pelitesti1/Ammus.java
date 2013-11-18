package pelitesti1;

import java.awt.Point;
import java.awt.Rectangle;

public class Ammus {
    
    private int x;
    private int y;;
    
    private int halkaisija;
    
    private int liikeX;
    
    public Ammus(int x, int y, int hahmLeveys, int hahmKorkeus, int k){
        this.halkaisija=4;
        
        this.x=x-(halkaisija/2)+((k+1)/2)*hahmLeveys;
        this.y=y+(hahmKorkeus)/2-(halkaisija/2);
        
        this.liikeX=7*k;
    }
    
    public void liiku(int liikeY) {
        this.x = this.x + liikeX;
        this.y += liikeY;
    }

    public boolean tarkistaTormays(Alusta alusta){
        Rectangle r = new Rectangle(alusta.getX(),alusta.getY(),alusta.getLeveys(),alusta.getKorkeus());
        
        Point xx1=new Point(this.x,this.y+(this.halkaisija/2));
        Point xx2=new Point(this.x+this.halkaisija,this.y+(this.halkaisija/2));
        if(r.contains(xx2)) {
            return true;
        }else if(r.contains(xx1)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean tarkistaTormaysVihu(Vihollinen vihollinen){
        Rectangle r = new Rectangle(vihollinen.getX(),vihollinen.getY(),vihollinen.getLeveys(),vihollinen.getKorkeus());
        
        Point xx1=new Point(this.x,this.y+(this.halkaisija/2));
        Point xx2=new Point(this.x+this.halkaisija,this.y+(this.halkaisija/2));
        if(r.contains(xx2)) {
            return true;
        }else if(r.contains(xx1)){
            return true;
        }else{
            return false;
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHalkaisija() {
        return halkaisija;
    }
}
