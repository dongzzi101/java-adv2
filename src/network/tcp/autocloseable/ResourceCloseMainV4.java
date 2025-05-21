package network.tcp.autocloseable;

public class ResourceCloseMainV4 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("callException 예외 처리");

            /*Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("throwable = " + throwable);
            }*/
            throw new RuntimeException(e);

        } catch (CloseException e) {
            System.out.println("closeException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CloseException, CallException {
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2")) {
            resource1.call();
            resource2.callEx(); // Call exception
        } catch (CallException e) {
            System.out.println("Ex : " + e);
            throw e;
        }
    }
}
