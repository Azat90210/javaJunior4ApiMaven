package lesson18.api;

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

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().GET()
                .uri(URI.create(String.format("https://randomuser.me/api?results=%d", count))).build();

        String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
        return parseApiResponseToPerson(res);


    }

    public ArrayList<Person> parseApiResponseToPerson(String res) {
        ArrayList<Person> people = new ArrayList<>();

        JSONObject fullData = new JSONObject(res);
        int count = fullData.getJSONObject("info").getInt("results");
        for (int i = 0; i < count; i++) {
            Person person = new Person();
            JSONObject result = fullData.getJSONArray("results").getJSONObject(i);
            person.setGender(result.getString("gender"));
            person.setFirstName(result.getJSONObject("name").getString("first"));
            person.setLastName(result.getJSONObject("name").getString("last"));
            person.setCountry(result.getJSONObject("location").getString("country"));
            person.setEmail(result.getString("email"));
            int phone;
            try {
                phone = Integer.parseInt(result.getString("phone"));
            } catch (Exception e) {
                phone = 0;
            }
            person.setPhone(i);
            ZonedDateTime zd = ZonedDateTime.parse(result.getJSONObject("dob").getString("date"));
            person.setDob(zd.toLocalDateTime());
            people.add(person);
        }
        return people;
    }


    public ArrayList<Country> getCountryApiRequest(int count, String countryC) throws IOException, InterruptedException {
        int a = 0;
        ArrayList<Country> countryFind = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().GET()
                .uri(URI.create(String.format("https://randomuser.me/api?results=%d", count))).build();
        String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();

        if (res.equals(countryC)) {
            System.out.println(parseApiResponseToCountry(res));
        }

        return parseApiResponseToCountry(res);
    }

    public ArrayList<Country> parseApiResponseToCountry(String res) {
        ArrayList<Country> country = new ArrayList<>();

        JSONObject fullData = new JSONObject(res);
        int count = fullData.getJSONObject("info").getInt("results");
        for (int i = 0; i < count; i++) {
            Country c = new Country();
            JSONObject result = fullData.getJSONArray("results").getJSONObject(i);
            c.setCountry(result.getJSONObject("location").getString("country"));
            country.add(c);
        }
        return country;
    }
}

