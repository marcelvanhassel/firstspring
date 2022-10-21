package be.abis.exercise;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    PersonService ps;

    @Test
    public void personAddWorks() throws IOException {
        // Arrange
        int placebo = ps.getAllPersons().size();
        Address myAddress = new Address();
        myAddress.setStreet("Teststraat");
        myAddress.setNr(1);
        myAddress.setZipcode("3010");
        myAddress.setTown("Kessel-Lo");
        Company myCompany = new Company();
        myCompany.setName("TestCompany");
        myCompany.setTelephoneNumber("122333");
        myCompany.setVatNr("23");
        myCompany.setAddress(myAddress);

        Person myPerson = new Person();
        myPerson.setFirstName("TestVoornaam");
        myPerson.setLastName("TestAchternaam");
        myPerson.setAge(31);
        myPerson.setEmailAddress("test@tik.el");
        myPerson.setPassword("123eentweedrie");
        myPerson.setLanguage("Nederlands");
        myPerson.setCompany(myCompany);
        myPerson.setPersonId(ps.getAllPersons().size()+1);

        int amountOfPersonsBeforeTest = ps.getAllPersons().size();
        // Act
        ps.addPerson(myPerson);
        int amountOfPersonsAfterTest = ps.getAllPersons().size();
        // Assert
        System.out.println("delete " + myPerson.getPersonId());
        ps.deletePerson(myPerson.getPersonId());
        assertEquals(amountOfPersonsBeforeTest +1, amountOfPersonsAfterTest);


    }
}
