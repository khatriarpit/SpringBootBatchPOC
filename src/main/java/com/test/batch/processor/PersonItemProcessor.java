package com.test.batch.processor;

import com.test.batch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger       log           = LoggerFactory.getLogger(PersonItemProcessor.class);
    private              List<Person> previousItems = new ArrayList<>();

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName         = person.getFirstName().toUpperCase();
        final String lastName          = person.getLastName().toUpperCase();
//        final Person transformedPerson = new Person(firstName, lastName, person.getEmail(), person.getAge());
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        if (previousItems.contains(person))
            return null; // Drop duplicate
        // Save item to check for duplicates later
        previousItems.add(person);
        return new Person(firstName, lastName, person.getEmail(), person.getAge());
    }
}