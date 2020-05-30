package com.kropotov.asrd.repositories;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.titles.SystemTitle;
import com.kropotov.asrd.repositories.titles.SystemTitleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"})
        /*@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")*/}) // Оставляю это для примера если потребуется особое поведение между интеграциооными тестами
public class SystemRepositoryTest {

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SystemTitleRepository systemTitleRepository;

    User user;
    SystemTitle systemTitle;

    @Before
    public void init() {
        user = userRepository.findById(1L).get();
        systemTitle = systemTitleRepository.findById(1L).get();
    }

    @Test
    public void ifNewControlSystemSaved_thenSuccess() {
        int newSize = systemRepository.findAll().size() + 1;
        ControlSystem newControlSystem = new ControlSystem();
        newControlSystem.setNumber("90453");
        newControlSystem.setUser(user);
        newControlSystem.setTitle(systemTitle);
        systemRepository.save(newControlSystem);

        assertEquals(newSize, systemRepository.findAll().size());
    }
}
