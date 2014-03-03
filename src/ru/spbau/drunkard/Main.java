package ru.spbau.drunkard;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Game game = new Game(printer);

        game.play();
    }
}
