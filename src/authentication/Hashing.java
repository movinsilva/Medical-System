package authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    public static String hashPasswordMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] byteData = md.digest();

        // Convert the byte data to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : byteData) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }


}
