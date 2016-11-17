package aesimagecrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptFile{
	
	KeyGenerator keyGenerator = null;
    SecretKey secretKey = null;
    Cipher cipher = null;
    byte[] raw = null;
    public EncryptFile() {
        try {
            /**
             * Create a AES key
             */
        	
            keyGenerator = KeyGenerator.getInstance("AES");
            secretKey = keyGenerator.generateKey();
            raw = secretKey.getEncoded();            
           /* 
            byte[] encoded = secretKey.getEncoded();
            FileOutputStream fos = null;
			try {
				fos = new FileOutputStream("C:/Users/Skynet-Admin/Desktop/Project-Crypto/mynotsocsecret.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				fos.write(encoded);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
            /**
             * Create an instance of cipher mentioning the name of algorithm
             *     - AES
             */
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchPaddingException ex) {
            System.out.println(ex);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
    }
	 /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Encrypts the file in srcPath and creates a file in destPath
     */
   public void encrypt(String srcPath, String destPath) {
        File rawFile = new File(srcPath);
        System.out.println("SourcePath and Desftination path is :"+srcPath+" "+destPath);
        File encryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
        DatabaseConnection db =new DatabaseConnection();
        db.connectToDatabase();
        FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("C:/Users/Skynet-Admin/Desktop/Project-Crypto/mynotsocsecret.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			fos.write(raw);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /***************/
        
    	System.out.println("Writing Key");
    	System.out.println(secretKey.getAlgorithm());
    	System.out.println("Size = "+raw.length);
        System.out.println("toString = "+raw.toString());
        
        /*************/
        
        try {
            /**
             * Initialize the cipher for encryption
             */
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(rawFile);
            outStream = new FileOutputStream(encryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * 
     * @param srcPath
     * @param destPath
     *
     * Decrypts the file in srcPath and creates a file in destPath
     * @throws IOException 
     */
    public void decrypt(String srcPath, String destPath) throws IOException {
    	
    	FileInputStream fin = new FileInputStream("C:/Users/Skynet-Admin/Desktop/Project-Crypto/mynotsocsecret.txt");
    	int k1=fin.available();
    	byte []keybyte = new byte[k1];
    	fin.read(keybyte);
    	fin.close();
    	SecretKey skey = new SecretKeySpec(keybyte, 0, k1, "AES");
    	/*****************/
    	
    	System.out.println("Reading Key");
    	System.out.println(skey.getAlgorithm());
    	System.out.println("Size = "+keybyte.length);
        System.out.println("toString = "+keybyte.toString());
    	
    	/*****************/
        File encryptedFile = new File(srcPath);
        File decryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            /**
             * Initialize the cipher for Decryption
             */
            cipher.init(Cipher.DECRYPT_MODE, skey);
            /**
             * Initialize input and output streams
             */
            inStream = new FileInputStream(encryptedFile);
            outStream = new FileOutputStream(decryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();
        } catch (IllegalBlockSizeException ex) {
            System.out.println(ex);
        } catch (BadPaddingException ex) {
            System.out.println(ex);
        } catch (InvalidKeyException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
