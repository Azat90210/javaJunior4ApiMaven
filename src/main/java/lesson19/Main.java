package lesson19;

import lesson18.api.ApiPerson;
import lesson18.api.Person;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ApiPerson apiPerson = new ApiPerson();
        ArrayList<Person> people = null;
        Person person = new Person();

        try {
            people = apiPerson.getPersonApiRequest(100);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(people); //запросить 100 персонажей

        List<String> collect1 = people.stream()
                .map((p -> "ФАМИЛИЯ: " + p.getLastName() + ". ИМЯ: " + p.getFirstName()))
                .collect(Collectors.toList());               // из 100 человек вывести фамилию имя

        List<String> collect2 = people.stream()
                .map((p -> "ФАМИЛИЯ: " + p.getLastName() + ". ИМЯ: " + p.getFirstName()))
                .distinct()
                .collect(Collectors.toList());              // из 100 человек вывести фамилию имя и вывести так же только без дублей.
        System.out.println(collect2);

        collect1.removeAll(collect2);
        System.out.println(collect1);                        //печать дублей


        System.out.println("____________________");

        List<Person> female = people.stream()
                .filter(p -> p.getGender().equals("female")).collect(Collectors.toList()); //создать лист с женщинами.
        System.out.println(female);

        System.out.println("____________________");
        Person youngFemale = female.stream().max((p1, p2) -> p1.getDob().compareTo(p2.getDob())).get(); //самая молодая
        Person oldFemale = female.stream().min((p1, p2) -> p1.getDob().compareTo(p2.getDob())).get();   //самая старая                                           //самая старая

        ArrayList<Person> oldYoungFemale = new ArrayList<>();
        oldYoungFemale.add(oldFemale);
        oldYoungFemale.add(youngFemale);

        System.out.println(oldYoungFemale);

        System.out.println("____________________");

        List<Person> male = people.stream()
                .filter(p -> p.getGender().equals("male")).collect(Collectors.toList());//создать лист с мужчинами.
        System.out.println(male);

        System.out.println("____________________");

        List<Person> collect = people.stream().sorted((o1, o2) -> {
                    if (!o1.getCountry().equals(o2)) {
                        return o1.getCountry().compareTo(o2.getCountry());
                    }
                    return 0;
                })
                .collect(Collectors.toList());


    }

}
