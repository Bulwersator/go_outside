/*
 * CharToByte.java
 *
 * This file is distributed under the terms of the BSD license.

Copyright (c) 2008-2009, Nicholas Killewald

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
* The name of the author(s) may not be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */

package net.exclaimindustries.tools;

/**
 * <p>
 * Static methods that convert {@code byte} arrays to {@code char}
 * arrays and vice versa.
 * </p>
 */

final class CharToByte {
    private CharToByte() {
    }

    /**
     * Converts the specified array of chars to an array of bytes.
     *
     * @param chars char array to convert
     * @return an array of bytes
     */
    public static byte[] charsToBytes(char[] chars) {
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) ((int) chars[i] & 0xFF);
        }
        return bytes;
    }

    /**
     * Converts the specified array of bytes to a String. The String will
     * consist of the hex digits of the byte array.
     *
     * @param bytes bytes to convert
     * @return a String of hex digits
     */
    public static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (((i % 32) == 0) && (i != 0)) {
                sb.append(System.lineSeparator());
            }
            String s = Integer.toHexString(bytes[i]);
            if (s.length() < 2) {
                s = "0" + s;
            }
            if (s.length() > 2) {
                s = s.substring(s.length() - 2);
            }
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Converts the specified array of bytes to an array of chars. The char
     * array will be half as long as the byte array, since chars are 2 bytes
     * each.
     *
     * @param bytes bytes to convert
     * @return a char array of hex digits
     */
    public static char[] bytesToChars(byte[] bytes) {
        char[] chars = new char[bytes.length / 2];
        for (int b = 0, c = 0; b < bytes.length; b += 2, c++) {
            int b1 = (int) bytes[b] & 0xFF;
            int b2 = (int) bytes[b + 1] & 0xFF;
            chars[c] = (char) ((b1 << 8) + b2);
        }
        return chars;
    }

}
