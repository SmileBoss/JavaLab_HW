package ru.itis.javalab;

import com.beust.jcommander.JCommander;

public class ClientMain {


    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        Client client = new Client(args.host, args.port);
    }
}
