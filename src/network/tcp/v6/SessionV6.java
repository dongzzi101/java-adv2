package network.tcp.v6;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.*;
import static util.MyLogger.log;

public class SessionV6 implements Runnable {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        try {
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
        } finally {
            sessionManager.remove(this);
            close();
        }

    }

    // 세션 종료시, 서버 종료시 동시에 호출될 수 있음
    public synchronized void close() {
        if (closed) {
            return;
        }

        closeAll(socket, input, output);
        closed = true;
        log("연결 종료  " + socket);

    }
}
