package pelitesti1;

public class Pelimoottori extends Thread {

    private Kayttoliittyma kayttoliittyma;
    private Maailma maailma;
    private MenuPause menupause;
    private boolean kaynnissa;
    private boolean pause;

    public Pelimoottori(Kayttoliittyma kayttoliittyma, Maailma maailma) {
        this.kayttoliittyma = kayttoliittyma;
        this.maailma = maailma;

        this.kaynnissa = true;
    }

    @Override
    public void run() {
        //maailma.nopeuta();
        //maailma.nopeuta();

        while (kaynnissa) {
            switch(maailma.getTila()){
                case Maailma.PAUSE:
                    piirra();
                    piirraMenuPause();
                    odota();
                    break;
                case Maailma.GAME:
                    paivita();
                    piirra();
                    odota();
                    break;
            }
        }
    }

    public void paivita() {
        maailma.liikuta();
    }

    public void piirra() {
        kayttoliittyma.piirra();
    }

    public void piirraMenuPause() {
        kayttoliittyma.piirra();
    }
    
    // metodikutsu sammuta sulkee pelimoottorin seuraavan odota-kutsun jälkeen
    public void sammuta() {
        kaynnissa = false;
    }

    // metodikutsu odota pyytää ohjelmaa odottamaan yhden kuudeskymmenesosasekunnin 
    // ajan, eli ajan, joka on 1000 millisekuntia jaettuna kuudellakymmenellä
    
    // käytännössä tämä johtaa animaatioon, jossa pelimoottori kutsuu paivita-
    // ja piirra-metodeita noin 60 kertaa sekunnissa
    public void odota() {
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}