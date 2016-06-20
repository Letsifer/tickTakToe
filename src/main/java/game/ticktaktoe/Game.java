package game.ticktaktoe;

import game.ticktaktoe.stepmakers.RandomMaker;
import game.ticktaktoe.stepmakers.StepMaker;
import game.ticktaktoe.stepmakers.UserMaker;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 *
 * @author Евгений
 */
@Slf4j
public class Game {
    
    @Setter
    private StepMaker maker;
    private Logic logic;
    private Drawer drawer;

    public Game() {
        logic = new Logic(4);
        drawer = new ConsoleDrawer(logic);
        maker = RandomMaker.getInstance(logic.getSize());
    }
    
    public StepEnd run() throws Exception {
        StepEnd end = StepEnd.NOT_END;
        boolean isFirst = true;
        boolean isNonAfterMistake = true;
        do {
            if (isNonAfterMistake) drawer.drawField();
            try {
                drawer.drawStep(isFirst);
                int y = maker.getY(), x = maker.getX();
                log.info(y + " " + x);
                end = logic.makeStep(y, x, isFirst);
                isNonAfterMistake = true;
                isFirst = !isFirst;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
                log.info(Marker.ANY_MARKER, exception);
                isNonAfterMistake = false;
            }            
        } while (end == StepEnd.NOT_END);
        drawer.drawVictory(end);
        return end;
    }
    
}
