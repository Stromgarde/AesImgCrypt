package aesimagecrypt;
import java.io.IOException;
import aesimagecrypt.EncryptFile;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AESImg {
	
	String fileToEncrypt = "Capture.JPG";
    String encryptedFile = "whatisthis.jpg";
    String decryptedFile = "omgitsamiracle.jpg";
    String directoryPath = "C:/Users/Skynet-Admin/Desktop/Project-Crypto/";
    EncryptFile encryptFile = new EncryptFile(directoryPath);

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
				mainFrame.removeAll();
				// TODO: Add logic for setting file path
			}
		});
    	
    	loadKeyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.removeAll();
				// TODO: Add logic for setting key
			}
		});
    	
    	executeButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.removeAll();
				// TODO: Add logic for setting file path
			}
		});
    	
    	mainFrame.setVisible(true);
    	
    	// Actually encrypt the file
        System.out.println("Starting Encryption..."+directoryPath + encryptedFile);
        encryptFile.encrypt(directoryPath + fileToEncrypt,directoryPath + encryptedFile);
        System.out.println("Encryption completed...");
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
				mainFrame.removeAll();
				// TODO: Add logic for setting file path
			}
		});
    	
    	loadKeyButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.removeAll();
				// TODO: Add logic for setting key
			}
		});
    	
    	executeButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.removeAll();
				// TODO: Add logic for setting file path
			}
		});

    	
    	mainFrame.setVisible(true);
    	
        System.out.println("Starting Decryption..."+directoryPath + decryptedFile);
        try {
			encryptFile.decrypt(directoryPath + encryptedFile,directoryPath + decryptedFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Decryption completed...");
    }
   
}
