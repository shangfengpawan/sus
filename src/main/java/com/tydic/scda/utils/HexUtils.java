package com.tydic.scda.utils;

/**
 * Created by lenovo on 2018/7/10.
 */
public class HexUtils {
    public static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     *10进制数组转62进制字符
     *
     * @param num
     * @return
     */
    public static String convertToSixtyHex(long num) {
        StringBuilder value = new StringBuilder();
        while (true) {
            int n = (int) (num % CHARS.length);
            num = (num - n) / CHARS.length;
            value.append(CHARS[n]);
            if (num <= 0)
                break;
        }
        return value.reverse().toString();

    }
}
