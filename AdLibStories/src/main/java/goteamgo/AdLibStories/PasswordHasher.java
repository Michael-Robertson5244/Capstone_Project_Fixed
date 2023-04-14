package goteamgo.AdLibStories;

import org.mindrot.jbcrypt.BCrypt;
import java.security.SecureRandom;

public class PasswordHasher {

    public static void main(String[] args) {  	    	
    	
    	/* Testing Code Should return true
        String password = "password123";
        String hashedPassword = encrypt(password);
        
        System.out.println("Password match: " + checkPassword("password123", hashedPassword));
        */
    }

    public static String encrypt(String password) {
        SecureRandom rand = new SecureRandom();
        char pepper = (char) (rand.nextInt(12)); 
        int saltRounds = 12;
        String salt = BCrypt.gensalt(saltRounds);
        return BCrypt.hashpw(password+pepper, salt);
    }
    
    public static boolean checkPassword(String candidate, String hash) {
        boolean isMatch = false;
        for (int i = 0; i < 12; i++) {
            char c = (char) i;
            isMatch = BCrypt.checkpw(candidate+c, hash);
            if(isMatch) {break;}
          }
        return isMatch;
    }

}
