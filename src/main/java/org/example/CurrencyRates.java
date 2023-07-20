package org.example;

import org.example.domain.Currency;
import org.example.utils.CbrApiUtils;
import org.example.utils.DateUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.util.Date;

import static org.example.utils.DateUtils.formatDateCbrAns;

public abstract class CurrencyRates {

    public static void printCurrencyRates(String currencyCode, Date date) {
        Document doc = CbrApiUtils.getContent(date);
        Element root = doc.getDocumentElement();
        String ansDateString = root.getAttribute("Date");
        try {
            Date ansDate = DateUtils.parseDateCbrAns(ansDateString);
            if (!date.equals(ansDate)) {
                System.out.printf("ВНИМАНИЕ: нет даннных на дату %s. Будут возвращены данные на %s\n",
                        formatDateCbrAns(date), formatDateCbrAns(ansDate));
            }

            NodeList currencies = root.getChildNodes();
            boolean currencyFound = false;
            for (int i = 0; !currencyFound && i < currencies.getLength(); i++) {
                Currency currency = Currency.fromXML(currencies.item(i));
                if (currencyCode.equals(currency.getCode())) {
                    System.out.printf("%s (%s): %f\n",
                            currency.getCode(),
                            currency.getName(),
                            currency.getNormalizedValue());
                    currencyFound = true;
                }
            }

            if (!currencyFound) {
                System.err.printf("Не нашлось данных для валюты %s на %s\n",
                        currencyCode, formatDateCbrAns(ansDate));
            }
        } catch (ParseException e) {
            throw new RuntimeException("Internal error (unable to parse date from API answer)");
        }
    }

}
