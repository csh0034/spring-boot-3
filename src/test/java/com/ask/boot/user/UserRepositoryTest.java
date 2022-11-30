package com.ask.boot.user;

import com.ask.boot.InitializeRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@DataJpaTest
@Import(InitializeRunner.class)
@Slf4j
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  void findByName() {
    User user = userRepository.findByName("user01").orElseThrow();
    log.info("user: {}", user);
  }

  @Test
  void save() {
    User user = User.create("ASk", PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("sample"));
    userRepository.save(user);
    entityManager.flush();
  }

}
