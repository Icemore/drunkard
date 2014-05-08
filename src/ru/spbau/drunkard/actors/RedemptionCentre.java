package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class RedemptionCentre extends Actor {
    private static final int beggarSpawnTimeout = 30;

    private boolean beggarIsOut;
    private int beggarVisitingTime;
    private Position beggarSpawnPos;

    public RedemptionCentre(Position pos, Field field) {
        super(pos, field);

        beggarIsOut = false;
        beggarVisitingTime = 0;
        beggarSpawnPos = new Position(pos.x + 1, pos.y);
    }

    @Override
    public void makeMove(int stepNumber) {
        if (beggarIsOut) return;

        beggarVisitingTime++;

        if (beggarVisitingTime >= beggarSpawnTimeout && field.getOnPos(beggarSpawnPos) == null) {
            field.addActor(new Beggar(beggarSpawnPos, field, this));
            beggarIsOut = true;
        }
    }

    public void acceptBeggar(Beggar beggar) {
        field.removeActor(beggar);
        beggarIsOut = false;
        beggarVisitingTime = 0;
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }
}
