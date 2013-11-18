package pelitesti1;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Pelihahmo {
    private int alkuX;
    private int alkuY;
    private int x;
    private int y;
    
    private int leveys;
    private int korkeus;
    private int hyppynro;
    
    private int katseenSuunta;
    
    private int hp;
    private int liikeY;
    private int liikeX;
    
    private ArrayList<Ammus> ammukset;
    
    
    public Pelihahmo(int x, int y, int halkaisija) {
        this.alkuX = x;
        this.alkuY = y;
        
        this.x = alkuX;
        this.y = alkuY;
        this.leveys = halkaisija;
        this.korkeus = halkaisija;
        
        this.liikeY = 0;
        this.katseenSuunta=1;
        this.hp=5;
        this.ammukset = new ArrayList();
    }
    
    public Pelihahmo(int x, int y, int leveys, int korkeus) {
        this.alkuX = x;
        this.alkuY = y;
        
        this.x = alkuX;
        this.y = alkuY;
        this.leveys = leveys;
        this.korkeus = korkeus;
        
        this.liikeY = 0;
        this.katseenSuunta=1;
        this.hp=5;
        this.ammukset = new ArrayList();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHalkaisija() {
        return leveys;
    }

    public void liiku(int painovoima) {
        this.y = this.y - this.liikeY;
        this.liikeY = this.liikeY - painovoima;
        
        if(this.liikeY < -8) {
            this.liikeY = -8;
        }
        this.x = this.x + liikeX;
    }
    
    public void liikuKuva(int painovoima) {
        //this.y = this.y - this.liikeY;
        this.liikeY = this.liikeY - painovoima;
        
        if(this.liikeY < -8) {
            this.liikeY = -8;
        }
        this.x = this.x + liikeX;
    }
    
    public void yritaKayttaaAlustaa(Alusta alusta) {
        Point x1=new Point(this.x+1,this.y);                                    //  .-x1-------x2-.
        Point x2=new Point(this.x+this.leveys-1,this.y);                        //  x8		  x3
        Point x3=new Point(this.x+this.leveys,this.y+1);                        //  |		  |
        Point x4=new Point(this.x+this.leveys,this.y+this.korkeus-1);           //  |		  |
        Point x5=new Point(this.x+this.leveys-1,this.y+this.korkeus);           //  |             |
        Point x6=new Point(this.x+1,this.y+this.korkeus);                       //  |		  |
        Point x7=new Point(this.x,this.y+this.korkeus-1);                       //  x7      	  x4
        Point x8=new Point(this.x,this.y+1);                                    //  '-x6-------x5-'
        
        Rectangle r = new Rectangle(alusta.getX(),alusta.getY(),alusta.getLeveys(),alusta.getKorkeus());
        
        if((r.contains(x6) || r.contains(x5)) && ((this.y + this.korkeus - (Math.abs(this.liikeY) + 1)) < r.y) ){  //lattia
            this.y = alusta.getY() - this.korkeus;                                                                 //
            if(this.liikeY<0){                                                                                     //
                this.liikeY = 0;                                                                                   //
            }                                                                                                      //
            this.hyppynro = 0;                                                                                     //
        }
        
        else if((r.contains(x3) || r.contains(x4)) && ((this.x + this.leveys - (Math.abs(this.liikeX) + 1)) < r.x) ) {  //oikea seinä
            this.x = alusta.getX() - this.leveys;                                                                  //
        }
        
        else if((r.contains(x7) || r.contains(x8)) && ((this.x + Math.abs(this.liikeX)+1) > (r.x+r.width)) ){           //vasen seinä
            this.x = alusta.getX() + alusta.getLeveys();                                                           //
        }
        
        else if((r.contains(x1) || r.contains(x2)) && ((this.y + Math.abs(this.liikeY)+1) > (r.y+r.height)) ){          //katto
            this.y = alusta.getY() + alusta.getKorkeus();                                                          //
            if(this.liikeY>0){                                                                                     //
                this.liikeY = 0;                                                                                   //
            }                                                                                                      //
        }
    }
    
    public void yritaTormataSeinaan(Alusta alusta) {
        Point x1=new Point(this.x,this.y+(this.korkeus/2));
        Point x2=new Point(this.x+this.leveys,this.y+(this.korkeus/2));
        Rectangle r = new Rectangle(alusta.getX(),alusta.getY(),alusta.getLeveys(),alusta.getKorkeus());
        if(r.contains(x2)||r.contains(x1)) {
            this.liikeX = 0;
        }
    }
    
    public boolean tarkistaVihuTormays(Vihollinen vihu){
        Rectangle r1 = new Rectangle(vihu.getX(), vihu.getY(), vihu.getLeveys(), vihu.getKorkeus());
        Rectangle r2 = new Rectangle(this.x, this.y, this.leveys, this.korkeus);
        
        if(r1.intersects(r2)){
            return true;
        }else{
            return false;
        }
    }
    public void hyppaa() {
        if(liikeY != 0) {
                return;
            }/*else{
                liikeY=14;
                ++hyppynro;
                return;
            }*/
        
        if(hyppynro==0){
            liikeY = 16;
            ++hyppynro;
        }
    }
    
    public void ammu(){
        this.ammukset.add(new Ammus(x,y,leveys,korkeus,katseenSuunta));
    }
    
    public void poistaAmmus(Ammus i){
        this.ammukset.remove(i);
    }
    
    public void liikuX(int k){
        liikeX=5*k;
        if(k!=0)
            katseenSuunta=k;
    }
    
    public void reset(){
        this.x=this.alkuX;
        this.y=this.alkuY;
    }
    
    public int getLiikeY() {
        return liikeY;
    }
    
    public void setLiikeY(int liikeY) {
        this.liikeY=liikeY;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public ArrayList<Ammus> getAmmukset() {
        return ammukset;
    }
}