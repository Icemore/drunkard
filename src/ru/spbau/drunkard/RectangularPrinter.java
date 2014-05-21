package ru.spbau.drunkard;

public class RectangularPrinter extends Printer {
    @Override
    protected Position getGraphicsSize(Field field) {
        return new Position(field.width + shift.x + extend.x, field.height + shift.y + extend.y);
    }

    @Override
    protected Position getRealPosition(Position pos) {
        return new Position(pos.x + shift.x, pos.y + shift.y);
    }
}
