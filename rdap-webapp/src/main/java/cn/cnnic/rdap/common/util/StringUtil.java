/*
 * Copyright (c) 2012 - 2015, Internet Corporation for Assigned Names and
 * Numbers (ICANN) and China Internet Network Information Center (CNNIC)
 * 
 * All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  
 * * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * * Neither the name of the ICANN, CNNIC nor the names of its contributors may
 *  be used to endorse or promote products derived from this software without
 *  specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL ICANN OR CNNIC BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package cn.cnnic.rdap.common.util;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String util.
 * 
 * @author jiashuo
 * 
 */
public final class StringUtil {
    /**
     * search wildcard.
     */
    public static final String ASTERISK = "*";

    /**
     * default constructor.
     */
    private StringUtil() {
        super();
    }

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(StringUtil.class);
    /**
     * UTF8 encoding.
     */
    public static final String CHAR_SET_UTF8 = "UTF-8";
    /**
     * iso8859-1 encoding.
     */
    public static final String CHAR_SET_ISO8859 = "ISO8859-1";

    /**
     * encoded url with UTF-8 encoding. This will escape protocol(eg:'http://')
     * and '/'.
     * 
     * @param str
     *            string
     * @return String encoded string
     */
    public static String urlEncode(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        String result = str;
        try {
            String decodedURL = URLDecoder.decode(str, CHAR_SET_UTF8);
            decodedURL = decodedURL.replaceAll("\\\\",
                    URLEncoder.encode("\\", CHAR_SET_UTF8));
            URI uri = new URI(decodedURL);
            result = uri.toASCIIString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    /**
     * get normalization format string.
     * 
     * @param str
     *            :string.
     * @return string.
     */
    public static String getNormalization(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        return Normalizer.normalize(str, Form.NFKC);
    }
}