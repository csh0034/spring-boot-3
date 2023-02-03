package com.ask.boot.user;

import static com.ask.boot.user.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<User> findAllByNames(Collection<String> names) {
    return jpaQueryFactory.selectFrom(user)
        .where(user.name.in(names))
        .fetch();
  }

}
