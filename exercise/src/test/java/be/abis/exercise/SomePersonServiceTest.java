package be.abis.exercise;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("development")
public class SomePersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void marcelShouldBeReturned() {
        // Arrange
        Person myPerson = personService.findPerson("marcel@jajk.nl", "wascht");
        // Act
        String firstName = "Marcel";
        // Assert
        assertEquals(firstName, myPerson.getFirstName());

    }

}
