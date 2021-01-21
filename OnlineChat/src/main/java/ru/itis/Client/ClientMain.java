package ru.itis.Client;

import com.beust.jcommander.JCommander;
import ru.itis.Args;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
