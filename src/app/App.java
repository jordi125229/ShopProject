package app;

import commandLine.Cli;

public class App {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.controlLoop();
    }
}
