package network.tcp.autocloseable;

public class ResourceCloseMainV2 {

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
                resource2.closeEx(); // close exception 발생
            }

            if (resource1 != null) {
                resource1.closeEx(); // 호출 x
            }
        }


    }
}
