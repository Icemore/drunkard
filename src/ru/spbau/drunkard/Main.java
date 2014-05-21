package ru.spbau.drunkard;

public class Main {
    public static void main(String[] args) {
        Printer printer;
        Field field;

        if (args.length > 0 && args[0].charAt(0) == 'r') {
            printer = new RectangularPrinter();
            field = new RectangularField();
        } else {
            printer = new HexagonalPrinter();
            field = new HexagonalField();
        }


        Game game = new Game(printer, field);

        game.play();
    }
}
