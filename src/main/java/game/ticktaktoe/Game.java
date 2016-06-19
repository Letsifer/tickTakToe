package game.ticktaktoe;

import game.ticktaktoe.stepmakers.RandomMaker;
import game.ticktaktoe.stepmakers.StepMaker;
import lombok.Setter;

/**
 *
 * @author Евгений
 */
//@Slf4j
public class Game {
    
    @Setter
    private StepMaker maker;
    private Logic logic;
    private Drawer drawer;

    public Game() {
        logic = new Logic();
        drawer = new ConsoleDrawer(logic);
        maker = RandomMaker.getInstance(logic.getSize());
    }
    
    public StepEnd run() throws Exception {
        StepEnd end = StepEnd.NOT_END;
        boolean isFirst = true;
        do {
            drawer.drawField();
            try {
                if (isFirst) {
                    System.out.println("First player! ");
                } else {
                    System.out.println("Second player! ");
                }
                System.out.println("Enter y and x: ");
                int y = maker.getY(), x = maker.getX();
                System.out.println(y + " " + x);
                end = logic.makeStep(y, x, isFirst);
                isFirst = !isFirst;
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException exception) {
                System.err.println(exception);
//                log.error(exception.getMessage());
            }            
        } while (end == StepEnd.NOT_END);
        switch (end) {
            case FIRST_PLAYER_WIN:
                System.out.println("First victory");
                break;
            case SECOND_PLAYER_WIN:
                System.out.println("Second victory");
                break;
            default:
                System.out.println("draw");
        }
        drawer.drawField();
        return end;
    }
    
}
