package server;

import client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8082);
        System.out.println("Server is running...........");

        Socket client = server.accept();

        InputStream sin = client.getInputStream();
        OutputStream sout = client.getOutputStream();

        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);

        String strline;

        while(true){

            strline = in.readUTF(); // ожидаем пока клиент пришлет строку текста.

            Process command;
            try {
                command = Runtime.getRuntime().exec(strline);

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(command.getInputStream(), "Cp866"));

                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                out.writeUTF(sb.toString());
                out.flush(); // заставляем поток закончить передачу данных.

            } catch (IOException e) {

                out.writeUTF("Error! No such a command!\n");
                out.flush(); // заставляем поток закончить передачу данных.
            }

        }
    }
}