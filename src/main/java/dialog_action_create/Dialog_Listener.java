package dialog_action_create;


import dialog_creator.Dialog_Main;
import simple_data.Valid_Session;
import dialog_action.Server_Runner;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Dialog_Listener implements ActionListener {
    private Dialog_Main dialog;
    private Dialog_Main mainDialog;
    private int port;
    private String logFileName;
    private String configFileName;
    private String errors = "";

    public Dialog_Listener(Dialog_Main dialog){
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        errors = "";
        if(isGetDataFromFile()) {
            Server_Runner creator = new Server_Runner(port, logFileName, configFileName, dialog);
            mainDialog  = creator.getMainDialog();
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, "Ошибки:\r\n" + errors, "Ошибка!",JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean isGetDataFromFile(){
        if (dialog.radioBoxGroups.get(0)[1].isSelected()){
            if(isOkUserData(dialog.textFields.get(0).getText(), dialog.textFields.get(1).getText(), dialog.textFields.get(2).getText())) {
                return writeDataInFile();
            } else return false;
        } else {
            return isOkFile() ? writeDataInFile() : false;
        }
    }


    private boolean isOkUserData(String tempPort, String tempLogFile, String tempConfigFile){
        if(!Valid_Session.isPort(tempPort)){
            errors += "   Порт в недопустимых диапазонах;\r\n";
        } else{
            port = Integer.valueOf(tempPort);
        }
        if (!Valid_Session.isLOG(logFileName = tempLogFile)){
            errors += "   Не log-файл для Логирования;\r\n" + logFileName;
        }
        if (!Valid_Session.isTXT(configFileName = tempConfigFile)){
            errors += "   Не txt-файл для Настроек Сервера;\r\n";
        }
        return errors.equals("") ? true : false;

    }

    private boolean writeDataInFile(){
        try (
                FileWriter fout = new FileWriter(configFileName, false);
        ){
            fout.write(port + "@@@" + logFileName + "@@@" + configFileName);
            return true;
        } catch (Exception e){

            errors += "   Не удаётся записать в Файл!";
            return false;
        }
    }


    private boolean isOkFile(){
        if(Valid_Session.isTXT(configFileName = dialog.textFields.get(2).getText())){
            return read();
        } else {
            errors += "   Не txt-файл для Настроек Сервера;\r\n";
            return false;
        }
    }

    private boolean read(){
        try(
                FileReader fin  = new FileReader(configFileName);
        ){
            Scanner scan = new Scanner(fin);
            String myAnswer = scan.next(), params[];

            params = myAnswer.split("@@@");

            return isOkUserData(params[0], params[1], params[2]) ? true : false;
        } catch (Exception e){

            errors += "   Не удаётся прочитать из Файла!";
            return false;
        }
    }
}
