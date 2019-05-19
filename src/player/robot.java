package player;

import java.awt.*;
import java.util.Random;

public class robot extends player {
    
    public robot() {
//        super("robot",Color.BLUE);
        String name;
        Color color;
        Random random = new Random(10);
        int r = random.nextInt();
        switch (r) {
            case 0:
                name = "Tom";
                color = Color.BLUE;
                break;
            case 1:
                name = "Jack";
                color = Color.YELLOW;
                break;
            case 2:
                name = "Candy";
                color = Color.MAGENTA;
                break;
            default:
                name = "Judy";
                color = Color.PINK;
        }
        this.setName(name);
        this.setColor(color);
    }
}
