package ru.spbau.drunkard;

public class HexagonalPrinter extends Printer {
    @Override
    protected Position getGraphicsSize(Field field) {
        return new Position((field.width + shift.x + extend.x) * 2, field.height + shift.y + extend.y);
    }

    @Override
    protected Position getRealPosition(Position pos) {
        Position res = new Position((pos.x + shift.x) * 2, pos.y + shift.y);
        if (pos.y % 2 == 0) {
            res.x += 1;
        }

        return res;
    }
}
