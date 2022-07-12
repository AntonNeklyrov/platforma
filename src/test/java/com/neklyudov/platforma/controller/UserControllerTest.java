package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.User;
import com.neklyudov.platforma.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUser() throws Exception {
    User user = new User(1L,"Андрей", "Неклюдов","12345678987","andr@mail.ru","12345");
        mockMvc.perform(get("/main"))
                .andDo(print()).andExpect(status().isOk());
    }

//    private long createTestUser(){
//        User user = new User();
//        return userRepository.save(user);
//    }

}