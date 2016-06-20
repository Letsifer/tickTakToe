package game.ticktaktoe;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Евгений
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            new Game().run();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
