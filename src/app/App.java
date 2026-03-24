package app;

import comandLine.Cli;

public class App {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.controlLoop();
    }
}
