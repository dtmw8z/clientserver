/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 * @author dtmw8z
 */
public class Server {

    private static final int port = 7007;
    private static final Set<ClientHandler> clients = new CopyOnWriteArraySet<>();
    private ServerSocket serverSocket;


    @SuppressWarnings("InfiniteLoopStatement")
    public void startServer() {
        try {

            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    try {
                        System.out.println("Shutting down server...");
                        serverSocket.close();
                        for (ClientHandler client : clients) {
                            client.close();
                        }
                    } catch (IOException e) {
                        System.err.println("Error closing server socket: " + e.getMessage());
                    }
                }
            ));

            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);

                Thread clientThread = new Thread(clientHandler);
                clientThread.start();

            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
        System.out.println(message);  // Display the server message with prefix
    }

    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }


    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }   

}
