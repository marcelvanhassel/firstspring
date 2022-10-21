package be.abis.exercise.repository;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
//@Profile("production")
@ConditionalOnMissingBean(FilePersonRepository.class)
public class FallbackMemoryPersonRepository implements PersonRepository {
    // Attributes
    private ArrayList<Person> allPersons = new ArrayList<>();

    // 1;John;Doe;35;jdoe@abis.be;def456;nl;Abis;016/245610;BE 0428.407.725;Diestsevest;32;3000;Leuven
    // 2;Mary;Jones;27;mjones@abis.be;abc123;fr;Abis;016/245610;BE 0428.407.725;Diestsevest;32;3000;Leuven
    // 3;Bob;Smith;53;bob.smith@oracle.com;abc986;en;Oracle;02/7191211;BE 0440.966.354;Medialaan;50;1800;Vilvoorde

    // Constructor
    public FallbackMemoryPersonRepository() {
        System.out.println("Fallback");
        Address abisAddress = new Address();
        abisAddress.setStreet("Diestsevest");
        abisAddress.setTown("Leuven");
        abisAddress.setZipcode("3000");
        abisAddress.setNr(32);
        Company abis = new Company();
        abis.setName("Abis");
        abis.setTelephoneNumber("016/245610");
        abis.setVatNr("BE 0428.407.725");
        abis.setAddress(abisAddress);
        Address oracleAddress = new Address();
        oracleAddress.setStreet("Medialaan");
        oracleAddress.setTown("Vilvoorde");
        oracleAddress.setNr(50);
        oracleAddress.setZipcode("1800");
        Company oracle = new Company();
        oracle.setName("Oracle");
        oracle.setTelephoneNumber("02/7191211");
        oracle.setVatNr("BE 0440.966.354");
        oracle.setAddress(oracleAddress);
        Person p3 = new Person();
        p3.setPersonId(3);
        p3.setFirstName("Bob");
        p3.setLastName("Smith");
        p3.setAge(53);
        p3.setEmailAddress("bob.smith@oracle.com");
        p3.setPassword("abc986");
        p3.setLanguage("en");
        p3.setCompany(oracle);

        Person p2 = new Person();
        p2.setPersonId(2);
        p2.setFirstName("Mary");
        p2.setLastName("Jones");
        p2.setAge(27);
        p2.setEmailAddress("mjones@abis.be");
        p2.setPassword("abc123");
        p2.setLanguage("fr");
        p2.setCompany(abis);

        Person p1 = new Person();
        p1.setPersonId(1);
        p1.setFirstName("John");
        p1.setLastName("Doe");
        p1.setAge(35);
        p1.setEmailAddress("jdoe@abis.be");
        p1.setPassword("def456");
        p1.setLanguage("nl");
        p1.setCompany(abis);

        this.allPersons.add(p1);
        this.allPersons.add(p2);
        this.allPersons.add(p3);

    }

    // Getters and setters

    // Implement methods
    @Override
    public ArrayList<Person> getAllPersons() {
        return this.allPersons;
    }

    @Override
    public Person findPerson(int id) {
        return allPersons.stream().filter(p->p.getPersonId()==id).findFirst().orElse(null);
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        if (emailAddress == null || passWord == null) {
            return null;
        }

        // System.out.println("persons in PersonList" + allPersons);
        Iterator<Person> iter = allPersons.iterator();

        while (iter.hasNext()) {
            Person pers = iter.next();
            if (pers.getEmailAddress().equalsIgnoreCase(emailAddress) && pers.getPassword().equals(passWord)) {
                return pers;
            }
        }
        return null;
    }


    @Override
    public void addPerson(Person p) throws IOException {
        this.allPersons.add(p);
    }

    @Override
    public void deletePerson(int id) {
        Iterator<Person> iter = allPersons.iterator();

        while (iter.hasNext()) {
            Person pers = iter.next();
            if (pers.getPersonId()==id) {
                iter.remove();
            }
        }
    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        Iterator<Person> iter = allPersons.iterator();
        while (iter.hasNext()) {
            Person pers = iter.next();
            if (pers.getEmailAddress().equals(p.getEmailAddress())) {
                pers.setPassword(newPswd);
            }
        }

    }
}
