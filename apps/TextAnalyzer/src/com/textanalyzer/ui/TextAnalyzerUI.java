package com.textanalyzer.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TextAnalyzerUI extends JPanel {
    private JTextArea textArea;
    private JButton loadButton;

    public TextAnalyzerUI() {
        setLayout(new BorderLayout());
        FileOperations fileOps = new FileOperations();

        // Create text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create button
        loadButton = new JButton("Choose file to Analize");
        loadButton.setPreferredSize(new Dimension(5,100));
        loadButton.setFont(new Font("", Font.BOLD, 24));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = fileOps.ChooseFile();
                
            }
        });

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

   
}

