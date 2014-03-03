package ru.spbau.drunkard;

import ru.spbau.drunkard.Actors.Actor;

import java.util.ArrayList;

public class Field {
    public final int width = 15;
    public final int height = 15;

    private ArrayList<Actor> actors;

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public Field() {
        this.actors = new ArrayList<Actor>();
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public Actor getOnPos(Position pos) {
        for (Actor actor : actors) {
            if (actor.getPos().equals(pos)) {
                return actor;
            }
        }

        return null;
    }

    public boolean isValidPos(Position pos) {
        return pos.x >= 0 && pos.y >= 0 && pos.x < width && pos.y < height;
    }
}
