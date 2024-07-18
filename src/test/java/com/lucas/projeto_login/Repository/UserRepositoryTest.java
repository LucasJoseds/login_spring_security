package com.lucas.projeto_login.Repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.lucas.projeto_login.Model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void popular(){
        User user = new User();
        user.setName("Lucas José");
        user.setEmail("lucasjs088@gmail.com");
        user.setPassword("123");
        user.setIdade(20);

        userRepository.save(user);

        User user2 = new User();
        user.setName("Pedro José");
        user.setEmail("pedro@gmail.com");
        user.setPassword("3424234");
        user.setIdade(10);

        userRepository.save(user2);

        User user3 = new User();
        user.setName("João José");
        user.setEmail("joao@gmail.com");
        user.setPassword("3424234");
        user.setIdade(21);

        userRepository.save(user3);

        User user4 = new User();
        user.setName("Maria José");
        user.setEmail("maria@gmail.com");
        user.setPassword("2423234");
        user.setIdade(25);

        userRepository.save(user4);
    }

    @Test
    void findByMaiorIdade(){

       List<User> usersList = userRepository.findByMaiorIdade();

       assertThat(usersList).hasSize(3);
    }
}
