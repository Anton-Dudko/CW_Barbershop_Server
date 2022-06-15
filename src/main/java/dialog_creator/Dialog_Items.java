package dialog_creator;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class Dialog_Items {

    public static JButton addButton(JPanel panel, int w, int h, int x, int y, String title){
        JButton myButton = new JButton(title);
        myButton.setSize(w, h);
        myButton.setLocation(x,y);

        panel.add(myButton);

        return myButton;
    }


    public static JTextField addTextField(JPanel panel, int w, int h, int x, int y){
        JTextField myTextField = new JTextField("");
        myTextField.setSize(w, h);
        myTextField.setLocation(x,y);

        panel.add(myTextField);

        return myTextField;
    }

    public static JLabel addLabel(JPanel panel, int w, int h, int x, int y, String title){
        JLabel myLabel = new JLabel(title);
        myLabel.setSize(w, h);
        myLabel.setLocation(x,y);

        panel.add(myLabel);

        return myLabel;
    }

    public static JCheckBox addCheckBox(JPanel panel, int w, int h, int x, int y, String title, boolean slected){
        JCheckBox myCheckBox = new JCheckBox(title, slected);
        myCheckBox.setSize(w, h);
        myCheckBox.setLocation(x,y);

        panel.add(myCheckBox);

        return myCheckBox;
    }

    public static JTextArea addTextArea(JPanel panel, int w, int h, int x, int y){
        JTextArea myTextArea = new JTextArea("");
        myTextArea.setSize(w, h);
        myTextArea.setLocation(x,y);

        panel.add(myTextArea);

        return myTextArea;
    }


    public static JRadioButton[] addRadioButtonGroupe(JPanel panel, int w, int h, int x, int y, String[] titles){

        JRadioButton[] myRadioButton = new JRadioButton[titles.length];
        ButtonGroup group = new ButtonGroup();
        boolean checked = true;

        for (int i = 0; i < titles.length; i++) {
            myRadioButton[i] = new JRadioButton(titles[i], checked);
            myRadioButton[i].setSize(w, h);
            myRadioButton[i].setLocation(x, y + i*30);

            myRadioButton[i].setBackground(Color.DARK_GRAY);
            myRadioButton[i].setForeground(Color.WHITE);

            group.add(myRadioButton[i]);
            panel.add(myRadioButton[i]);

            checked = false;
        }

        return myRadioButton;
    }

    public static JComboBox addComboBox(JPanel panel, boolean isEditable, int w, int h, int x, int y){
        JComboBox myComboBox = new JComboBox();
        myComboBox.setEditable(isEditable);
        myComboBox.setSize(w, h);
        myComboBox.setLocation(x,y);
        panel.add(myComboBox);
        return myComboBox;
    }

    public static JTable addTable(JPanel panel, int w, int h, int x, int y, AbstractTableModel model){

        JTable myTable = new JTable(model);
        myTable.setSize(w, h);
        myTable.setLocation(x,y);

        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setSize(w, h);
        scrollPane.setLocation(x,y);

        panel.add(scrollPane);
        return myTable;
    }
}
