package socket_server;

import dialog_creator.Dialog_Main;
import simple_data.System_Datadown;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server_TCP extends Thread{

    public ArrayList<Server_TCP_Starter> clientSockets = new ArrayList<>();
    private ServerSocket serverSocket = null;
    public int count = 0;
    private int port;
    public String lastError = "";
    public Dialog_Main dialog;

    public Server_TCP(int port, String logFile, Dialog_Main dialog) {
        this.port = port;
        this.dialog = dialog;
        System.setProperty("my.log", "C:\\electrical_shop\\" + logFile);
        start();
    }

    public void run(){
        try {
            System_Datadown.logger.info("Сервер запущен");
            serverSocket = new ServerSocket(port);
            while(true) {
                Socket tempSocket = serverSocket.accept();
                ObjectInputStream nois = new ObjectInputStream(tempSocket.getInputStream());
                ObjectOutputStream noos = new ObjectOutputStream(tempSocket.getOutputStream());

                if(!nois.readObject().toString().equals("BREAK")){
                    clientSockets.add(new Server_TCP_Starter(tempSocket, count, this, nois, noos));
                } else {
                    serverSocket.close();
                    break;
                }
                dialog.textFields.get(1).setText(++count + " шт.");
                System_Datadown.logger.info("Подключился клиент: #" + (count-1) );
            }
        } catch (Exception e) {
            lastError = "Ошибка сокета: \"" + e.getMessage() + "\"";
            System_Datadown.logger.error("Ошибка сокета: \"" + e.getMessage() + "\"");
        }
    }

    public void exitServer() {
        try {

            for (int i = 0; i < clientSockets.size(); i++) {
                clientSockets.get(i).exit();
            }
            System_Datadown.logger.info("Сервер завершил работу корректно");
        } catch (Exception e) {
            lastError = "Ошибка сокета: \"" + e.getMessage() + "\"";
            System_Datadown.logger.error("Сервер завершил с ошибкой сокета: \"" + e.getMessage() + "\"");
        }
        Hook_Stop hookStop = new Hook_Stop();
        interrupt();
    }


}
