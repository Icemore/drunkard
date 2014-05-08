package ru.spbau.drunkard.actors;

import ru.spbau.drunkard.Field;
import ru.spbau.drunkard.Position;

public class Beggar extends Actor {
    private RedemptionCentre redemptionCentre;
    private boolean hasBottle;
    private Bottle targetBottle;

    public Beggar(Position pos, Field field, RedemptionCentre redemptionCentre) {
        super(pos, field);

        this.redemptionCentre = redemptionCentre;
        this.hasBottle = false;
    }

    @Override
    public void makeMove(int stepNumber) {
        if (hasBottle) {
            Position moveTo = field.findFirstStepInPath(this.getPos(), redemptionCentre.getPos());
            field.moveActor(this, moveTo);
        } else {
            moveToBottle();
        }
    }

    private void moveToBottle() {
        if (targetBottle == null ||
                field.findFirstStepInPath(this.getPos(), targetBottle.getPos()) == null) {
            targetBottle = findBottle();
        }

        if (targetBottle != null) {
            Position moveTo = field.findFirstStepInPath(this.getPos(), targetBottle.getPos());
            field.moveActor(this, moveTo);
        }
    }

    private Bottle findBottle() {
        for (Actor bottle : field.findActor(Bottle.class)) {
            Position moveTo = field.findFirstStepInPath(this.getPos(), bottle.getPos());

            if (moveTo != null) {
                return (Bottle) bottle;
            }
        }

        return null;
    }

    @Override
    public void acceptVisitor(Actor visitor) {
        visitor.interact(this);
    }

    @Override
    public void interact(RedemptionCentre obstacle) {
        obstacle.acceptBeggar(this);
    }

    @Override
    public void interact(Bottle obstacle) {
        field.removeActor(obstacle);
        hasBottle = true;
    }
}
