package com.test.batch.writer;

import com.test.batch.model.Person;
import com.test.batch.repository.PersonRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
public class PersonItemWriter implements ItemWriter<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void write(List<? extends Person> items) throws Exception {
        personRepository.save(items);
    }
}
