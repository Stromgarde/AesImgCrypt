package aesimagecrypt;
import aesimagecrypt.EncryptFile;

public class AESImg {
	
	String fileToEncrypt = "Capture.JPG";
    String encryptedFile = "whatisthis.jpg";
    String decryptedFile = "omgitsamiracle.jpg";
    String directoryPath = "C:/Users/Skynet-Admin/Desktop/Project-Crypto/";
    EncryptFile encryptFile = new EncryptFile(directoryPath);

    public void  UIEncrypt()
    {
        System.out.println("Starting Encryption...");
        encryptFile.encrypt(directoryPath + fileToEncrypt,
                directoryPath + encryptedFile);
        System.out.println("Encryption completed...");
    }
    public void  UIDecrypt()
    {
        System.out.println("Starting Decryption...");
        encryptFile.decrypt(directoryPath + encryptedFile,
                directoryPath + decryptedFile);
        System.out.println("Decryption completed...");
    }
   
}
