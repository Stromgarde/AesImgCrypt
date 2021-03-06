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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;
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
   public void encrypt(String srcPath, String destPath, String directoryPath,String key) 
   {  
	   System.out.println(srcPath+" "+destPath+" "+key);
	   String test="novalue";
	   if(key.toString().equals(test))
	   {
		   
		   
		   
		   
		   
		   FileOutputStream fos = null;
			try {
				System.out.println(destPath);
				fos = new FileOutputStream(directoryPath+"\\mynotsocsecret.txt");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
				fos.write(secretKey.getEncoded());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		    
		   
	   }
	   else
	   { 
		   FileInputStream fin = null;
			try {
				fin = new FileInputStream(key);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	int k1 = 0;
			try {
				k1 = fin.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	byte []keybyte = new byte[k1];
	    	try {
				fin.read(keybyte);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				fin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	secretKey = new SecretKeySpec(keybyte, 0, k1, "AES");
	    	
	        /***************/
	        
	    	System.out.println("Writing Key");
	    	System.out.println(secretKey.getAlgorithm());
	    	System.out.println("Size = "+raw.length);
	        System.out.println("toString = "+raw.toString());
	        
	        /*************/
		      
		   
		   
	   }
        File rawFile = new File(srcPath);
        System.out.println("SourcePath and Desftination path is :"+srcPath+" "+destPath);
        File encryptedFile = new File(destPath);
        InputStream inStream = null;
        OutputStream outStream = null;
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
        
        
        try {
        	FileInputStream f =new FileInputStream(encryptedFile);
			String checksum = DigestUtils.md5Hex(f);
			f.close();
	        DatabaseConnection db =new DatabaseConnection();
	        db.connectToDatabase(encryptedFile.getAbsolutePath(),checksum);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    public void decrypt(String srcPath, String destPath, String key) throws IOException {
    	File encryptedFile = new File(srcPath);
        File decryptedFile = new File(destPath);
    	FileInputStream f =new FileInputStream(encryptedFile);
		String checksum = DigestUtils.md5Hex(f);
		String checksum1=null;
		f.close();
        DatabaseConnection db =new DatabaseConnection();
        ResultSet rs= db.displayDataBase();
        try {
			while(rs.next())
			{
				checksum1=rs.getString("hashvalue");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(checksum.equals(checksum1))
        {
    	String KeyPath=key;
    	FileInputStream fin = new FileInputStream(KeyPath);
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
        else
        {
        	JOptionPane.showMessageDialog(null, "Cannot Decrypt. Never Encrypted File with this Key. Hash Mismatch:", "InfoBox: " + "Hmmm..???", JOptionPane.INFORMATION_MESSAGE);
        	System.exit(0);
        }
    }
}
