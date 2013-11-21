package pelitesti1;

public class Alusta {

    private int x;
    private int y;
    private int leveys;
    private int korkeus;

    public Alusta(int x, int y, int leveys, int korkeus) {
        this.x = x;
        this.y = y;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void liiku(int nopeusY, int nopeusX, boolean kameraPaikallaanX, boolean kameraPaikallaanY) {
        if(!kameraPaikallaanY)
            this.y += nopeusY;
        if(!kameraPaikallaanX)
            this.x += nopeusX;
    }
}
