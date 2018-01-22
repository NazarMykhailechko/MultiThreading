package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("192.168.1.105",8082);
        //Socket client = new Socket("192.168.6.86",8082);

        InputStream sin = client.getInputStream();
        OutputStream sout = client.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);

        Scanner scanner = new Scanner(System.in);

        String strline;

        while(true){
            System.out.println("Print command line:");
            strline = scanner.next();
            out.writeUTF(strline); // отсылаем введенную строку текста серверу.
            out.flush(); // заставляем поток закончить передачу данных.
            strline = in.readUTF(); // ждем пока сервер отошлет строку текста.
            System.out.println(strline);
        }
    }
}