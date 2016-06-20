package game.ticktaktoe;

/**
 *
 * @author Евгений
 */
public interface Drawer {
    public void drawField();
    
    public void drawVictory(StepEnd result);
    
    public void drawStep(boolean isFirst);
}
