//package echoServer;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
//
//public class ClientSocketConnection implements Connection {
//
//    Connection clientSocket;
//    SocketAddress socketAddress;
//
//    //    Inject the actual Socket as a dependency so I can use a TestSocket.
//    public ClientSocketConnection(Connection clientSocket) throws IOException {
//        this.clientSocket = clientSocket;
//        this.socketAddress = new InetSocketAddress("localhost", 3000);
//        this.clientSocket.connect(socketAddress);
//    }
//
//    @Override
//    public void closeConnection() {
//        clientSocket.close();
//    }
//}
