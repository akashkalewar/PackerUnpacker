import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException; 
import java.util.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.InvalidKeyException;



class CryptoException extends Exception {
 
    public CryptoException() {
    }
 
    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
/**
 * A utility class that encrypts or decrypts a file.
 */

class CryptoUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Name :          Encrypt
    //  Input :         String File File
    //  Output :        void
    //  Description :   This function is used to encrypt a file
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////// 

    public static void encrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Name :          Decrypt
    //  Input :         String File File
    //  Output :        void
    //  Description :   This function is used to decrypt a file
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    public static void decrypt(String key, File inputFile, File outputFile)
            throws CryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Name :          doCrypto
    //  Input :         int String File
    //  Output :        void
    //  Description :   This function performs the actual task of encrypting/decrypting based on cipher Mode
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    private static void doCrypto(int cipherMode, String key, File inputFile,
            File outputFile) throws CryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
             
            byte[] outputBytes = cipher.doFinal(inputBytes);
             
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
             
            inputStream.close();
            outputStream.close();
             
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}

