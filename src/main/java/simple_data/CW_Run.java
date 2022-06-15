package simple_data;

import dialog_action.Server_Starter;

public class CW_Run {
    public static void main(String[] args) {
        //ServerSocketConnector connector = new ServerSocketConnector();
        //ServerSocketConnector.runServer();
        Server_Starter controller = new Server_Starter();
        controller.run();
    }
}
