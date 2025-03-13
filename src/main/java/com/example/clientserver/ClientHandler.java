/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author dtmw8z
 */
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userName = in.readLine();
            System.out.println("Client connected: " + userName);
            out.println("Welcome to the chatroom, " + userName + "!");


            Server.broadcastMessage(userName + " has joined the chat!", this);


            String message;
            while ((message = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Send message to all clients
                Server.broadcastMessage(userName + ": " + message, this);
            }

            Server.broadcastMessage(userName + " has left the chat.", this);
            System.out.println("Client disconnected: " + userName);

            Server.removeClient(this);
            close();

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing client resources: " + e.getMessage());
        }
    }
}