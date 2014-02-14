/**
 * HexFraction.java
 * Copyright (C)2008 Nicholas Killewald
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

import java.math.BigDecimal;

/**
 * Contains a static method for parsing a hex string as if it were the
 * fractional part of a number and returning its fractional float value.
 *
 * @author Nicholas Killewald
 */
public class HexFraction {
    /**
     * Converts a string, presumably the fractional part of a hex number, into
     * its fractional decimal counterpart. Don't feed it a negative.
     *
     * @param s
     *            the hex string to convert
     * @return a float value of the hex string
     * @throws NumberFormatException
     *             parsing error with the string
     */
    public static double calculate(String s) throws NumberFormatException {
        // We're dealing with values to the precision of 1/(16^16). I think
        // BigDecimal is quite called for in this case.
        BigDecimal curvalue = new BigDecimal(0);

        // We need to parse the string one character at a time and continuously
        // calculate each digit's fractional hex value. Note, this WILL hurt.
        for (int i = 0; i < s.length(); i++) {
            // Get the hexit.
            String hexit = s.substring(i, i + 1);
            // Make it into an integer.
            int part = Integer.parseInt(hexit, 16);
            // Now divide it out.
            BigDecimal d1 = new BigDecimal(part);
            BigDecimal d2 = new BigDecimal(16);
            d2 = d2.pow(i + 1);
            curvalue = curvalue.add(d1.divide(d2));
            // Then repeat for the entire string.
        }
        // Finally, return at will!
        return curvalue.doubleValue();
    }
}
