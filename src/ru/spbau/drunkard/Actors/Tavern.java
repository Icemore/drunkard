package ru.spbau.drunkard.Actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class Tavern extends Actor {
    private final int frequency = 20;
    private Position spawnPos;

    public Tavern(Position pos, Field field) {
        super(pos, field);

        spawnPos = new Position(pos.x, pos.y + 1);
    }

    @Override
    public void makeMove(int stepNumber) {
        // go every frequency move
        if (stepNumber % frequency != 0) {
            return;
        }

        if (field.getOnPos(spawnPos) == null) {
            field.addActor(new Drunkard(new Position(spawnPos), field));
        }
    }
}
