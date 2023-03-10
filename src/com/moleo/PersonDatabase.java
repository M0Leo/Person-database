package com.moleo;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonDatabase {
    private HashMap<String, Date> persons = new HashMap<>();

    public PersonDatabase(String path) {
        File file = new File(path);
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineArray = line.split(";");
                var d = Arrays.copyOfRange(lineArray,1, lineArray.length);
                persons.put(lineArray[0], format.parse(String.join("/", d)));
            }
        }catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        } finally {
            assert scan != null;
            scan.close();
        }
    }

    public void displayAll() {
        System.out.println("/--------------Persons Table---------------/");
        System.out.println("Name\t Date of birth");
        for (Map.Entry<String,Date> entry : persons.entrySet()) {
            System.out.print(entry.getKey() + "\t");
            System.out.printf("%tF %n", entry.getValue());
        }
    }

    public void oldestPerson() {
        Optional<String> firstKey = persons.keySet().stream().findFirst();
        Date maxDate = persons.get(firstKey.get());
        for (var i : persons.keySet()) {
            if (maxDate.after(persons.get(i))) {
                maxDate = persons.get(i);
            }
        }
        System.out.printf("%tF %n", maxDate);
    }

    public void addPerson(String name, String date) throws ParseException  {
        if (persons.containsKey(name.toLowerCase()))
            System.out.println("This person is already exist.");
        Date formattedDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
        persons.put(name.toLowerCase(), formattedDate);
    }

    public void removePerson(String name) {
        if (!persons.containsKey(name.toLowerCase()))
            System.out.println("Name doesn't exist.");
        persons.remove(name);
    }

    public void getUpComingBirthdays() {
        Date today = new Date();
        for (Map.Entry<String,Date> entry : persons.entrySet()) {
            long diff = (today.getTime() - entry.getValue().getTime()) / (1000 * 60 * 60 * 24) % 365;
            System.out.print(entry.getKey() + "\t");
            System.out.println(diff + " Days");
        }
    }
}
