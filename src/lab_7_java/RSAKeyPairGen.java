package lab_7_java;
import java.io.*;
import java.security.*;

public class RSAKeyPairGen
{
  public static void main(String[] args)
  {
    /**
     * Generate RSA key pair and write to separate files. N.B. This is just for
     * illustration. Private keys should not be stored in an unprotected form
     * like this. Better to use Keystore
     */

    try
    {
      // Generate RSA key pair
      KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();

      // File for writing private key
      FileOutputStream privateKeyFOS = new FileOutputStream("RSAPrivateKeyFile");
      ObjectOutputStream privateKeyOOS = new ObjectOutputStream(privateKeyFOS);

      // File for writing public key
      FileOutputStream publicKeyFOS = new FileOutputStream("RSAPublicKeyFile");
     // *CHANGE* >>> REMOVED THE OUTPUT 
     
      // Write the keys to respective files
      privateKeyOOS.writeObject(keyPair.getPrivate());
      publicKeyFOS.write(keyPair.getPublic().getEncoded());
      
   // *CHANGE* >>> PRINTS FORMAT AND  ALGORITHM OF PUBLIC KEY <<< 
  	System.out.println( String.format("Key format: %s, Algorithm: %s", keyPair.getPublic().getFormat(),keyPair.getPublic().getAlgorithm()));

    }
    catch (Exception e)
    {
      System.out.println(e);
    }
  }
}