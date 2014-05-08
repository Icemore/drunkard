package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

abstract public class Actor {
    protected Position pos;
    protected Field field;

    protected Actor(Position pos, Field field) {
        this.pos = pos;
        this.field = field;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    abstract public void makeMove(int stepNumber);

    abstract public void acceptVisitor(Actor visitor);

    public void interact(Bottle obstacle) {
    }

    public void interact(Drunkard obstacle) {
    }

    public void interact(Pole obstacle) {
    }

    public void interact(Tavern obstacle) {
    }

    public void interact(Lantern obstacle) {
    }

    public void interact(RedemptionCentre obstacle) {
    }

    public void interact(Beggar obstacle) {
    }

    public void interact(Policeman obstacle) {
    }

    public void interact(PoliceStation obstacle) {
    }

    public void afterMove(Position from) {
    }
}
