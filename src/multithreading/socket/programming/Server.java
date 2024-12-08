package multithreading.socket.programming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket; 

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1414);
            System.out.println("Server is Wating for Client");

            boolean Connection = true;

            while (Connection) {
                Socket client = server.accept();
                System.out.println("Client is Connected");
                DataOutputStream write = new DataOutputStream(client.getOutputStream());
                DataInputStream read = new DataInputStream(client.getInputStream());

                new Thread(() -> {
                    try {
                        write.writeUTF("Server Ready to Help you Calculate 2 Number Result:");
                        while (true) {
                            String ClientSay = read.readUTF();
                            String[] Operation = ClientSay.split(" ");
                            if (Operation.length == 3) {
                                float Result = Calculator(Float.parseFloat(Operation[0]), Float.parseFloat(Operation[1]), Operation[2]);
                                write.writeUTF("Result: "+Float.toString(Result));
                            } else {
                                write.writeUTF("Please Enter Like This Number1 Number2 Sum Do not Use Spetial Character!");
                            }
                        }

                    } catch (IOException | NumberFormatException ex) {
                        System.out.println(ex.getMessage());
                    }
                }).start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private static float Calculator(float num1, float num2, String Operation) {
        float result = 0.0f;
        switch (Operation.toLowerCase()) {
            case "sum":
                result = num1 + num2;
                break;
            case "modules":
                result = num1 % num2;
                break;
            case "division":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                result = num1 / num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiplication":
                
                result = num1 * num2;
                break;
            default:
                result = 0.0f;
        }
        return result;
    }

}
