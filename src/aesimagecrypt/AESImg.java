package aesimagecrypt;
import java.io.IOException;

import javax.swing.JFileChooser;
import aesimagecrypt.EncryptFile;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AESImg {
	
	
	String fileToEncrypt = null;
	String fileToDecrypt = null;
	String keyToDecrypt = null;
	String keyToUse = "novalue";
	String encryptedFile = "\\whatisthis.jpg";
    String decryptedFile = "\\omgitsamiracle.jpg";
    String directoryPath=null;
    EncryptFile encryptFile = new EncryptFile();

    public void  UIEncrypt(Frame mainFrame)
    {
    	// Set up grid layout
    	mainFrame.setLayout(new GridLayout(3,2));
    	
    	// Add the buttons
    	Button addImageButton = new Button("Add Image");
    	Button loadKeyButton = new Button ("Load Key (Optional)");
    	Button executeButton = new Button ("Execute");
    	
    	Label addImageLabel = new Label("");
    	Label loadKeyLabel = new Label("");
    	Label executeLabel = new Label("");
    	
    	// Add everything to layout
    	
    	mainFrame.add(addImageButton);
    	mainFrame.add(addImageLabel);
    	mainFrame.add(loadKeyButton);
    	mainFrame.add(loadKeyLabel);
    	mainFrame.add(executeButton);
    	mainFrame.add(executeLabel);
    	
    	// Add listeners to perforate appropriate actions
    	addImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);// Added New code
				//mainFrame.removeAll(); Commented out as I needed the old page to execute encryption
				FilePath fp    = new FilePath();
                JFileChooser x = fp.getFilePath();
                fileToEncrypt  = x.getSelectedFile().getAbsolutePath();
                directoryPath= x.getCurrentDirectory().getAbsolutePath();
				mainFrame.setVisible(true);
			}
		});
    	
    	loadKeyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);// Added New code
				//mainFrame.removeAll(); Commented out as I needed the old page to execute encryption
				FilePath fp = new FilePath();
                JFileChooser x=fp.getFilePath();
                keyToUse = x.getSelectedFile().getAbsolutePath();
				directoryPath= x.getCurrentDirectory().getAbsolutePath();
				mainFrame.setVisible(true);
			}
		});
    	
    	executeButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(true);
		    	// Actually encrypt the file
		        System.out.println("Starting Encryption..."+fileToEncrypt+" "+encryptedFile);
				encryptFile.encrypt(fileToEncrypt,directoryPath + encryptedFile,directoryPath,keyToUse);
		        System.out.println("Encryption completed...");
		        mainFrame.setVisible(false);
		        System.exit(0);
			}
		});
    	
    	mainFrame.setVisible(true);
    	

    }
    public void  UIDecrypt(Frame mainFrame)
    {
    	// Set up grid layout
    	mainFrame.setLayout(new GridLayout(3,2));
    	
    	// Add the buttons
    	Button addImageButton = new Button("Add Image");
    	Button loadKeyButton = new Button ("Load Key");
    	Button executeButton = new Button ("Execute");
    	
    	Label addImageLabel = new Label("");
    	Label loadKeyLabel = new Label("");
    	Label executeLabel = new Label("");
    	
    	// Add everything to layout
    	mainFrame.add(addImageButton);
    	mainFrame.add(addImageLabel);
    	mainFrame.add(loadKeyButton);
    	mainFrame.add(loadKeyLabel);
    	mainFrame.add(executeButton);
    	mainFrame.add(executeLabel);
    	
    	// Add listeners to perforate appropriate actions
    	addImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				// TODO: Add logic for setting file path
				FilePath fp    = new FilePath();
                JFileChooser x = fp.getFilePath();
				fileToDecrypt  = x.getSelectedFile().toString();
				directoryPath  = x.getCurrentDirectory().toString();
				System.out.println(fileToDecrypt+" "+directoryPath + decryptedFile);
				mainFrame.setVisible(true);
				
			}
		});
    	
    	loadKeyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				// TODO: Add logic for setting file path
				FilePath fp    = new FilePath();
                JFileChooser x = fp.getFilePath();
				keyToDecrypt  = x.getSelectedFile().toString();
				System.out.println(fileToDecrypt+" "+directoryPath + decryptedFile);
				mainFrame.setVisible(true);
			}
		});
    	
    	executeButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(true);
				//Actually Decrypts the Image
				System.out.println("Starting Decryption..."+directoryPath + decryptedFile);
				try {
					encryptFile.decrypt(fileToDecrypt,directoryPath + decryptedFile,keyToDecrypt);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        System.out.println("Decryption completed...");
		        mainFrame.setVisible(false);
		        System.exit(0);
			}
		});

    	
    	mainFrame.setVisible(true);
    }
   
}
