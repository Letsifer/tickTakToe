package game.ticktaktoe;

/**
 *
 * @author Евгений
 */
public class ConsoleDrawer implements Drawer {

    private final Logic field;
    private final int size;

    public ConsoleDrawer(Logic field) {
        this.field = field;
        this.size = field.getSize();
    }

    private void drawLine(int line) {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(field.getCell(line, i) + " | ");
        }
        System.out.println(field.getCell(line, size - 1));
    }

    @Override
    public void drawField() {
        drawLine(0);
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("---");
            }
            System.out.println("");
            drawLine(i);
        }
        System.out.println("");
        System.out.println("_______________________________________-");
    }

    @Override
    public void drawVictory(StepEnd result) {
        switch (result) {
            case FIRST_PLAYER_WIN:
                System.out.println("First victory");
                break;
            case SECOND_PLAYER_WIN:
                System.out.println("Second victory");
                break;
            default:
                System.out.println("draw");
        }
        drawField();
    }

    @Override
    public void drawStep(boolean isFirst) {
        if (isFirst) {
            System.out.println("First player! Enter y and x: ");
        } else {
            System.out.println("Second player! Enter y and x: ");
        }
    }
}
