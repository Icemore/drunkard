package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

import java.util.List;

public class PoliceStation extends Actor {
    private Lantern lantern;
    private boolean policemanIsOut;
    private Position policemanSpawnPos;

    public PoliceStation(Position pos, Field field, Lantern lantern) {
        super(pos, field);
        this.lantern = lantern;
        this.policemanIsOut = false;
        policemanSpawnPos = new Position(pos.x - 1, pos.y);
    }

    @Override
    public void makeMove(int stepNumber) {
        if (policemanIsOut) return;

        List<Actor> drunkards = field.findActor(Drunkard.class, lantern.getPos(), Lantern.lightRadius);

        for (Actor drunkard : drunkards) {
            Drunkard.State state = ((Drunkard) drunkard).getState();

            if ((state == Drunkard.State.SLEEPING || state == Drunkard.State.LYING) &&
                    field.getOnPos(policemanSpawnPos) == null) {
                field.addActor(new Policeman(new Position(policemanSpawnPos), field, (Drunkard) drunkard, this));
                policemanIsOut = true;
                return;
            }
        }
    }

    public void acceptPoliceman(Policeman policeman) {
        field.removeActor(policeman);
        policemanIsOut = false;
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }
}
