package com.dev.fullstackdemo.web;

import com.dev.fullstackdemo.domain.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //TODO: create a user before ech test, data gets wiped out before each run
    // uses h2 db by default
    @BeforeEach
    public  void setup() {
        CustomUserDetails customUserDetailsOne = new CustomUserDetails("admin", "user", "test123@abcxyz.com", "pw","test123@abcxyz.com", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        CustomUserDetails customUserDetailsTwo = new CustomUserDetails("test", "user", "test456@abcdef.com", "pw","test123@mnjjjrd.com", Arrays.asList("ROLE_USER"));
    }

    //TODO: not working correctly
    @Test
    @WithMockUser
    public void testSignin() throws Exception {
        mockMvc.perform(post("/api/v1/auth/signup")
                .content("{\"username\": \"test123@abcxyz.com\", \"password\": \"pw\", \"email\": \"test123@abcxyz.com\"}")
                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andDo(print()).andExpect(status().isOk());
    }
}