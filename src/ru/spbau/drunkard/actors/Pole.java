package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class Pole extends Actor {
    public Pole(Position pos, Field field) {
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
