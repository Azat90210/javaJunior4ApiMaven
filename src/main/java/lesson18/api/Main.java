package lesson18.api;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ApiPerson apiPerson = new ApiPerson();
        ArrayList<Person> person = null;
        ArrayList<Country> country = null;

        try {
            person = apiPerson.getPersonApiRequest(10);
            country = apiPerson.getCountryApiRequest(10, "Canada");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //PersonSort.filtrAge(person, person1 -> LocalDateTime.now().getYear() - person1.getDob().getYear() > 40);// лямбда выражение
        System.out.println(country);

    }
}
