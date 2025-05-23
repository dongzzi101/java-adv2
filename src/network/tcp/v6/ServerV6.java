package network.tcp.v6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV6 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

        log("서버 시작");
        SessionManagerV6 sessionManger = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // shutdown 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManger);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "ShutdownHook"));

        try {
            while (true) {
                Socket socket = serverSocket.accept(); // blocking
                log("소켓 연결: " + socket);

                SessionV6 session = new SessionV6(socket, sessionManger);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }


    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdown hook start");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); // 자원정리 대기
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
