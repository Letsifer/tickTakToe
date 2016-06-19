package game.ticktaktoe;

/**
 *
 * @author Евгений
 */
public class Main {
    public static void main(String[] args) {
        try {
            new Game().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
