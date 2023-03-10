package com.moleo;

import java.text.ParseException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner read = new Scanner(System.in);
        String path;

        //Take the file path from user
        do {
            System.out.println("Please enter a valid file path");
            path = read.nextLine();
        } while (path == null);

        //parsing the database from the file
        PersonDatabase database = new PersonDatabase(path);
        System.out.println("Database added successfully!");
        database.displayAll();
        int op;

        System.out.println("------------------------------------------------------------");
        System.out.println("[1] Display all");
        System.out.println("[2] Add person");
        System.out.println("[3] Remove person");
        System.out.println("[4] See upcoming birthdays");
        System.out.println("[5] Oldest person");

        do {
            System.out.println("Make another operation or exit[0]");
            op = read.nextInt();

            switch (op) {
                case 0 -> op = 0;
                case 1 -> database.displayAll();
                case 2 -> {
                    System.out.println("Enter name and date separated by a space");
                    System.out.print("Name: ");
                    String name = read.next();
                    System.out.print("Date: ");
                    String date = read.next();
                    if (!name.isEmpty() || !date.isEmpty()) {
                        database.addPerson(name, date);
                    }
                }
                case 3 -> {
                    System.out.println("Enter the name:");
                    String name = read.next();
                    database.removePerson(name);
                }
                case 4 -> database.getUpComingBirthdays();
                case 5 -> database.oldestPerson();
            }
        } while (op != 0);
    }
}