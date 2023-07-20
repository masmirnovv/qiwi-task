package org.example;

import org.example.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.example.utils.MapUtils.getExpected;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("Expected at least 1 argument.");
        }

        Map<String, String> parsedArgs = parseArguments(args, 1);
        switch (args[0]) {
            case "currency_rates" -> processCurrencyRatesArguments(parsedArgs);
            default -> throw new IllegalArgumentException("Unknown command: " + args[0] +
                    " ('currency_rates' supported only)");
        }

    }


    private static Map<String, String> parseArguments(String[] args, int offset) {
        Map<String, String> argsMap = new HashMap<>();
        for (int i = offset; i < args.length; i++) {
            String arg = args[i];
            // begin and end positions in default Java format: begin included & end excluded
            int keyBeginPos = arg.indexOf("--") + 2;
            int keyEndPos = arg.indexOf("=");
            int valueBeginPos = keyEndPos + 1;
            argsMap.put(
                    arg.substring(keyBeginPos, keyEndPos),
                    arg.substring(valueBeginPos));
        }
        return argsMap;
    }

    private static void processCurrencyRatesArguments(Map<String, String> parsedArgs) {
        String currencyCode = getExpected(parsedArgs, "code");
        String dateString = getExpected(parsedArgs, "date");
        try {
            Date date = DateUtils.parseDateDefault(dateString);
            CurrencyRates.printCurrencyRates(currencyCode, date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date: " + dateString +
                    ", expected date in " + DateUtils.getDefaultFormat() + " format");
        }
    }

}