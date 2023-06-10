package Run;

import Statements.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Graphic extends JFrame {

    private static Graphic graphic ;

    private JLabel jLabel = new JLabel() ;
    private JLabel jLabel2 = new JLabel();
    private TextField textField = new TextField() ;
    private JButton button = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JTextArea jTextArea = new JTextArea();
    private JScrollPane scrollPane ;
    private JComboBox<Integer> fontSize ;

    public static Graphic getGraphic() {
        if (graphic == null){
            graphic = new Graphic() ;
        }
        return graphic ;
    }

    private Graphic(){
        jLabel.setText("File Path");
        jLabel.setBounds(15,20,50,10);
        this.getContentPane().add(jLabel);

        textField.setBounds(70,15,200,20);
        this.getContentPane().add(textField);

        button.setText("Run");
        button.setBounds(280,15,60,20);
        button.addActionListener(new Handler());
        this.getContentPane().add(button);

        button2.setText("Show Code");
        button2.setBounds(240,345,100,20);
        button2.addActionListener(new Handler());
        this.getContentPane().add(button2);

        button3.setText("Open File");
        button3.setBounds(130,345,100,20);
        button3.addActionListener(new Handler());
        this.getContentPane().add(button3);

        jTextArea.setEditable(false);
        scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBounds(15,40,325,300);
        this.getContentPane().add(scrollPane);

        Integer [] integer = {10,12,14,16,18,20,22};
        fontSize = new JComboBox<Integer>(integer);
        fontSize.setBounds(80,345,40,20);
        fontSize.addActionListener(new ComboBoxHandler());
        this.getContentPane().add(fontSize);

        jLabel2.setText("Text Size");
        jLabel2.setBounds(20,345,60,20);
        this.getContentPane().add(jLabel2);


        this.setTitle("X Language Interpreter");
        this.setLayout(null);
        this.setSize(365,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private class Handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(button)){
                Program.getProgram().setPath(textField.getText());
                jTextArea.setText("");
                Program.getProgram().runProgram();
            }else if(e.getSource().equals(button2)){
                jTextArea.setText("");
                Program.getProgram().setPath(textField.getText());
                for (int i = 0 ; i <= Program.getProgram().getStatements().size()-1 ; i++){
                    addText(Program.getProgram().getStatements().get(i));
                }
            }else if(e.getSource().equals(button3)){
                File file = new File(textField.getText());
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException exp) {

                }
            }
        }
    }

    private class ComboBoxHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fontSize.getSelectedItem().equals(new Integer(10))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(10f));
            }else if (fontSize.getSelectedItem().equals(new Integer(12))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(12f));
            }else if (fontSize.getSelectedItem().equals(new Integer(14))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(14f));
            }else if (fontSize.getSelectedItem().equals(new Integer(16))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(16f));
            }else if (fontSize.getSelectedItem().equals(new Integer(18))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(18f));
            }else if (fontSize.getSelectedItem().equals(new Integer(20))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(20f));
            }else if (fontSize.getSelectedItem().equals(new Integer(22))){
                jTextArea.setFont(jTextArea.getFont().deriveFont(22f));
            }
        }
    }

    public void addText(String text){
        jTextArea.append(text + "\n");
    }

}
