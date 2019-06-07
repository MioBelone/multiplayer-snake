import model.client.Client;
import model.server.ClientHandler;
import org.junit.jupiter.api.*;

import model.server.Server;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerClientTest {

    private Server server;
    private Thread serverRunning;
    private int port = 5065;

    @BeforeAll
    void initServer() {
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
    void testSingleClientDisconnect() {
        Client client = new Client("Client");
        client.connect("localhost", port);
    }

    @Test
    void testMultipleClientSendMsg() {
        Client client1 = new Client("Client1");
        client1.connect("localhost", port);
        client1.sendMsgToServer("erster Client");

        Client client2 = new Client("Client2");
        client2.connect("localhost", port);
        client2.sendMsgToServer("zweiter Client");
    }

    @Test
    void testGetNameOfClient() {
        Client client = new Client("MioBelone");
        client.connect("localhost", port);
        client.sendMsgToServer("Testnachricht");

        //The Thread needs to wait for the client to connect completely
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<ClientHandler> clientList = server.getClientList();
        String name = clientList.get(0).getNameOfClient();

        Assertions.assertEquals("MioBelone", name);
    }

    @Test
    void testSendDirOfClient() {
        Client client = new Client("Klaus");
        client.connect("localhost", port);

        //The Thread needs to wait for the client to connect completely
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client.sendDirectionToServer("DOWN");
    }

    @AfterAll
    void endServer() {
        server.close();
        serverRunning.stop();
    }
}
