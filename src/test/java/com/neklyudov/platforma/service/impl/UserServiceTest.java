package com.neklyudov.platforma.service.impl;

import com.neklyudov.platforma.model.Role;
import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import com.neklyudov.platforma.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    public void init (){
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testFindUserById() {
        User user =  new User(1L, "Андрей", "Неклюдов", "12345678987","andr@mail.ru","12345",
        new Role(1L, "Администратор"));
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));

        User userDto = userService.getUserById(1L);
        verify(userRepository, times(1)).findById(anyLong());

        assertNotNull(userDto);

        String firstName = userDto.getFirstName();;
        String lastName = userDto.getLastName();
        assertEquals(user.getFirstName(), firstName);
        assertEquals(user.getLastName(), lastName);
    }

}