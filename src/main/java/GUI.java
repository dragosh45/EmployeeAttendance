import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.event.*;
import java.io.File;
import java.util.Map;
import javax.swing.*;

public class GUI extends JFrame {
    File chosenFile = new File("dummy");
    public GUI() {
        //main content panel
        JPanel content = new JPanel(new FlowLayout());
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        //title label
        JLabel titleLabel = new JLabel("PONTAJ");
        Font font = titleLabel.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_REGULAR);
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes.put(TextAttribute.SIZE, 35);
        titleLabel.setFont(font.deriveFont(attributes));
        //incarca buton
        JButton bIncarca = new JButton("Incarca document excel");
        //label nume fisier
        JLabel numeFisier = new JLabel("Niciun fisier incarcat");
        bIncarca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container contentpane = new Container();
                contentpane.setLayout(new BorderLayout());
                contentpane.setSize(400, 400);
                contentpane.setVisible(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JFileChooser chooser= new JFileChooser();
                int choice = chooser.showOpenDialog(content);
                if(choice != JFileChooser.APPROVE_OPTION) return;
                chosenFile = chooser.getSelectedFile();
                //label nume fisier modificare
                numeFisier.setText("Fisierul " + chosenFile.getName() + " este incarcat");
            }
        });

        //conversie real button
        JButton bReal = new JButton("Conversie real");
        bReal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DocumentManipulation docMan = new DocumentManipulation(chosenFile);
                chosenFile = docMan.manipulateDocumentReal(chosenFile);
            }
        });
        //conversie ITM button
        JButton bITM = new JButton("Conversie ITM");
        bITM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DocumentManipulation docMan = new DocumentManipulation(chosenFile);
                chosenFile = docMan.manipulateDocumentITM(chosenFile);
            }
        });
        //Creating Exit Button
        JButton bInchide = new JButton("Inchide");
        bInchide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //adding to content
        content.add(titleLabel);
        int height= 10;
        int width = 0;
        content.add(bIncarca);
        content.add(Box.createVerticalStrut(10));
        content.add(numeFisier);
        content.add(Box.createVerticalStrut(10));
        content.add(bReal);
        content.add(bITM);
        content.add(Box.createVerticalStrut(30));
        content.add(bInchide);
        //Adding content panel to frame
        add(content);
        //((JPanel) getContentPane()).setOpaque(false);
        //Setting frame layout
        setLayout(new FlowLayout());
    }
    public static void createAndShowFrame() {
        JFrame firstFrame = new GUI();
        firstFrame.setTitle("Practical solutions for intractable problems");
        firstFrame.setSize(1200, 450);
        firstFrame.setVisible(true);
        firstFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
