package lesson18.api;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ApiPerson {
    public ArrayList<Person> getPersonApiRequest(int count) throws IOException, InterruptedException {
        ArrayList<Person> people = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().GET().uri(URI.create("https://randomuser.me/api")).build();
        for (int i = 0; i < count; i++) {
            String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
            people.add(parseApiResponseToPerson(res));
        }


        return people;
    }

    public Person parseApiResponseToPerson(String res) {
        Person person = new Person();
        JSONObject fullData = new JSONObject(res);
        JSONObject result = fullData.getJSONArray("results").getJSONObject(0);
        person.setGender(result.getString("gender"));
        person.setFirstName(result.getJSONObject("name").getString("first"));
        person.setLastName(result.getJSONObject("name").getString("last"));
        person.setCountry(result.getJSONObject("location").getString("country"));
        person.setEmail(result.getString("email"));
        int i;
        try {
            i = Integer.parseInt(result.getString("phone"));
        } catch (Exception e) {
            i = 0;
        }
        person.setPhone(i);
        ZonedDateTime zd = ZonedDateTime.parse(result.getJSONObject("dob").getString("date"));
        person.setDob(zd.toLocalDateTime());
        return person;
    }

    public ArrayList<Country> getPersonCountryApiRequest(int count) throws IOException, InterruptedException {
        ArrayList<Country> country = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().GET().uri(URI.create("https://randomuser.me/api")).build();
        for (int i = 0; i < count; i++) {
            String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
            country.add(parseApiResponseToPersonCountry(res));

        }

        return country;
    }

    public Country parseApiResponseToPersonCountry(String res) {
        Country country = new Country();
        JSONObject fullData = new JSONObject(res);
        JSONObject result = fullData.getJSONArray("results").getJSONObject(0);
        country.setCountry(result.getJSONObject("location").getString("country"));
        return country;
    }
}
