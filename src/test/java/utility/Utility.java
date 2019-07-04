package utility;
/* if a method must be called in more than one class, that method is found here. */

import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    public static int generateRandomValue(Integer min, Integer max) {
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
}
