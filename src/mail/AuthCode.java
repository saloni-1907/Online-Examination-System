package mail;


import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthCode {
    
  private static final Random RANDOM = new SecureRandom();
  
  public static final int PASSWORD_LENGTH = 12;
  
  public String generateCode()
  {
      
      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

      String pw = "";
      for (int i=0; i<PASSWORD_LENGTH; i++)
      {
          int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);
      }
      return pw;
  }
  
  public static void main(String ar[])
  {
     
      
  }


}
    

