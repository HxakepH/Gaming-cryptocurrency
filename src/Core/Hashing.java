package Core;
//Скрипт для хеширования SHA256
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Hashing {

	public static String CryptSHA256(int index, String previousHash, String time, String date) {
        String data = index+previousHash+time+date;
        Hashing sj = new Hashing();
        String hash = sj.getSHA256Hash(data);
        return hash;
    }
    private String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }
}