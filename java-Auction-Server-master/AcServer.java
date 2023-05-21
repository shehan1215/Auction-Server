import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcServer {

    public static final int BASE_PORT = 2021;

    public static void main(String[] args) {

        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        long bigin = System.currentTimeMillis();
        while (System.currentTimeMillis() - bigin < 30000) {
        }

        // ServerController controller = new ServerController();
        DBaseStock stockDataStruct = new DBaseStock();
        OfferDB offerDatastruct = new OfferDB();
        ServerController guiController = new ServerController();
        Thread guiThread = new Thread(guiController);
        guiThread.start();
        AcServer server = new AcServer();
        try {
            server.serverLoop();
        } catch (IOException ex) {
        }
    }

    public void serverLoop() throws IOException {
        ServerSocket serverSocket = new ServerSocket(BASE_PORT);
        while (true) {
            Socket socket = serverSocket.accept(); // if error must close the socket
            ClientConnection newConnection = new ClientConnection(socket);
            newConnection.startThread();
        }
    }

}
