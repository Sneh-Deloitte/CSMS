package com.CSMS.CSMS.services.impl;

import java.util.*;
import java.nio.charset.*;

class RandomString {
    static String getAlphaNumericString(int n)
    {
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString
                = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }
    public static void main(String[] args)
    {
        int n = 8;
        System.out.println(getAlphaNumericString(n));
    }
}
