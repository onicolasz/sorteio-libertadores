package com.nicolasbarros.sorteiolibertadores.domains;

public enum Countries {
    ARGENTINA,
    BOLIVIA,
    BRAZIL,
    CHILE,
    COLOMBIA,
    COSTA_RICA,
    CUBA,
    DOMINICAN_REPUBLIC,
    ECUADOR,
    EL_SALVADOR,
    GUATEMALA,
    HAITI,
    HONDURAS,
    MEXICO,
    NICARAGUA,
    PANAMA,
    PARAGUAY,
    PERU,
    PUERTO_RICO,
    URUGUAY,
    VENEZUELA;

    public static boolean isValidCountry(Countries country) {
        for (Countries c : Countries.values()) {
            if (c.equals(country)) {
                return true;
            }
        }
        return false;
    }
}

