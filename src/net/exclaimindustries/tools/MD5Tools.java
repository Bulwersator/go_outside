/*
 * MD5Tools.java
 * Copyright (C) 2006 Nicholas Killewald
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <code>MD5Tools</code> consists of a helper method for the common gruntwork
 * tasks commonly associated with MD5 hashing. Most common of these would be the
 * hashing of a simple string.
 *
 * @author Nicholas Killewald
 */
public class MD5Tools {

    /**
     * Hashes a string through the MD5 algorithm. If something goes wrong with
     * getting an MD5 instance, this returns an empty string.
     *
     * @param input
     *            String object to hash
     * @return the MD5 hash of the input
     */
    public static String MD5hash(String input) {
        MessageDigest diggy;

        try {
            diggy = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // No, seriously, if this fails, we're all doomed.
            return "";
        }

        diggy.update(CharToByte.charsToBytes(input.toCharArray()));

        return CharToByte.bytesToString(diggy.digest());
    }

}
