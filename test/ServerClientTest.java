import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.server.Server;

public class ServerClientTest {

    Server server;
    int port = 5056;

    @BeforeEach
    public void initServer() {
        server = new Server(port);
        server.start();
    }

    @Test
    public void testSingleServerClient() {

    }

    @AfterEach
    public void endServer() {
        server.stop();
    }
}
