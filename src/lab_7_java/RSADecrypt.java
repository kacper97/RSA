package lab_7_java;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class RSADecrypt
{
  public static void main(String args[])
  {
    /**
     * Decrypts message provided in file and writes to standard output
     */

    try
    {
      // File containing RSA private key
      FileInputStream keyFIS = new FileInputStream("RSAPrivateKeyFile");
      ObjectInputStream keyOIS = new ObjectInputStream(keyFIS);

      // Create RSA cipher instance
      Cipher rsaCipher = Cipher.getInstance("RSA");

      // Initialize the cipher for encryption
      rsaCipher.init(Cipher.DECRYPT_MODE, (PrivateKey) keyOIS.readObject());

      keyOIS.close();
      keyFIS.close();

      // Read ciphertext from file and decrypt it
      FileInputStream fis = new FileInputStream("scrambled");
      BufferedInputStream bis = new BufferedInputStream(fis);
      CipherInputStream cis = new CipherInputStream(bis, rsaCipher);

      StringBuffer plaintext = new StringBuffer();
      int c;
      while ((c = cis.read()) != -1)
        plaintext.append((char) c);
      cis.close();
      bis.close();
      fis.close();

      System.out.println("Plaintext: " + plaintext.toString());

    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}