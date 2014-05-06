package ru.spbau.drunkard;

import ru.spbau.drunkard.actors.Actor;
import ru.spbau.drunkard.actors.Pole;
import ru.spbau.drunkard.actors.Tavern;

import java.util.ArrayList;

public class Game {
    Field field;
    Printer printer;

    public Game(Printer printer) {
        this.printer = printer;
    }

    public void play() {
        init();

        for (int i = 0; i < 500; i++) {
            makeMove(i);
            printer.printField(i, field);
        }
    }

    public void init() {
        field = new Field();

        field.addActor(new Pole(new Position(7, 7), field));
        field.addActor(new Tavern(new Position(9, -1), field));
    }

    public void makeMove(int stepNumber) {
        ArrayList<Actor> actors = new ArrayList<>(field.getActors());

        for (Actor actor : actors) {
            actor.makeMove(stepNumber);
        }
    }
}
