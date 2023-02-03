package com.ask.boot.user;

import java.util.Collection;
import java.util.List;

public interface UserRepositoryCustom {

  List<User> findAllByNames(Collection<String> names);

}
