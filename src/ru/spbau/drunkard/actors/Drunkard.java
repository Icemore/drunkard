package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

import java.util.List;
import java.util.Random;

public class Drunkard extends Actor {
    boolean hasBottle;
    State state;
    Random random;

    public Drunkard(Position pos, Field field) {
        super(pos, field);

        hasBottle = true;
        state = State.WANDERING;
        random = new Random();
    }

    public State getState() {
        return state;
    }

    @Override
    public void makeMove(int stepNumber) {
        if (state != State.WANDERING) return;

        List<Position> neighbours = field.getNeighbours(pos);
        int direction = random.nextInt(neighbours.size());
        Position newPos = neighbours.get(direction);

        field.moveActor(this, newPos);
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }

    @Override
    public void interact(Bottle obstacle) {
        state = State.LYING;
    }

    @Override
    public void interact(Pole obstacle) {
        state = State.SLEEPING;
    }

    @Override
    public void interact(Drunkard obstacle) {
        if (obstacle.state == State.SLEEPING) {
            state = State.SLEEPING;
        }
    }

    @Override
    public void afterMove(Position from) {
        if (hasBottle && random.nextInt(30) == 0) {
            field.addActor(new Bottle(new Position(from), field));
            hasBottle = false;
        }
    }

    public enum State {
        WANDERING, SLEEPING, LYING
    }
}
