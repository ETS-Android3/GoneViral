package com.blackopalsolutions.goneviral.backend.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

public class GoalDaoTest {
  private GoalDao dao;

  @BeforeEach
  public void setup() {
    dao = spy(GoalDao.class);
  }
}