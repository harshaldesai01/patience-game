package model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Suite enum representing each of the 4 suites in the Deck
 */
public enum Suite {
    CLUBS('C', "Clubs", false),
    DIAMONDS('D', "Diamonds", true),
    HEARTS('H', "Hearts", true),
    SPADES('S', "Spades", false);

    private final char symbol;
    private final String name;
    private final boolean isRed;

    Suite(char symbol, String name, boolean isRed) {
        this.symbol = symbol;
        this.name = name;
        this.isRed = isRed;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isRed() {
        return isRed;
    }

    @Override
    public String toString() {
        return name + '(' + symbol + ')';
    }

    /**
     * Return all Suite Symbols
     * @return list of all symbols
     */
    public static List<Character> getAllSymbols() {
        List<Character> symbols = new ArrayList<>();
        for(Suite suite: Suite.values()) {
            symbols.add(suite.getSymbol());
        }
        return symbols;
    }


    /**
     * Get the Suite enum from a given symbol.
     *
     * @param symbol The symbol representing a suite.
     * @return The corresponding Suite enum value.
     * @throws IllegalArgumentException if the symbol does not correspond to a valid Suite.
     */
    public static Suite fromSymbol(char symbol) {
        for (Suite suite : Suite.values()) {
            if (suite.getSymbol() == symbol) {
                return suite;
            }
        }
        throw new IllegalArgumentException("Invalid suite symbol: " + symbol);
    }

}
