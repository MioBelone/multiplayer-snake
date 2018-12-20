package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {

    final Socket clientS;
    final DataInputStream din;
    final DataOutputStream dout;
    final Server server;

    public ClientHandler(Socket clientS, DataInputStream din, DataOutputStream dout, Server server) {
        this.clientS = clientS;
        this.din = din;
        this.dout = dout;
        this.server = server;
    }

    @Override
    public void run() {

        String msg;

        try {
            while(true) {
                msg = din.readUTF();

                if(msg.equals("/disconnect")) {
                    clientS.close();
                    System.out.println("Client " + clientS + " is disconnected!");
                    break;
                }
                server.sendToAllHandler(msg);
            }

            //Closing input-/outputstream after disconnecting
            din.close();
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToClient(String msg) {
        try {
            dout.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
