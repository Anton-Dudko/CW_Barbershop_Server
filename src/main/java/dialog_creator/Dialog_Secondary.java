package dialog_creator;



import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Dialog_Secondary extends JDialog{
    private JPanel panel;
    public ArrayList<JButton> buttons = new ArrayList<>();
    public ArrayList<JTextField> textFields = new ArrayList<>();
    public ArrayList<JLabel> labels = new ArrayList<>();
    public ArrayList<JComboBox> comboBoxes = new ArrayList<>();
    public ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    public ArrayList<JRadioButton[]> radioBoxGroups = new ArrayList<>();
    public ArrayList<JTextArea> textAreas = new ArrayList<>();
    public ArrayList<JTable> tables = new ArrayList<>();

    public Dialog_Secondary(String title, int width, int height, boolean modal){
        super();
        setTitle(title);
        setModal(modal);
        setSize(width, height);
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.WHITE);
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setForeground(Color.WHITE);
        panel.setLayout(null);
        setLocationRelativeTo(null);
    }


    public void addButton(int w, int h, int x, int y, String title){
        JButton button = Dialog_Items.addButton(panel, w, h, x, y, title);
        button.setBackground(Color.MAGENTA);
        button.setForeground(Color.WHITE);
        setContentPane(panel);
        buttons.add(button);
    }


    public void addTextField(int w, int h, int x, int y){
        JTextField textField = Dialog_Items.addTextField(panel, w, h, x, y);
        setContentPane(panel);
        textFields.add(textField);
    }

    public void addLabel(int w, int h, int x, int y, String title){
        JLabel label = Dialog_Items.addLabel(panel, w, h, x, y, title);
        setContentPane(panel);
        labels.add(label);

    }

    public void addCheckBox(int w, int h, int x, int y, String title, boolean slected){
        JCheckBox checkBox = Dialog_Items.addCheckBox(panel, w, h, x, y, title, slected);
        setContentPane(panel);
        checkBoxes.add(checkBox);
    }

    public void addTextArea(int w, int h, int x, int y){
        JTextArea textArea = Dialog_Items.addTextArea(panel, w, h, x, y);
        setContentPane(panel);
        textAreas.add(textArea);
    }


    public void addRadioButtonGroupe(int w, int h, int x, int y, String[] titles){
        JRadioButton[] radioButtons = Dialog_Items.addRadioButtonGroupe(panel, w, h, x, y, titles);
        setContentPane(panel);
        radioBoxGroups.add(radioButtons);
    }

    public void addComboBox(boolean isEditable, int w, int h, int x, int y){
        JComboBox comboBox = Dialog_Items.addComboBox(panel, isEditable, w, h, x, y);
        setContentPane(panel);
        comboBoxes.add(comboBox);
    }


   public void addTable(int w, int h, int x, int y, AbstractTableModel model){
       JTable table = Dialog_Items.addTable(panel, w, h, x, y, model);          //new UserTableModel(models)

       setContentPane(panel);
       tables.add(table);
    }


}




