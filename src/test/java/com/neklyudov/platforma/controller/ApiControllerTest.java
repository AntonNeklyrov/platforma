package com.neklyudov.platforma.controller;

import com.neklyudov.platforma.model.Subscription;
import com.neklyudov.platforma.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {


    @Autowired
    private SubscriptionRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllSubscriptions() throws Exception {
        List<Subscription> subscriptions = repository.findAll();
        mockMvc.perform(get("/api/qqq"))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(subscriptions.size() )));

    }


//    @Test
//    void testDeleteSubscriptions() throws Exception {
//        Subscription subscription = new Subscription(1L, 1L, 1L, 200, 20, )
//        mockMvc.perform(post("/subscriptions/delete/{id}"))
//                .andDo(print());
//    }

}