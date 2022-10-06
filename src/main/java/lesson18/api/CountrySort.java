package lesson18.api;

import java.util.ArrayList;

public class CountrySort {
    public static void filterCountry(ArrayList<Country> country, Proverka<Country> proverka) {
        ArrayList<Country> countryList = new ArrayList<>();
        int a = 5;
        for (Country c : country) {
            if (proverka.test(c)) {
                while (a > 0) {
                    countryList.add(c);

                    a--;
                }
            }

        }
        System.out.println(countryList);
    }
}
