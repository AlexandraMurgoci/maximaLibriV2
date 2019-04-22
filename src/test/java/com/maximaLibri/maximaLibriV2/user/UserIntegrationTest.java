package com.maximaLibri.maximaLibriV2.user;

import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.repository.UserRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import com.maximaLibri.maximaLibriV2.testData.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(value = "com.maximaLibri.maximaLibriV2")
@EnableTransactionManagement
public class UserIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void AddNewUserTest() {
        TestData testData = new TestData();
        User jimmyUser = testData.getJimmyUser();
        jimmyUser = userService.save(jimmyUser);
        entityManager.flush();

        assertThat(jimmyUser.getId())
                .isNotNull();
    }

    @Test
    @Transactional
    public void getAllUsers() {
        TestData testData = new TestData();
        User jimmyUser = testData.getJimmyUser();
        jimmyUser = userService.save(jimmyUser);
        entityManager.flush();

        List<User> userList = userRepository.findAll();
        assertThat(userList).isNotNull()
                .contains(jimmyUser);
    }

}