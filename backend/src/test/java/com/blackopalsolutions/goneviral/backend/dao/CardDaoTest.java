package com.blackopalsolutions.goneviral.backend.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

public class CardDaoTest {
    private CardDao dao;

    @BeforeEach
    public void setup() {
        dao = spy(CardDao.class);
    }
}