/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multithreading.socket.programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 1414);
            System.out.println("Cient is Connected with Server");
            boolean Connection = true;
            DataOutputStream write = new DataOutputStream(client.getOutputStream());
            DataInputStream read = new DataInputStream(client.getInputStream());
            Scanner input = new Scanner(System.in);

            while (Connection) {
                System.out.println(read.readUTF());
                String message = input.nextLine();
                write.writeUTF(message);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
