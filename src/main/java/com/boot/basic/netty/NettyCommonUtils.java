package com.boot.basic.netty;

/**
 * @author HTuoZhou
 */
public class NettyCommonUtils {

    private static final String CHAR_TO_BYTE = "0123456789ABCDEF";

    /**
     * 字节转16进制字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length);
        String str;
        for (int i = 0; i < bytes.length; i++) {
            str = Integer.toHexString(0xFF & bytes[i]);
            if (str.length() < 2) {
                sb.append(0);
            }
            sb.append(str.toUpperCase()).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 16进制字符串转字节
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.replaceAll(" ", "");

        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        char[] ch = hexString.toCharArray();
        for (int i = 0; i < len; i++) {
            int p = 2 * i;
            bytes[i] = (byte) (charToByte(ch[p]) << 4 | charToByte(ch[p + 1]));
        }
        return bytes;
    }

    /**
     * 字符转换为字节
     */
    private static byte charToByte(char c) {
        return (byte) CHAR_TO_BYTE.indexOf(c);
    }

}
