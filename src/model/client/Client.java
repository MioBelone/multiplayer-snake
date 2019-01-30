package model.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket clientS;
    private String name;
    private DataInputStream din;
    private DataOutputStream dout;

    public Client(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Client c = new Client("Testclient");
        c.connect("localhost", 5065);
        c.sendMsgToServer("Testnachricht 123");
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

            //Send name of Client to Server
            sendMsgToServer("/sysinf clientName:" + name);
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

    public void sendDirectionToServer(String dir) {
        sendMsgToServer("/sysinf dir:" + dir);
    }

    private void writeMsgToGUI(String msg) {
        //msg must be passed to the presenter
    }
}
