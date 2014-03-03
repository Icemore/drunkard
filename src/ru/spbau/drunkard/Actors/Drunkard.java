package ru.spbau.drunkard.Actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

import java.util.Random;

public class Drunkard extends Actor {
    public enum State {
        WANDERING, SLEEPING, LYING
    }

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

        int direction = random.nextInt(4);
        Position newPos = new Position(pos.x + dx[direction], pos.y + dy[direction]);

        if (field.isValidPos(newPos)) {
            Actor obstacle = field.getOnPos(newPos);

            if (obstacle == null) {
                handleBottle();
                pos = newPos;
            } else {
                handleInteraction(obstacle);
            }
        }
    }

    void handleBottle() {
        if (!hasBottle) {
            return;
        }

        if (random.nextInt(30) == 0) {
            field.addActor(new Bottle(new Position(pos), field));
            hasBottle = false;
        }
    }

    void handleInteraction(Actor obstacle) {
        if (obstacle instanceof Bottle) {
            state = State.LYING;
        }

        if (obstacle instanceof Pole) {
            state = State.SLEEPING;
        }

        if (obstacle instanceof Drunkard) {
            Drunkard other = (Drunkard) obstacle;

            if (other.state == State.SLEEPING) {
                state = State.SLEEPING;
            }
        }
    }
}
