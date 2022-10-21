package be.abis.exercise.service;

import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Profile("development")
public class SomePersonService implements PersonService {
    // Attributes
    private PersonRepository personRepository;
    private Person myPerson = new Person();

    // Constructor
    public SomePersonService() {
        myPerson.setPersonId(100);
        myPerson.setFirstName("Marcel");
        myPerson.setLastName("van Hassel");
        myPerson.setEmailAddress("marcelvanhassel@gmail.com");
        myPerson.setLanguage("Nederlands");
        myPerson.setAge(31);
        myPerson.setPassword("wachtwoord1");
    }

    // Getters and Setters
    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Implement Methods
    @Override
    public ArrayList<Person> getAllPersons() {
        return null;
    }

    @Override
    public Person findPerson(int id) {

        return myPerson;
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return myPerson;
    }

    @Override
    public void addPerson(Person p) throws IOException {

    }

    @Override
    public void deletePerson(int id) {

    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {

    }
}
