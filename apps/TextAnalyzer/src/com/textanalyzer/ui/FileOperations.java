package com.textanalyzer.ui;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;

public class FileOperations {
    private String fileString;

    public String ChooseFile() {
                JFileChooser file_upload = new JFileChooser();
                file_upload.showOpenDialog(null);

                int res = file_upload.showOpenDialog(null);

                if (res == JFileChooser.APPROVE_OPTION) {
                        File file_path = new File(file_upload.getSelectedFile().getAbsolutePath());  

                        try {
                                this.SetFileString(Files.readString(file_path.toPath())); 
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                        System.out.println("Path of choosen file: " + file_path );
                }
        
                return fileString;
        }

        private void GetFileString() {
               System.out.println("Input file string: \n" + this.fileString);
        } 

        private void SetFileString(String inputText) {
                System.out.println("Input file set string: \n" + inputText); 
                this.fileString = inputText;
        }
}
