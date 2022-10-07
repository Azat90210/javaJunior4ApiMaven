package lesson18.api;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ApiPerson apiPerson = new ApiPerson();
        ArrayList<Person> person = null;
        ArrayList<Country> country = null;
        ArrayList<Country> country2 = null;
        try {
            person = apiPerson.getPersonApiRequest(10);
            //country = apiPerson.getPersonCountryApiRequest(200);

        } catch (Exception e) {
            e.printStackTrace();
        }

        PersonSort.filtrAge(person, person1 -> LocalDateTime.now().getYear() - person1.getDob().getYear() > 40);// лямбда выражение

//        CountrySort.filterCountry(country, new Proverka<Country>() {
//            @Override
//            public boolean test(Country country) {
//                boolean testCountry = country.getCountry().equals("Germany");
//                return testCountry;
//            }
//        });
    }
}
