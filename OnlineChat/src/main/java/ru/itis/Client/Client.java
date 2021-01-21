package ru.itis.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public BufferedReader in;
    public PrintWriter out;
    public Socket socket;

    public Client(String host, int port) {
        try {
            Scanner scan = new Scanner(System.in);
            socket = new Socket(host, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Enter your nickname:");
            out.println(scan.nextLine());

            Resender resend = new Resender();
            resend.start();

            String str = "";
            while (!str.equals("exit")) {
                str = scan.nextLine();
                out.println(str);
            }
            resend.setStop();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Error!");
        }
    }

    private class Resender extends Thread {

        private boolean stoped;

        public void setStop() {
            stoped = true;
        }

        @Override
        public void run() {
            try {
                while (!stoped) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                System.err.println("Error while receiving messages.");
                e.printStackTrace();
            }
        }
    }
}
