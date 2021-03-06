package pelitesti1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Maailma {
    private int kierros;
    
    private int painovoima;
    private int hahmonYnopeus;
    private int tila;
    private ArrayList<Alusta> alustat;
    private Pelihahmo pelihahmo;
    private ArrayList<Vihollinen> viholliset;
    private boolean kameraPaikallaanX, kameraPaikallaanY;
    
    public final static int MAIN=0, GAME=1, PAUSE=2;
        
    public Maailma() {
        // alusta täällä muuttujan kierros arvoksi 0
        this.kierros = 0;
        
        this.tila = GAME;
        this.painovoima = 1;
        this.hahmonYnopeus = 1;
        
        // älä muuta alustojen luontia - Alusta(x, y, leveys, korkeus)
        this.alustat = new ArrayList<Alusta>();
        this.alustat.add(new Alusta(0, -32, 32, 640));
        this.alustat.add(new Alusta(800, -20, 32, 640));
        this.alustat.add(new Alusta(-32, 576, 960, 32));
        
        //this.alustat.add(new Alusta(64, 448, 512, 32));
        this.alustat.add(new Alusta(68, 448, 512, 32));
        this.alustat.add(new Alusta(192, 320, 512, 32));
        this.alustat.add(new Alusta(64, 192, 512, 32));
        this.alustat.add(new Alusta(192, 64, 512, 32));
        /*this.alustat.add(new Alusta(256, 100, 500, 32));
        this.alustat.add(new Alusta(64, 20, 480, 32));
        this.alustat.add(new Alusta(64, 450, 32, 66));*/
        // voit lisätä toki omia alustoja halutessasi!
        this.viholliset = new ArrayList<Vihollinen>();
        //this.viholliset.add(new Vihollinen(300, 300, 16, 16));

        this.pelihahmo = new Pelihahmo(50, 200, 32);
    }
/*
    public void nopeuta() {
        this.nopeus = nopeus + 1;
    }
*/
    public void liikuta() {
        
        ArrayList<Ammus> ammukset = pelihahmo.getAmmukset();
        kameraPaikallaanX = true;
        kameraPaikallaanY = true;
        
        if((pelihahmo.getY()<200 && pelihahmo.getLiikeY()>0) || (pelihahmo.getY()>400 && pelihahmo.getLiikeY()<0))  //Jos lähestytään ruudun reunaa, liikutetaan muita hahmon sijaan.
            kameraPaikallaanY=false;
        if((pelihahmo.getX()<300 && pelihahmo.getLiikeX()<0) || (pelihahmo.getX()>500 && pelihahmo.getLiikeX()>0))  //Jos lähestytään ruudun reunaa, liikutetaan muita hahmon sijaan.
            kameraPaikallaanX=false;
        
        for(Vihollinen vihollinen : viholliset){
            vihollinen.liiku(painovoima, pelihahmo.getLiikeY(), pelihahmo.getLiikeX(), kameraPaikallaanX, kameraPaikallaanY);   //Vihollisten liikutus
        }
        
        for (Alusta alusta : alustat) {
            alusta.liiku(pelihahmo.getLiikeY(), -pelihahmo.getLiikeX(), kameraPaikallaanX, kameraPaikallaanY);  //Alustojen liikutus
        }
        
        Iterator<Ammus> ammusIte = ammukset.iterator();
        while(ammusIte.hasNext()){
            ammusIte.next().liiku(pelihahmo.getLiikeY(), pelihahmo.getLiikeX(), kameraPaikallaanX, kameraPaikallaanY);   //Ammusten liikutus
        }
        
        this.pelihahmo.liiku(painovoima, kameraPaikallaanX, kameraPaikallaanY); //Pelihahmon Liikutus
        
        // asetetaan hahmo alustalle jos mahdollista
        for (Alusta alusta : alustat){
            this.pelihahmo.yritaKayttaaAlustaa(alusta);
            for(Vihollinen vihollinen : viholliset){
                vihollinen.yritaKayttaaAlustaa(alusta);
            }
            Iterator<Ammus> ite = ammukset.iterator();
            while(ite.hasNext()){
                if(ite.next().tarkistaTormays(alusta)){
                    //this.pelihahmo.poistaAmmus(iterator);
                    ite.remove();
                }
            }

            //this.pelihahmo.yritaTormataSeinaan(alusta);
        }
        
        ListIterator<Vihollinen> iteVihu = viholliset.listIterator();
        while(iteVihu.hasNext()){
            Vihollinen vihu = iteVihu.next();
            
            ListIterator<Ammus> iteAmmus = ammukset.listIterator();
            while(iteAmmus.hasNext()){
                if(iteAmmus.next().tarkistaTormaysVihu(vihu)){
                    //this.pelihahmo.poistaAmmus(iterator);
                    vihu.setHp(vihu.getHp()-1);
                    if(vihu.getHp()==0){
                        iteVihu.remove();
                    }
                    iteAmmus.remove();
                }
            }
            
            if(pelihahmo.tarkistaVihuTormays(vihu)){
                iteVihu.remove();
                pelihahmo.setHp(pelihahmo.getHp()-1);
            }
        }
        
        
        /*
        // siirretään alustoja uudestaan oikealle
        for (Alusta alusta : alustat) {
            if(alusta.getX() + alusta.getLeveys() < 0) {
                alusta.setX(alusta.getX() + 1000);
            }
        }
        */
        // nopeutetaan peliä 600 kierroksen välein (600 kierrosta on noin 
        // 10 sekuntia)
        this.kierros = this.kierros + 1;
        if(kierros % 300 == 0) {
            this.viholliset.add(new Vihollinen( alustat.get(0).getX() + alustat.get(0).getLeveys() + (int)(Math.random() * ((764 - alustat.get(0).getX()) + 1)),        //xPosition
                                                alustat.get(0).getY() + (int)(Math.random() * ((alustat.get(2).getY() - 16 - alustat.get(0).getY()) + 1)), 16, 16));    //yPosition
        }
    }
    
    public ArrayList<Alusta> getAlustat() {
        return alustat;
    }
    
    public Pelihahmo getPelihahmo() {
        return pelihahmo;
    }
    
    public ArrayList<Vihollinen> getViholliset() {
        return viholliset;
    }

    public int getNopeus() {
        return hahmonYnopeus;
    }

    public int getTila() {
        return tila;
    }

    public void setTila(int tila) {
        this.tila = tila;
    }
}