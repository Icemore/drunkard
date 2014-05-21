package ru.spbau.drunkard;

import java.util.List;

public class HexagonalField extends Field {
    private Position[] evenDirections = {
            new Position(0, -1),
            new Position(1, -1),
            new Position(1, 0),
            new Position(1, 1),
            new Position(0, 1),
            new Position(-1, 0)
    };

    private Position[] oddDirections = {
            new Position(-1, -1),
            new Position(0, -1),
            new Position(1, 0),
            new Position(0, 1),
            new Position(-1, 1),
            new Position(-1, 0)
    };

    @Override
    public List<Position> getNeighbours(Position pos) {
        if (pos.y % 2 == 0) {
            return getNeighbours(pos, evenDirections);
        } else {
            return getNeighbours(pos, oddDirections);
        }
    }
}