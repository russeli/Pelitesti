package pelitesti1;

public class MenuValinta {
    
    private int polyx;
    private int polyy;
    private int valinta;
    private Maailma maailma;
    
    public MenuValinta(Maailma maailma){
        this.maailma = maailma;
        
        this.polyx=130;
        this.polyy=206;
        this.valinta=0;
    }            

    public void liikuta(int k){
        if((valinta+k>=0) && (valinta+k<2)){
            this.polyy += k*40;
            this.valinta+=k;
        }
    }
    
    public void valitse(){
        switch(this.valinta){
            case 0:
                maailma.setTila(Maailma.GAME);
                break;
            case 1:
                System.exit(0);
        }
    }
    
    public void reset(){
        this.polyx=130;
        this.polyy=206;
        this.valinta=0;
    }
    public int getPolyx() {
        return polyx;
    }

    public void setPolyx(int polyx) {
        this.polyx = polyx;
    }

    public int getPolyy() {
        return polyy;
    }

    public void setPolyy(int polyy) {
        this.polyy = polyy;
    }
}
