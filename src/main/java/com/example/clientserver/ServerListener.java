/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.clientserver;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author dtmw8z
 */
public class ServerListener implements Runnable {

    private final BufferedReader in;

    public ServerListener(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                    System.out.println(inputLine);  // Display the server message without prefix

            }
        } catch (IOException e) {
            System.err.println("Connection closed by server: " + e.getMessage());
            
    }

}
}
