package com.example.stringtemplates;

import static java.lang.StringTemplate.RAW;
import static java.lang.StringTemplate.STR;
import static java.util.FormatProcessor.FMT;

public class Main {
    
    public static final java.lang.StringTemplate.Processor<String> SQL = st -> {
        var frags = st.fragments();
        var vals = st.values();
        var sb = new StringBuilder();
        for (int i = 0; i < vals.size(); i++) {
            sb.append(frags.get(i));
            Object v = vals.get(i);
            if (v instanceof Identifier id) {
                sb.append(id.name());
            } else if (v == null) {
                sb.append("NULL");
            } else if (v instanceof Number || v instanceof Boolean) {
                sb.append(v);
            } else {
                String s = v.toString().replace("'", "''");
                sb.append("'").append(s).append("'");
            }
        }
        sb.append(frags.get(frags.size() - 1));
        return sb.toString();
    };

    public static   void main(String[] args) {
        basicSTR();
        fmtExample();
        rawExample();
        customSqlProcessorExample();
    }

    static void basicSTR() {
        String name = "Alice";
        int count = 3;
        String msg = STR."Hello, \{name}! You have \{count} new messages.";
        System.out.println("STR  : " + msg);
    }

    static void fmtExample() {
        double price = 12.5;
        int qty = 3;
        String line = FMT."Total due: $%,.2f\{price * qty}";
        System.out.println("FMT  : " + line);
    }

    static void rawExample() {
        String who = "World";
        var raw = RAW."Hello, \{who}!";
        System.out.println("RAW  : fragments=" + raw.fragments() + ", values=" + raw.values());
        System.out.println("RAW  : interpolate() => " + raw.interpolate());
    }

    static void customSqlProcessorExample() {
        String userInput = "O'Brien";
        Identifier table = new Identifier("users");
        Identifier column = new Identifier("name");

        String sql = SQL."select * from \{table} where \{column} = \{userInput}";
        System.out.println("SQL  : " + sql);
    }
    
    record Identifier(String name) {}
}
