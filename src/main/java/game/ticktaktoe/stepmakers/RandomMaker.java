package game.ticktaktoe.stepmakers;

import java.util.Random;

/**
 *
 * @author Евгений
 */
public class RandomMaker implements StepMaker {

    private RandomMaker() {
    }   
    
    public static RandomMaker getInstance(int size) {
        if (maker == null) {
            maker = new RandomMaker();
        }
        maker.size = size;
        return maker;
    }

    @Override
    public int getX() throws Exception {
        return random.nextInt(size);
    }

    @Override
    public int getY() throws Exception {
        return random.nextInt(size);
    }

    private static RandomMaker maker;
    private int size;
    private final Random random = new Random();
}
