/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

public class OmaHiiriAdapter extends MouseInputAdapter{

    private Hmm pp;
    
    public OmaHiiriAdapter(Hmm pp){
        this.pp=pp;
    }
    
    public void mousePressed(MouseEvent e){
        pp.aloitaSuorakaide(e.getX(), e.getY());
    }
    
    public void mouseDragged(MouseEvent e) {
        pp.paivitaSuorakaide(e.getX(), e.getY());
    }
    
    public void mouseReleased(MouseEvent e) {
        pp.lopetaSuorakaide(e.getX(), e.getY());
    }
}