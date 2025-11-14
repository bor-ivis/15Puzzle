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

        Collections.shuffle(values);

        for (int i = 0; i < 16; i++) {
            brickor[i] = new Tile(values.get(i));
        }
    }

    public Tile getTile(int index) {
        return brickor[index];
    }

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

    public boolean isSolved() {
        for (int i = 0; i < 15; i++) {
            if (!brickor[i].getVärde().equals(String.valueOf(i + 1))) return false;
        }
        return brickor[15].isEmpty();
    }

    private int findEmpty() {
        for (int i = 0; i < 16; i++) {
            if (brickor[i].isEmpty()) return i;
        }
        return -1;
    }

    private boolean isAdjacent(int i1, int i2) {
        int r1 = i1 / 4, c1 = i1 % 4;
        int r2 = i2 / 4, c2 = i2 % 4;
        return (Math.abs(r1 - r2) + Math.abs(c1 - c2)) == 1;
    }
    public void lättVinst(){
        for(int i=0; i < 14; i++){
            brickor[i] = new Tile(String.valueOf(i+1));
        }
        brickor[14] = new Tile("");
        brickor[15] = new Tile("15");
    }

}
