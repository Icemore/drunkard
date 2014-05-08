package ru.spbau.drunkard;

import ru.spbau.drunkard.actors.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Printer {
    private Map<Drunkard.State, Character> drunkardSymbols;
    private Map<Class, Character> actorsSymbols;

    public Printer() {
        actorsSymbols = new HashMap<>();
        drunkardSymbols = new HashMap<>();

        actorsSymbols.put(Tavern.class, 'T');
        actorsSymbols.put(Pole.class, 'C');
        actorsSymbols.put(Bottle.class, 'B');
        actorsSymbols.put(Lantern.class, 'L');
        actorsSymbols.put(RedemptionCentre.class, 'R');
        actorsSymbols.put(Beggar.class, 'z');
        actorsSymbols.put(PoliceStation.class, 'S');
        actorsSymbols.put(Policeman.class, 'P');
        actorsSymbols.put(null, '.');

        drunkardSymbols.put(Drunkard.State.WANDERING, 'D');
        drunkardSymbols.put(Drunkard.State.LYING, '&');
        drunkardSymbols.put(Drunkard.State.SLEEPING, 'Z');
    }

    char getSymbol(Actor actor) {
        if (actor == null) {
            return actorsSymbols.get(null);
        }

        if (actor instanceof Drunkard) {
            return drunkardSymbols.get(((Drunkard) actor).getState());
        } else {
            return actorsSymbols.get(actor.getClass());
        }
    }

    void printField(int stepNumber, Field field) {
        int[] shift = {1, 1};
        int[] extend = {0, 1};
        char[][] graphics = new char[field.height + shift[0] + extend[0]][field.width + shift[1] + extend[1]];

        for (char[] row : graphics) {
            Arrays.fill(row, ' ');
        }

        for (int i = 0; i < field.height; i++) {
            for (int j = 0; j < field.width; j++) {
                graphics[i + shift[0]][j + shift[1]] = getSymbol(null);
            }
        }

        for (Actor actor : field.getActors()) {
            Position pos = actor.getPos();
            char symbol = getSymbol(actor);

            graphics[pos.y + shift[0]][pos.x + shift[1]] = symbol;
        }

        System.out.println("Step #" + stepNumber);
        for (char[] row : graphics)
            System.out.println(row);
        System.out.println();
    }
}
