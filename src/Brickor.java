import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Brickor {
    private Tile[] brickor = new Tile[16];

    public Brickor() {
        resetRandom();
    }

    public void resetRandom() {
        List<String> values = new ArrayList<>();
        for (int i = 1; i <= 15; i++) values.add(String.valueOf(i));
        values.add(""); // sista rutan = tom

        Collections.shuffle(values); //blandar brickorna slumpmässigt

        for (int i = 0; i < 16; i++) {
            brickor[i] = new Tile(values.get(i));
        }
    }

    public Tile getTile(int index) {
        return brickor[index];
    }
    //Hittar den tomma brickan och byter plats om det går
    public boolean moveTile(int index) {
        int emptyIndex = findEmpty();
        if (isAdjacent(index, emptyIndex)) {
            Tile temp = brickor[index];
            brickor[index] = brickor[emptyIndex];
            brickor[emptyIndex] = temp;
            return true;
        }
        return false;
    }
    //En loop som bara returnerar true om index 15 är tom
    public boolean isSolved() {
        for (int i = 0; i < 15; i++) {
            if (!brickor[i].getVärde().equals(String.valueOf(i + 1))) return false;
        }
        return brickor[15].isEmpty();
    }

    private int findEmpty() { //returnerar indexet för den tomma brickan
        for (int i = 0; i < 16; i++) {
            if (brickor[i].isEmpty()) return i;
        }
        return -1;
    }
    //Räknar rad och kolumn, samt kollar om brickorna ligger bredvid varandra
    private boolean isAdjacent(int i1, int i2) {
        int r1 = i1 / 4, c1 = i1 % 4; //r = rad
        int r2 = i2 / 4, c2 = i2 % 4; //c = kolumn
        return (Math.abs(r1 - r2) + Math.abs(c1 - c2)) == 1;
    }
    //Funktionen till JButton ezWin (lätt vinst knappen)
    public void lättVinst(){
        for(int i=0; i < 14; i++){
            brickor[i] = new Tile(String.valueOf(i+1));
        }
        brickor[14] = new Tile("");
        brickor[15] = new Tile("15");
    }

}
