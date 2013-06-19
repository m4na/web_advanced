package com.web_advanced.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class FileUploader implements Receiver, SucceededListener {
    public File file;
    public String directory;
    String path = "/tmp/server/web_advanced/";
    
    public FileUploader(String directory){
    	this.directory = directory;
    }
    
    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        // Create upload stream
        FileOutputStream fos = null; // Stream to write to
        try {
        	new File(path+directory).mkdirs();
            // Open the file for writing.
            file = new File(path+directory+"/"+ filename);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            return null;
        }
        return fos; // Return the output stream to write to
    }

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		// TODO Auto-generated method stub
		
	}
};
