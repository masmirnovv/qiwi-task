package org.example.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public abstract class ParseUtils {

    private static DecimalFormatSymbols symbols;
    private static DecimalFormat commaFormat;

    public static double parseDoubleWithComma(String doubleString) {
        if (symbols == null) {
            symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            commaFormat = new DecimalFormat("0.#");
            commaFormat.setDecimalFormatSymbols(symbols);
        }

        try {
            return commaFormat.parse(doubleString).floatValue();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Unable to parse double from API answer: "
                    + doubleString);
        }
    }

}
