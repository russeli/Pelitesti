package pelitesti1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NappaimistonKuuntelija implements KeyListener {

    private Pelihahmo pelihahmo;
    private Maailma maailma;
    private MenuValinta menuvalinta;
    private boolean oikeaPainettu;
    private boolean vasenPainettu;
    private boolean hyppyPainettu;
    private boolean zPainettu;
    private long lastPressProcessed;

    public NappaimistonKuuntelija(Pelihahmo pelihahmo, Maailma maailma, MenuValinta menuvalinta) {
        this.pelihahmo = pelihahmo;
        this.maailma = maailma;
        this.menuvalinta = menuvalinta;
        this.lastPressProcessed = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ei koodia
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && maailma.getTila()==Maailma.GAME) {
            if(!hyppyPainettu)
                pelihahmo.hyppaa();
            hyppyPainettu=true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(maailma.getTila()==Maailma.GAME){
                maailma.setTila(Maailma.PAUSE);
                menuvalinta.reset();
            }else if(maailma.getTila()==Maailma.PAUSE){
                maailma.setTila(Maailma.GAME);
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_R) {
            pelihahmo.reset();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            if(!zPainettu){
                if(System.currentTimeMillis() - lastPressProcessed > 100) {
                    pelihahmo.ammu();
                    lastPressProcessed = System.currentTimeMillis();
                }
                zPainettu=true;
            }
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikeaPainettu=true;
            pelihahmo.liikuX(1);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            vasenPainettu=true;
            pelihahmo.liikuX(-1);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP && maailma.getTila()==Maailma.PAUSE) {
            menuvalinta.liikuta(-1);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN && maailma.getTila()==Maailma.PAUSE) {
            menuvalinta.liikuta(1);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER && maailma.getTila()==Maailma.PAUSE) {
            menuvalinta.valitse();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            hyppyPainettu=false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            zPainettu=false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(!vasenPainettu)
                pelihahmo.liikuX(0);
            else
                pelihahmo.liikuX(-1);
            oikeaPainettu=false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(!oikeaPainettu)
                pelihahmo.liikuX(0);
            else
                pelihahmo.liikuX(1);
            vasenPainettu=false;
        }
    }
}
