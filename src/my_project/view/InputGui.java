package my_project.view;

import my_project.control.ProgramController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputGui {
    private JTextField textfield1;
    private JPanel mainPanel;
    private JButton früchteHinzufügenButton;
    private JButton playButton;
    private JScrollBar scrollBar1;
    private JScrollPane scrollPane;
    private JTextArea textArea1;
    String inputFromPlayer;

    public InputGui(ProgramController pc){

       JFrame frame = new JFrame();
       frame.add(mainPanel);
       frame.pack();
       frame.setSize(500,500);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setVisible(true);
       inputFromPlayer = textArea1.getText();

       playButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               inputFromPlayer = textArea1.getText();
               pc.scanAndParse(inputFromPlayer);
           }
       });

       scrollPane.setHorizontalScrollBar(scrollBar1);
   }
}
