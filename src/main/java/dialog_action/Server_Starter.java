package dialog_action;

import dialog_creator.Dialog_Main;
import dialog_action_create.Dialog_Listener;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Server_Starter {
    private Dialog_Main prepareDialog;
    private static int id = 0;


    public void run(){
        prepareDialog = new Dialog_Main("Обработка Барбершопа Style", 400, 400);
        preparePrepareDialog();
        addDefaultData();
        addItemListener();
        setEventForButton();
        prepareDialog.setVisible(true);
    }

    private void preparePrepareDialog(){
        prepareDialog.addLabel(300, 30,50, 20, "Порт:");
        prepareDialog.addTextField(300, 30,50, 50);
        prepareDialog.textFields.get(0).setEnabled(false);
        prepareDialog.addLabel(300, 30,50, 100, "Лог:");
        prepareDialog.addTextField(300, 30,50, 130);
        prepareDialog.textFields.get(1).setEnabled(false);
        prepareDialog.addLabel(300, 30,50, 180, "Настройки:");
        prepareDialog.addTextField(300, 30,50, 210);
        prepareDialog.addRadioButtonGroupe(300, 30,50, 240, new String[]{"Автоматически", "Выбрать"});
        prepareDialog.addButton(300, 30, 50, 310, "Запуск");
    }

    private void addItemListener(){
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean isEnabled;
                if(id++ % 2 == 0) isEnabled = true;
                else isEnabled = false;
                prepareDialog.textFields.get(0).setEnabled(isEnabled);
                prepareDialog.textFields.get(1).setEnabled(isEnabled);
            }
        };
        prepareDialog.radioBoxGroups.get(0)[0].addItemListener(itemListener);
    }

    private void addDefaultData(){
        prepareDialog.textFields.get(0).setText(String.valueOf(2000));
        prepareDialog.textFields.get(1).setText("logfile.log");
        prepareDialog.textFields.get(2).setText("serverConfig.txt");
    }

    private void setEventForButton(){
        ActionListener actionListener = new Dialog_Listener(prepareDialog);
        prepareDialog.buttons.get(0).addActionListener(actionListener);
    }
}
