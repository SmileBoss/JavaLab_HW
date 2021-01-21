package ru.itis.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Server {

    private List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());
    private ServerSocket server;
    public Server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server launched!");
            while (true){
                Socket socket = server.accept();
                Connection con = new Connection(socket);
                connections.add(con);
                con.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            server.close();
            synchronized (connections){
                Iterator<Connection> iterator = connections.iterator();
                while (iterator.hasNext()){
                    iterator.next().close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Connection extends Thread{

        public Socket socket;
        private String name = "";
        private PrintWriter out;
        private BufferedReader in;



        public Connection(Socket socket) {
            try {
                this.socket = socket;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        void close(){

            try {
                in.close();
                out.close();
                socket.close();

                connections.remove(this);
                if (connections.size() == 0) {
                    Server.this.closeAll();
                    System.out.println("Server switched off!");
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        iter.next().out.println(name + " cames now");
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if(str.equals("exit")) break;

                    synchronized(connections) {
                        Iterator<Connection> iter = connections.iterator();
                        while(iter.hasNext()) {
                             iter.next().out.println(name + ": " + str);
                        }
                    }
                }

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        iter.next().out.println(name + " has left");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

}
