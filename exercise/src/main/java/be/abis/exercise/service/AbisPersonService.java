package be.abis.exercise.service;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class AbisPersonService implements PersonService {
    // Attributes
    @Autowired
    PersonRepository pr;

    // Constructors
    public AbisPersonService() {
    }

    // Getters and setters

    // Method implementations
    @Override
    public ArrayList<Person> getAllPersons() {
        return pr.getAllPersons();
    }

    @Override
    public Person findPerson(int id) {
        return pr.findPerson(id);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return pr.findPerson(emailAddress, passWord);
    }

    @Override
    public void addPerson(Person p) throws IOException {
        pr.addPerson(p);
    }

    @Override
    public void deletePerson(int id) {
        pr.deletePerson(id);
    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        pr.changePassword(p, newPswd);
    }
}
