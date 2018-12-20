package model.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientS;
    private DataInputStream din;
    private DataOutputStream dout;

    public static void main(String[] args) {
        Client c = new Client();
        c.connect("localhost", 5065);
        c.sendMsgToServer("Testnachricht");
    }

    public void connect(String ip, int port) {
        try {
            clientS = new Socket(ip, port);

            din = new DataInputStream(clientS.getInputStream());
            dout = new DataOutputStream(clientS.getOutputStream());

            Thread msgReading = new Thread() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            String msg;
                            msg = din.readUTF();
                            System.out.println(msg);
                            writeMsgToGUI(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            msgReading.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToServer(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    private void writeMsgToGUI(String msg) {
        //msg must be passed to the presenter
    }
}
