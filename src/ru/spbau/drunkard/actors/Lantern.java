package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class Lantern extends Actor {
    public static final int lightRadius = 3;

    public Lantern(Position pos, Field field) {
        super(pos, field);
    }

    @Override
    public void makeMove(int stepNumber) {
        // do nothing
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }
}
