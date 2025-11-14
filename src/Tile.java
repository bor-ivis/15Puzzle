public class Tile {
    String värde;  //Nummer på brickan eller om den är tom!

    public Tile(String värde){
        this.värde = värde;
    }

    public String getVärde(){ //getter
        return värde;
    }
    public void setVärde(String värde){ //setter
        this.värde = värde;

    }
    public boolean isEmpty(){
        return värde.equals("");  //En boolean som returnerar true om brickan är tom
    }
}
