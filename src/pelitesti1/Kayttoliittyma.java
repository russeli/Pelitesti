package pelitesti1;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Maailma maailma;
    private Ikkuna ikkuna;
    private MenuPause menupause;
    private MenuValinta menuvalinta;

    public Kayttoliittyma(Maailma maailma, MenuValinta menuvalinta) {
        this.maailma = maailma;
        this.menuvalinta= menuvalinta;
    }

    @Override
    public void run() {
        frame = new JFrame("Roller!");
        frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        NappaimistonKuuntelija kuuntelija = new NappaimistonKuuntelija(maailma.getPelihahmo(),maailma, menuvalinta);
        frame.addKeyListener(kuuntelija);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void luoKomponentit(Container container) {
        ikkuna = new Ikkuna(maailma,menuvalinta);
        menupause = new MenuPause();
        container.add(ikkuna);
        //container.add(menupause);
    }

    // tarjotaan Kayttoliittyman ulkopuolelta pääsy ikkunan piirtämiseen
    public void piirra() {
        if (ikkuna == null) {
            return;
        }

        ikkuna.repaint();
    }
    
    public void piirraMenuPause() {
        if (menupause == null) {
            return;
        }

        menupause.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }
}