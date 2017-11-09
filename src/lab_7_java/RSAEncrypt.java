package lab_7_java;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class RSAEncrypt
{
  public static void main(String args[]) throws Exception
  {
    /**
     * Encrypts message provided on command line and writes to file
     * @param args
     */

    try
    {
      if (args.length != 1)
      {
        System.out.println("Please provide input as one argument. Use quotation marks if needed.");
        throw new Exception();
      }

      // File containing RSA public key
      FileInputStream keyFIS = new FileInputStream("RSAPublicKeyFile");
      ObjectInputStream keyOIS = new ObjectInputStream(keyFIS);

      // Create RSA cipher instance
      Cipher rsaCipher = Cipher.getInstance("RSA");

      // Initialize the cipher for encryption
      rsaCipher.init(Cipher.ENCRYPT_MODE, (PublicKey) keyOIS.readObject());

      keyOIS.close();
      keyFIS.close();

      // File for writing output
      FileOutputStream fos = new FileOutputStream("scrambled");

      // Read first command-line arg into a buffer.
      // This is the messge to be encrypted
      byte plaintext[] = args[0].getBytes();

      // Encrypt the plaintext
      byte[] ciphertext = rsaCipher.doFinal(plaintext);

      // Write ciphertext to file
      fos.write(ciphertext);
      fos.close();

      // Also display it in Hex on stdout
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < ciphertext.length; i++)
      {
        hexString.append(Integer.toHexString(0xF & ciphertext[i] >> 4));
        hexString.append(Integer.toHexString(0xF & ciphertext[i]));
        hexString.append(" ");
      }
      System.out.println("Plaintext: " + args[0]);
      System.out.println("Ciphertext: " + hexString.toString());

    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}