package dialog_action;

import dialog_creator.Dialog_Main;
import socket_server.Server_TCP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Server_Runner {
    private Dialog_Main dialogMain;
    private Dialog_Main mainDialog;
    private int port;
    private String logFile;
    private Server_TCP runner;


    public Server_Runner(int port, String logFile, String configFile, Dialog_Main dialogMain){
        this.dialogMain = dialogMain;
        this.port = port;
        this.logFile = logFile;
        mainDialog = new Dialog_Main("Обработка Барбершопа Style", 500, 270);
        prepareMainDialog();
        prepareItems(port, logFile, configFile);
        setEventForButtons();
        setEventForWindow();
        mainDialog.setVisible(true);
    }

    private void prepareMainDialog(){
        mainDialog.addLabel(400, 30,50, 20, "Настройки:");
        mainDialog.addTextField(400, 30,50, 50);
        mainDialog.addLabel(400, 30,50, 100, "Клиенты:");
        mainDialog.addTextField(400, 30,50, 130);
        mainDialog.addButton(400, 30, 50, 180, "Остановить");
    }

    private void prepareItems(int port, String logFile, String configFile){
        mainDialog.textFields.get(0).setEnabled(false);
        mainDialog.textFields.get(0).setText("Порт: " + port + " Лог: \"" + logFile + "\" Настройки: \"" + configFile +  "\"");
        mainDialog.textFields.get(1).setEnabled(false);
        mainDialog.textFields.get(1).setText("0 шт.");
    }

    private void setEventForButtons(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runner.exitServer();
                if(!runner.lastError.equals("")){
                    JOptionPane.showMessageDialog(mainDialog, runner.lastError, "Ошибка!",JOptionPane.PLAIN_MESSAGE);
                }
                dialogMain.setVisible(true);
                mainDialog.dispose();
            }
        };
        mainDialog.buttons.get(0).addActionListener(actionListener);
    }

    private void setEventForWindow(){
        WindowListener windowListener = new WindowListener() {
            public void windowActivated(WindowEvent event) {}
            public void windowClosed(WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified(WindowEvent event) {}
            public void windowOpened(WindowEvent event) {
                runner = new Server_TCP(port, logFile, mainDialog);
                if(!runner.lastError.equals("")){
                    JOptionPane.showMessageDialog(mainDialog, runner.lastError, "Ошибка!",JOptionPane.PLAIN_MESSAGE);
                    mainDialog.dispose();
                }
            }
            public void windowClosing(WindowEvent event) {
                runner.exitServer();
                if(!runner.lastError.equals("")){
                    JOptionPane.showMessageDialog(mainDialog, runner.lastError, "Ошибка!",JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
        mainDialog.addWindowListener(windowListener);
    }

    public Dialog_Main getMainDialog() {
        return mainDialog;
    }

    public static void setCountForDialog(Dialog_Main dialog, int count){
        dialog.textFields.get(2).setText(count + " шт.");

    }

    public static void showErrorMessage(Dialog_Main dialog, String message){
        JOptionPane.showMessageDialog(dialog, message, "Ошибка!",JOptionPane.PLAIN_MESSAGE);
    }

}
