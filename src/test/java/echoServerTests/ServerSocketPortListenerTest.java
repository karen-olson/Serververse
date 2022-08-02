package echoServerTests;

import echoServer.ServerSocketPortListener;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ServerSocketPortListenerTest {

    @Test
    void connectsToPort3000() throws IOException {
        ServerSocketPortListener portListener = new ServerSocketPortListener();

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    portListener.accept();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        t1.start();

        new Socket("localhost", 3000);

        portListener.stopListening();
    }

    @Test
    void closesConnection() throws IOException {
        ServerSocketPortListener portListener = new ServerSocketPortListener();

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    portListener.accept();
//                } catch (SocketException e) {
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        t1.start();

        portListener.stopListening();

        ConnectException connectExceptionError = assertThrows(ConnectException.class, () -> {
            new Socket("localhost", 3000);
        });

        assertEquals(connectExceptionError.getMessage(), "Connection refused");
    }
}
