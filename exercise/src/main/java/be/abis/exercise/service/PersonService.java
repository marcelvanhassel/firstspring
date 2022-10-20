package be.abis.exercise.service;

import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.ArrayList;

public interface PersonService {
    public ArrayList<Person> getAllPersons();
    public Person findPerson(int id);
    public Person findPerson(String emailAddress, String passWord);
    public void addPerson(Person p) throws IOException;
    public void deletePerson(int id);
    public void changePassword(Person p, String newPswd) throws IOException;
}
