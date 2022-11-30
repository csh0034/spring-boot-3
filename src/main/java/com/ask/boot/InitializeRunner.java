package com.ask.boot;

import com.ask.boot.user.User;
import com.ask.boot.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitializeRunner implements ApplicationRunner {

  public final UserRepository userRepository;

  @Override
  public void run(ApplicationArguments args) {
    String password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("111111");
    userRepository.saveAll(List.of(
        User.create("user01", password),
        User.create("user02", password),
        User.create("user03", password)
    ));
  }

}
