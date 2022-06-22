package sample;

public class Gracz {
    static Gracz to;
    int zycia;
    int punkty;

    Gracz(){
        this.zycia = 4;
        this.punkty = 0;
        to = this;
    }


    public int getZycia() {
        return zycia;
    }

    public static Gracz getTo() {
        return to;
    }

    void utrataZycia(){
        zycia--;
    }
    void dodajPunkt(){
        this.punkty++;
    }
    int getPunkt(){
        return punkty;
    }
}
