package pu.fmi.mediatorandfactory.data.io;

public class ConsoleWriter implements Writer {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public void writeError(String error) {
        System.err.println(error);
    }
}
