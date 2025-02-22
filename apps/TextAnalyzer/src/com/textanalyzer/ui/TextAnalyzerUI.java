package com.textanalyzer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class TextAnalyzerUI extends JPanel {
    private JTextArea textArea;
    private JButton loadButton;

    public TextAnalyzerUI() {
        setLayout(new BorderLayout());

        // Create text area
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Create button
        loadButton = new JButton("Load Text");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadText();
            }
        });

        // Add components
        add(scrollPane, BorderLayout.CENTER);
        add(loadButton, BorderLayout.SOUTH);
    }

    private void loadText() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("apps/TextAnalyzer/input/INPUT.TXT")));
            textArea.setText(content);
        } catch (IOException e) {
            textArea.setText("Error loading file: " + e.getMessage());
        }
    }
}

