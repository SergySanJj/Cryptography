package com.sergeiyarema.sha1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SHA1Test {
    @Test
    void stringEncoding(){
        String text1 = "";
        String text2 = "some text";
        String text3 = "2ome text";

        assertNotNull(SHA1.encode(text1));
        assertNotNull(SHA1.encode(text2));
        assertNotNull(SHA1.encode(text3));

        assertNotEquals(SHA1.encode(text1),SHA1.encode(text2));
        assertNotEquals(SHA1.encode(text1),SHA1.encode(text3));
    }
}