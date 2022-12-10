package com.ernilsson.wego.infrastructure.repository.mysql;

import com.ernilsson.wego.infrastructure.repository.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySQLUserRepository extends JpaRepository<UserEntity, String> {
}
