package was.v1;

import java.io.IOException;

public class ServerMain {

    private static final int port = 12345;

    public static void main(String[] args) throws IOException {
        HttpServerV1 server = new HttpServerV1(port);
        server.start();
    }
}
