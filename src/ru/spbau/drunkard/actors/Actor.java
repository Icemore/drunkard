package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

abstract public class Actor {
    static final int dx[] = {1, 0, -1, 0};
    static final int dy[] = {0, 1, 0, -1};

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
}
