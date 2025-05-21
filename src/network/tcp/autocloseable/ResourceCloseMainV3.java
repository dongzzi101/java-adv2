package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("callException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("closeException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CloseException, CallException {
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // callException

        } catch (CallException e) {
            System.out.println("ex : " + e);
            throw e;
        } finally {
            if (resource2 != null) {
                try {
                    resource2.closeEx(); // close exception 발생
                } catch (CloseException e) {
                    // close()에서 발생한 예외는 버림
                    System.out.println("close ex: " + e);
                }

            }

            if (resource1 != null) {
                try {
                    resource1.closeEx();
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }


    }
}
