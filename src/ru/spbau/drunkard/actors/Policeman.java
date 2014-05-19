package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class Policeman extends Actor {
    private Drunkard goalDrunkard;
    private PoliceStation station;
    private boolean hasDrunkard;

    public Policeman(Position pos, Field field, Drunkard goalDrunkard, PoliceStation station) {
        super(pos, field);
        this.goalDrunkard = goalDrunkard;
        this.station = station;
        hasDrunkard = false;
    }

    @Override
    public void makeMove(int stepNumber) {
        Position moveTo = null;

        if (!hasDrunkard) {
            moveTo = field.findFirstStepInPath(this.getPos(), goalDrunkard.getPos());
        }

        if (hasDrunkard || moveTo == null) {
            moveTo = field.findFirstStepInPath(this.getPos(), station.getPos());
        }

        field.moveActor(this, moveTo);
    }

    @Override
    public void interact(Drunkard obstacle) {
        field.removeActor(obstacle);
        hasDrunkard = true;
    }

    @Override
    public void interact(PoliceStation obstacle) {
        obstacle.acceptPoliceman(this);
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }
}
