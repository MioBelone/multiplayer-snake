import model.client.Client;
import org.junit.jupiter.api.*;

import model.server.Server;

import java.io.IOException;

public class ServerClientTest {

    Server server;
    Thread serverRunning;
    int port = 5065;

    @BeforeEach
    public void initServer() {
        server = new Server(port);

        serverRunning = new Thread() {
            @Override
            public void run() {
                server.start();
            }
        };
        serverRunning.start();
    }

    @Test
    public void testSingleClientSendMsg() {
        Client client = new Client("Client");
        client.connect("localhost", port);
        client.sendMsgToServer("Testnachricht 456");
    }

    @Test
    public void testMultipleClientSendMsg() {
        Client client1 = new Client("Client1");
        client1.connect("localhost", port);
        client1.sendMsgToServer("erster Client");

        Client client2 = new Client("Client2");
        client2.connect("localhost", port);
        client2.sendMsgToServer("zweiter Client");
    }

    @Test
    public void getNameOfClient() {
        Client client = new Client("MioBelone");
        client.connect("localhost", port);
        Assertions.assertEquals("MioBelone", client.getName());
    }

    @AfterEach
    public void endServer() {
        server.close();
        serverRunning.stop();
    }
}
