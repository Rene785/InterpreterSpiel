package my_project.view;

import javax.swing.*;

public class InputGui {
    private JTextField textfield1;
    private JPanel mainPanel;
    private JButton früchteHinzufügenButton;

   public InputGui(){
       JFrame frame = new JFrame();
       frame.add(mainPanel);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setVisible(true);
   }
}
