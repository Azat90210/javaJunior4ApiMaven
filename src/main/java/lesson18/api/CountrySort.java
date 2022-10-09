package lesson18.api;

import java.util.ArrayList;

public class CountrySort {
    public static void filterCountry(ArrayList<Country> country, Proverka<Country> proverka) {
        ArrayList<Country> countryList = new ArrayList<>();
        for (Country c : country) {
            if (proverka.test(c)) {
                System.out.println(c);
            }
        }
    }
}
