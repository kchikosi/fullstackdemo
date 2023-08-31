package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomUserDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //    @Autowired
    private CustomUserDetails user;
    @Autowired
    private CustomUserController controller;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {

        user = new CustomUserDetails("Dee", "Money", "test123@abc.com", "pw","test123@abc.com", Arrays.asList("Admin"));
        mockMvc = MockMvcBuilders
                .standaloneSetup(CustomUserDetailsControllerTest.class)
                .build();
    }

    @Test
    void registerUserCredential() throws Exception {
        MvcResult result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    void getAllUsers() throws Exception {
        MvcResult result = mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    @DisplayName("Context loads")
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}