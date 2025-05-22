package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class SessionV5 implements Runnable {

    private final Socket socket;

    public SessionV5(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                socket;
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
        {
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

        } catch (IOException e) {
            log(e);
        }

        log("연결 종료 - socket isClosed: " + socket.isClosed());
    }
}
