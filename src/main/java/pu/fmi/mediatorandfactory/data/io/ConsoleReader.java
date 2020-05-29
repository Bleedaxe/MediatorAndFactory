package pu.fmi.mediatorandfactory.data.io;

import pu.fmi.mediatorandfactory.data.exception.InvalidMessageException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    private final BufferedReader bufferedReader;

    public ConsoleReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            String error = String.format("[ConsoleReader.readLine] Caught exception with message %s%n", e.getMessage());
            throw new InvalidMessageException(error);
        }
    }
}
