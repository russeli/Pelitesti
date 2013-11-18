package pelitesti1;

import javax.swing.SwingUtilities;

public class PeliTesti1 {

    public static void main(String[] args) {
        Maailma pelimaailma = new Maailma();
        MenuValinta menuvalinta = new MenuValinta(pelimaailma);

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(pelimaailma,menuvalinta);
        Pelimoottori moottori = new Pelimoottori(kayttoliittyma, pelimaailma);

        SwingUtilities.invokeLater(kayttoliittyma);

        moottori.start();
    }
}
