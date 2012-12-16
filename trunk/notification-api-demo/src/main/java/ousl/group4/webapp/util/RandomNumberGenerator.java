package ousl.group4.webapp.util;

import java.util.Random;

public class RandomNumberGenerator {

    public static int getRandomNumber(){
        Random random = new Random();
        return Math.abs(random.nextInt(100000));
    }
}
