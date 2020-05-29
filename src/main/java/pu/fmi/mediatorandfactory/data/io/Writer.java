package pu.fmi.mediatorandfactory.data.io;

public interface Writer {

    void writeLine(String line);

    void write(String text);

    void writeError(String error);
}
