package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Objects;


public class FileDialog extends JFrame {

    private String fileString = "";

    List statements;
    private JButton bDateioeffnen = new JButton();
    private JButton bStringwandeln = new JButton();

    public FileDialog() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 600;
        int frameHeight = 200;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("FileDialogBeispiel");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        bDateioeffnen.setBounds(25, 25, 150, 50);
        bDateioeffnen.setText("Datei öffnen");
        bDateioeffnen.setMargin(new Insets(2, 2, 2, 2));
        bDateioeffnen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bDateioeffnen_ActionPerformed(evt);
            }
        });

        bStringwandeln.setBounds(200, 25, 150, 50);;
        bStringwandeln.setText("String wandeln");
        bStringwandeln.setMargin(new Insets(2, 2, 2, 2));
        bStringwandeln.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bStringwandeln_ActionPerformed(evt);
            }
        });
        cp.add(bDateioeffnen);
        cp.add(bStringwandeln);

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
                fileString = "";
                while ((r = br.read()) != -1) {
                    fileString += ((char) r);
                }
                System.out.println(fileString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void bStringwandeln_ActionPerformed(ActionEvent evt) {
        fileString = fileString.replace(" ", "");
        String[] stringByLine = fileString.split("\\n");
        System.out.print(fileString);
        //for ( String line: stringByLine ) {
        //    if (Objects.equals(line, stringByLine[0])) {
        //        setDatabaseName(line);
        //    } else {
        //      setTableStructure(line);
        //}
    }
    private void setDatabaseName(String databaseName ) {
        statements.add("CREATE DATABASE IF EXISTS " + databaseName + ";", 0);
        statements.add("USE DATABASE " + databaseName + ";", 1);
    }
    private void setTableStructure(String tableStatement) {
        String[] twoPartString = tableStatement.split("\\(");
        statements.add("CREATE TABLE " + twoPartString[0] + "(");
    }
}