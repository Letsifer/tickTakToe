package game.ticktaktoe;

import java.text.MessageFormat;
import java.util.Arrays;
import lombok.Getter;

/**
 *
 * @author Евгений
 */
public class Logic {
    @Getter private final int size;

    private final FieldType[][] fields;

    public String getCell(int i, int j) {
        return fields[i][j] == FieldType.X ? "X"
                : fields[i][j] == FieldType.O ? "O"
                        : " ";
    }

    public Logic() {
        this(3);
    }

    public Logic(int size) {
        this.size = size;
        fields = new FieldType[size][size];
        for (FieldType[] line : fields) {
            Arrays.fill(line, FieldType.EMPTY);
        }
    }

    public StepEnd makeStep(int i, int j, boolean isFirstPlayer) throws Exception {
        try {
            assert (i >= 0 && j >= 0);
            assert (i < size && j < size);
            if (fields[i][j] != FieldType.EMPTY) {
                throw new IllegalArgumentException(
                        MessageFormat.format("Cell({0}, {1}) is occupied", i, j)
                );
            }
            fields[i][j] = isFirstPlayer ? FieldType.X : FieldType.O;
            return isGameOver(i, j);
        } catch (AssertionError assErr) {
            throw new ArrayIndexOutOfBoundsException(MessageFormat.format("Step out of border. Values i = {0}, j = {1}", i, j));
        }

    }

    private StepEnd checkInOneLine(int startI, int startJ, int addI, int addJ) {
        FieldType type = fields[startI][startJ];
        for (int i = startI, j = startJ; i < size && j < size; i += addI, j += addJ) {
            if (type == FieldType.EMPTY || type != fields[i][j]) {
                return StepEnd.NOT_END;
            }
        }
        return type == FieldType.X
                ? StepEnd.FIRST_PLAYER_WIN
                : StepEnd.SECOND_PLAYER_WIN;
    }

    private StepEnd checkMainDiagonal() {
        return checkInOneLine(0, 0, 1, 1);
    }

    private StepEnd checkRow(int row) {
        return checkInOneLine(row, 0, 0, 1);
    }

    private StepEnd checkColumn(int column) {
        return checkInOneLine(0, column, 1, 0);
    }

    private StepEnd checkAdverseDiagonal() {
        return checkInOneLine(0, size - 1, 1, -1);
    }

    private StepEnd isGameOver(int i, int j) {
        StepEnd resultRow = checkRow(i),
                resultColumn = checkColumn(j),
                resultMainDiagonal = i == j ? checkMainDiagonal() : StepEnd.NOT_END,
                resultAdverseDiagonal = i + j == size - 1 ? checkAdverseDiagonal() : StepEnd.NOT_END;
        if (resultRow != StepEnd.NOT_END) {
            return resultRow;
        }
        if (resultColumn != StepEnd.NOT_END) {
            return resultColumn;
        }
        if (resultMainDiagonal != StepEnd.NOT_END) {
            return resultMainDiagonal;
        }
        if (resultAdverseDiagonal != StepEnd.NOT_END) {
            return resultAdverseDiagonal;
        }
        if (Arrays.stream(fields)
                .flatMap(line -> Arrays.stream(line))
                .anyMatch(field -> field == FieldType.EMPTY)) {
            return StepEnd.NOT_END;
        }
        return StepEnd.DRAW;
    }
}
