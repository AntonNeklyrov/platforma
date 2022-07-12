package com.neklyudov.platforma.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void prepareUser() {
        user = new User(1L,"Андрей", "Неклюдов","12345678987","andr@mail.ru","12345");
    }

    @Test
    void testGetFirstName() {
        assertEquals("Андрей", user.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Неклюдов", user.getLastName());
    }

    @Test
    void testCardNumberLength() {
        assertEquals(11,user.getCardNumber().length());
    }

    @Test
    void testSetFirstName() {
        user.setFirstName("Виктор");
        assertEquals("Виктор",user.getFirstName());
    }

}