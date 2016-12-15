import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;


public class PasswordHelper
{
    public static String generateHash(String text)
    {
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");        
            hasher.update(text.getBytes());           
            return DatatypeConverter.printHexBinary(hasher.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException nsae) {
            return nsae.getMessage();
        }     
    }


}