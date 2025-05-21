package network.tcp.v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // from client
                String recieved = input.readUTF();
                log("client -> server : " + recieved);

                if (recieved.equals("exit")) {
                    break;
                }

                // to client
                String toSend = recieved + " World";
                output.writeUTF(toSend);
                log("client <- server : " + toSend);
            }

            // 자원 정리
            log("연결 종료 :" + socket);
            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
