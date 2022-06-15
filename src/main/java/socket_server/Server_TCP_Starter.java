package socket_server;

import simple_data.System_Datadown;
import entity.*;

import java.io.*;
import java.net.Socket;

public class Server_TCP_Starter extends Thread{
    private Server_TCP parental = null;
    private Socket clientAccepted;
    private ObjectInputStream sois = null;
    private ObjectOutputStream soos = null;
    private boolean isExit = false;
    private boolean isExitWitchServer = false;
    private boolean isBadExit = false;
    public boolean isHaveError = false;
    public String errorMessage = "";
    private int number;


    public Server_TCP_Starter(Socket clientAccepted, int number, Server_TCP parental, ObjectInputStream sois, ObjectOutputStream soos) {
        this.clientAccepted = clientAccepted;
        this.number = number;
        this.parental = parental;
        this.sois = sois;
        this.soos = soos;
        start();
    }

    public void run(){
        try {
            //soos = new ObjectOutputStream(clientAccepted.getOutputStream());
            while (!isExit) {
                jobWithClient();
                clearLastError();
            }
            if(!isBadExit) {
                if (isExitWitchServer) exitWitchServer();
                else exit();
            }
            interrupt();
        } catch (Exception e){
            System_Datadown.logger.error("Ошибка соединения с клиентом #" + number + ": " +  e.getMessage());
            generateError("Ошибка соединения с клиентом", e.getMessage());
        }
    }


    private void jobWithClient(){
        try {
            String command = sois.readObject().toString();
            String data = sois.readObject().toString();
            String answer = runCommand(command, data);
            System_Datadown.logger.info("Запрос от клиента #" + number + ": \"" +  command + "\" обработан");
            clearLastError();

            if(!answer.equals("exit"))
                soos.writeObject(answer);
        } catch (IOException | ClassNotFoundException  e){
            System_Datadown.logger.warn("Ошибка передачи информации серверу от клиента #" + number + ": " +  e.getMessage());
            isExit = true;  isBadExit = true;
            generateError("Ошибка передачи информации серверу", e.getMessage() );
        }
    }

    private String runCommand(String command, String data){
        switch (command){
            case "authorization": return Server_Transformer.doAuthorization(data);
            case "getCategorys": return Server_Transformer.getAll(data, CategoryIEntity.class);
            case "getHaircuts": return Server_Transformer.getAll(data, HaircutIEntity.class);
            case "getPurchases": return Server_Transformer.getAll(data, PurchaseIEntity.class);
            case "getMasters": return Server_Transformer.getAll(data, MasterIEntity.class);
            case "getUsers": return Server_Transformer.getAll(data, UserIEntity.class);
            case "getSalons": return Server_Transformer.getAll(data, SalonIEntity.class);
            case "getMastersHaircuts": return Server_Transformer.getAll(data, TransferIEntity.class);
            case "getUserCategorys": return Server_Transformer.getAll(data, ModeratorIEntity.class);
            case "delete": return Server_Transformer.delete(data);
            case "insert_categorys": return Server_Transformer.insert(data, "categorys", CategoryIEntity.class);
            case "insert_haircuts": return Server_Transformer.insert(data, "haircuts", HaircutIEntity.class);
            case "insert_purchases": return Server_Transformer.insert(data, "purchases", PurchaseIEntity.class);
            case "insert_masters": return Server_Transformer.insert(data, "masters", MasterIEntity.class);
            case "insert_users": return Server_Transformer.insert(data, "users", UserIEntity.class);
            case "insert_salons": return Server_Transformer.insert(data, "salons", SalonIEntity.class);
            case "insert_transfers": return Server_Transformer.insert(data, "transfers", TransferIEntity.class);
            case "insert_user_categories": return Server_Transformer.insert(data, "user_categories", ModeratorIEntity.class);
            case "update_categorys": return Server_Transformer.update(data, "categorys", CategoryIEntity.class);
            case "update_haircuts": return Server_Transformer.update(data, "haircuts", HaircutIEntity.class);
            case "update_purchases": return Server_Transformer.update(data, "purchases", PurchaseIEntity.class);
            case "update_masters": return Server_Transformer.update(data, "masters", MasterIEntity.class);
            case "update_users": return Server_Transformer.update(data, "users", UserIEntity.class);
            case "update_salons": return Server_Transformer.update(data, "salons", SalonIEntity.class);
            case "update_transfers": return Server_Transformer.update(data, "transfers", TransferIEntity.class);
            case "update_user_categories": return Server_Transformer.update(data, "user_categories", ModeratorIEntity.class);
            case "exit_witchout_server":  isExit = true;     return "exit";
            case "exit_witch_server": isExit = true;  isExitWitchServer = true;   return "exit";
            default: return "";
        }
    }


    public void exitWitchServer(){
        exit();
        parental.dialog.dispose();
        parental.exitServer();
    }

    public void exit(){

        parental.dialog.textFields.get(1).setText(--parental.count + " шт.");
        try {
            sois.close();
            soos.close();
            clientAccepted.close();
            parental.clientSockets.remove(this);
            clearLastError();
            System_Datadown.logger.info("Отключился клиент: #" + number);
        } catch (IOException e) {
            generateError("Ошибка закрытия соедитения с клиентом", e.getMessage());
            System_Datadown.logger.error("Ошибка закрытия соедитения с клиентом #" + number + ": " +  e.getMessage());
        }
    }


    private void generateError(String message, String javaMessage){
        isHaveError = true;
        errorMessage = message + ". Ошибка: \"" + javaMessage +"\"";
    }

    private void clearLastError(){
        isHaveError = false;
        errorMessage = "";
    }


}
