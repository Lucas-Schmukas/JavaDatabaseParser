package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class FileDialog extends JFrame {

    private JButton bDateioeffnen = new JButton();

    public FileDialog() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 235;
        int frameHeight = 143;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("FileDialogBeispiel");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        bDateioeffnen.setBounds(24, 16, 171, 65);
        bDateioeffnen.setText("Datei öffnen");
        bDateioeffnen.setMargin(new Insets(2, 2, 2, 2));
        bDateioeffnen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bDateioeffnen_ActionPerformed(evt);
            }
        });
        cp.add(bDateioeffnen);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        new FileDialog();
    } // end of main

    public void bDateioeffnen_ActionPerformed(ActionEvent evt) {
        java.awt.FileDialog fd = new java.awt.FileDialog(this, "Datei wählen", java.awt.FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setFile("*.txt");
        fd.setVisible(true);
        String filename =  fd.getDirectory() + fd.getFile();
        if (filename == null)
            System.out.println("You cancelled the choice");
        else {
            System.out.println("You chose " + filename);
            try {
                File fil = new File(filename);
                BufferedReader br = new BufferedReader(new FileReader(fil));
                System.out.println("file content: ");
                int r = 0;
                while ((r = br.read()) != -1) {
                    System.out.print((char) r);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

