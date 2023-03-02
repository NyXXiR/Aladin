package com.project.aladin.repository;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback(false)
class EventRepositoryTest {

}