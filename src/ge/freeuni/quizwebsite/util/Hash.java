package ge.freeuni.quizwebsite.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String hashText(String text) throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());
        return hexToString(md.digest());
    }

    private static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (byte aByte : bytes) {
            int val = aByte;
            val = val & 0xff; // remove higher bits, sign
            if (val < 16)
                buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
}
