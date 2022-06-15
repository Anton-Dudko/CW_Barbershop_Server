package socket_server;

import java.io.*;
import java.net.Socket;

public class Hook_Stop {
    private ObjectOutputStream coos;
    private Socket clientSocket;
    public boolean isHaveError = false;

    public Hook_Stop(){
        try {
            clientSocket = new Socket("127.0.0.1",11);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            coos.writeObject("BREAK");
        } catch(Exception e){
            isHaveError = true;
        } finally {
            try {
                coos.close();
                clientSocket.close();
            } catch (Exception e){
                isHaveError = true;
            }
        }
    }

}
