package ru.job4j.it.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean runService = true;
            while (runService) {
                Socket socket = server.accept();
                try (OutputStream write = socket.getOutputStream();
                     BufferedReader read = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = read.readLine();
                    String answer = "";
                    while (!str.isEmpty()) {
                        if (str.contains("Exit")) {
                            runService = false;
                            server.close();
                            break;
                        } else if (str.contains("Hello")) {
                            answer = "Hello";
                            break;
                        } else {
                            answer = "What";
                            System.out.println(str);
                            str = read.readLine();
                        }
                    }
                    write.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    write.write(answer.getBytes());
                }
            }
        }
    }
}
