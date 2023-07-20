package org.example.domain;

import org.example.utils.ParseUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Currency {

    private String code;
    private long nominal;
    private String name;
    private double value;

    private Currency() { }

    public static Currency fromXML(Node node) {
        Currency currency = new Currency();
        NodeList kvs = node.getChildNodes();
        for (int i = 0; i < kvs.getLength(); i++) {
            Node kv = kvs.item(i);
            String key = kv.getNodeName();
            String value = kv.getFirstChild().getNodeValue();
            switch (key) {
                case "CharCode" -> currency.code = value;
                case "Name" -> currency.name = value;
                case "Nominal" -> currency.nominal = Long.parseLong(value);
                case "Value" -> currency.value = ParseUtils.parseDoubleWithComma(value);
            }
        }
        return currency;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getNormalizedValue() {
        return value / nominal;
    }

}
