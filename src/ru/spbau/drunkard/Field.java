package ru.spbau.drunkard;

import ru.spbau.drunkard.actors.Actor;

import java.util.*;

public class Field {
    public final int width = 15;
    public final int height = 15;

    private Map<Position, Actor> actors;

    public Collection<Actor> getActors() {
        return actors.values();
    }

    public Field() {
        this.actors = new HashMap<>();
    }

    public void addActor(Actor actor) {
        actors.put(actor.getPos(), actor);
    }

    public void addActor(Actor actor, Position pos) {
        actor.setPos(pos);
        actors.put(pos, actor);
    }

    public Actor getOnPos(Position pos) {
        return actors.get(pos);
    }

    public void moveActor(Actor actor, Position to) {
        Position from = actor.getPos();

        if(actors.containsKey(to)) {
            Actor obstacle = actors.get(to);
            obstacle.acceptVisitor(actor);
        }
        else {
            if(isValidPos(to)) {
                actors.remove(from);
                actors.put(to, actor);
                actor.setPos(to);
                actor.afterMove(from);
            }
        }
    }

    public boolean isValidPos(Position pos) {
        return pos.x >= 0 && pos.y >= 0 && pos.x < width && pos.y < height;
    }
}
