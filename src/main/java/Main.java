import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // just some calls
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.answer);
        System.out.println(game.makeGuess("a"));
        System.out.println("Automatic guess a");

    }
}
