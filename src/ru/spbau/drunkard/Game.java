package ru.spbau.drunkard;

import ru.spbau.drunkard.actors.*;

import java.util.ArrayList;

public class Game {
    Field field;
    Printer printer;

    public Game(Printer printer, Field field) {
        this.printer = printer;
        this.field = field;
    }

    public void play() {
        init();

        for (int i = 0; i < 500; i++) {
            makeMove(i);
            printer.printField(i, field);
        }
    }

    public void init() {
        field.clear();

        field.addActor(new Pole(new Position(7, 7), field));
        field.addActor(new Tavern(new Position(9, -1), field));
        field.addActor(new RedemptionCentre(new Position(-1, 4), field));
        Lantern lantern = new Lantern(new Position(10, 3), field);
        field.addActor(lantern);
        field.addActor(new PoliceStation(new Position(15, 3), field, lantern));
    }

    public void makeMove(int stepNumber) {
        ArrayList<Actor> actors = new ArrayList<>(field.getActors());

        for (Actor actor : actors) {
            actor.makeMove(stepNumber);
        }
    }
}
