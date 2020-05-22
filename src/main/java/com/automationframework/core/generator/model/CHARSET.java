package com.automationframework.core.generator.model;


import static com.automationframework.core.generator.Constants.*;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

public enum CHARSET {

    CHARS_LOWER(alphaLowerPoolChar),
    CHARS_UPPER(alphaUpperPoolChar),
    NUMBERS(numericPoolChar),
    SYMBOLS(symbolsPoolChar);

    char[] charset;

    CHARSET(char[] set) {
        this.charset = set;
    }

    public char[] getCharset(){
        return this.charset;
    }

}
