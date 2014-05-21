package ru.spbau.drunkard;

import java.util.List;

public class RectangularField extends Field {
    private Position[] directions = {
            new Position(0, -1),
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 0)
    };

    @Override
    public List<Position> getNeighbours(Position pos) {
        return getNeighbours(pos, directions);
    }
}
