package cn.gesgood.util;

public class HexUtil {

    private static char[] hex_char = {
            '1', '2', '3','4', '5', '6', '7', '8','9','A', 'B', 'C', 'D', 'E', 'F'
    };

    public static String toHexString(byte[] bytes) {
        if (bytes.length <=0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(hex_char[(b>>4) & 0x0f]);
            sb.append(hex_char[b & 0x0f]);
            sb.append(' ');
        }
        return sb.toString();
    }
}
