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
    
    public final static int MAIN=0, GAME=1, PAUSE=2;
        
    public Maailma() {
        // alusta täällä muuttujan kierros arvoksi 0
        this.kierros = 0;
        
        this.tila = GAME;
        this.painovoima = 1;
        this.hahmonYnopeus = 1;
        
        // älä muuta alustojen luontia - Alusta(x, y, leveys, korkeus)
        this.alustat = new ArrayList<Alusta>();
        this.alustat.add(new Alusta(-20, -20, 40, 640));
        this.alustat.add(new Alusta(780, -20, 40, 640));
        this.alustat.add(new Alusta(-20, 580, 840, 40));
        this.alustat.add(new Alusta(45, 500, 499, 16));
        this.alustat.add(new Alusta(260, 420, 500, 16));
        this.alustat.add(new Alusta(40, 340, 500, 16));
        this.alustat.add(new Alusta(260, 260, 500, 16));
        this.alustat.add(new Alusta(40, 180, 500, 16));
        this.alustat.add(new Alusta(260, 100, 500, 16));
        this.alustat.add(new Alusta(40, 20, 500, 16));
        this.alustat.add(new Alusta(40, 450, 16, 66));
        // voit lisätä toki omia alustoja halutessasi!
        this.viholliset = new ArrayList<Vihollinen>();
        //this.viholliset.add(new Vihollinen(300, 300, 16, 16));

        this.pelihahmo = new Pelihahmo(50, 200, 15);
    }
/*
    public void nopeuta() {
        this.nopeus = nopeus + 1;
    }
*/
    public void liikuta() {
        
        ArrayList<Ammus> ammukset = pelihahmo.getAmmukset();
        
        if(pelihahmo.getY()<100 && pelihahmo.getLiikeY()>0){
            
            for (Alusta alusta : alustat) {
                alusta.liiku(Math.abs(pelihahmo.getLiikeY()));
            }
            for(Ammus ammus : ammukset){
                ammus.liiku(Math.abs(pelihahmo.getLiikeY()));
            }
            for(Vihollinen vihollinen : viholliset){
                vihollinen.liiku(Math.abs(pelihahmo.getLiikeY()));
            }
            this.pelihahmo.liikuKuva(painovoima);
            
        }else if(pelihahmo.getY()>500 && pelihahmo.getLiikeY()<0){
            
            for (Alusta alusta : alustat) {
                alusta.liiku(-Math.abs(pelihahmo.getLiikeY()));
            }
            for(Ammus ammus : ammukset){
                ammus.liiku(-Math.abs(pelihahmo.getLiikeY()));
            }
            for(Vihollinen vihollinen : viholliset){
                vihollinen.liiku(Math.abs(pelihahmo.getLiikeY()));
            }
            this.pelihahmo.liikuKuva(painovoima);
            
        }else{
            
            this.pelihahmo.liiku(painovoima);
            Iterator<Ammus> ite = ammukset.iterator();
            while(ite.hasNext()){
                ite.next().liiku(0);
            }
            for(Vihollinen vihollinen : viholliset){
                vihollinen.liiku(Math.abs(pelihahmo.getLiikeY()));
            }
        }
        
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
        
        Iterator<Vihollinen> iteVihu = viholliset.iterator();
        while(iteVihu.hasNext()){
            Vihollinen vihu = iteVihu.next();
            
            Iterator<Ammus> iteAmmus = ammukset.iterator();
            while(iteAmmus.hasNext()){
                Ammus ammus = iteAmmus.next();

                if(ammus.tarkistaTormaysVihu(vihu)){
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
            this.viholliset.add(new Vihollinen( alustat.get(0).getX() + alustat.get(0).getLeveys() + (int)(Math.random() * ((764 - alustat.get(0).getX()) + 1)),
                                                alustat.get(0).getY() + (int)(Math.random() * ((alustat.get(2).getY() - 16 - alustat.get(0).getY()) + 1)), 16, 16));
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