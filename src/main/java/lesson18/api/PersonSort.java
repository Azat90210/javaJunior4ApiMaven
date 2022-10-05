package lesson18.api;

import java.util.ArrayList;

public class PersonSort {
    public static void filtrAge(ArrayList <Person> people, Proverka <Person> proverka){
        for(Person p : people)
            if (proverka.test(p)) {
                System.out.println(p);
            }
    }
}
