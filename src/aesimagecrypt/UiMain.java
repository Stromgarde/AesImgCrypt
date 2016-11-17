package aesimagecrypt;	

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.*;
import java.awt.event.*;

public class UiMain {
	
    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;
  
	public UiMain(){
	    prepareGUI();
	 }
	
  public static void main(String[] args) {
	  UiMain  awtControlDemo = new UiMain();
      awtControlDemo.showButtonDemo();
  }

     private void prepareGUI(){
        mainFrame = new Frame("AesImgCrypt");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }        
        });    
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
        statusLabel = new Label();        
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(350,100);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
     }

     private void showButtonDemo(){
        headerLabel.setText("AES Image Encrypting and Decrypting Application"); 

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");
        Button dataBaseButton = new Button("Open Database");

        encryptButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              statusLabel.setText("TODO: Launch Encrypt Menu");
           }
        });

        decryptButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              statusLabel.setText("TODO: Launch Decrypt Menu");
           }
        });

        dataBaseButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
              statusLabel.setText("TODO: Launch Database Display");
           }
        });

        controlPanel.add(encryptButton);
        controlPanel.add(decryptButton);
        controlPanel.add(dataBaseButton);       

        mainFrame.setVisible(true);  
     }
  }