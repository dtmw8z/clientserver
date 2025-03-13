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
public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startClient() {
        try {
            // Read messages from the console and send to the server
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name: ");
            String name = consoleReader.readLine().trim();

            try {
                String address = "localhost";
                int port = 7007;
                clientSocket = new Socket(address, port);
                System.out.println("Connected to server at " + address);

            } catch (IOException e) {
                System.err.println("Error: Could not connect to the server. Please ensure the server is running.");
                return;
            }

            // Set up input/output streams
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Send the user's name to the server
            out.println(name);

            // Start a new thread to listen for server messages
            new Thread(new ServerListener(in)).start();


            while (true) {
                String message = consoleReader.readLine().trim();

                if (message.isEmpty()){
                    continue;
                }

                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Closing connection...");
                    break;
                }

                out.println(message);
            }

            consoleReader.close();
            closeClient();
        } catch(IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }

    private void closeClient() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            System.err.println("Error closing client resources: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }


}
