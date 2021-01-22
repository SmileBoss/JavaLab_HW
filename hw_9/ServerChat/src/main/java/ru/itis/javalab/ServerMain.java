package ru.itis.javalab;

import com.beust.jcommander.JCommander;

public class ServerMain {
    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Server server = new Server(args.port);
    }
}
