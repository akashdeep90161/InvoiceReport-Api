package com.walkover.user.api.controller;

import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.dao.repository.UserRepository;
import com.walkover.user.api.resources.v1.UserResource;
import com.walkover.user.api.utils.commons.JsonUtils;
import maker.UserMaker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/**
 * @author Akash Deep Gupta
 **/


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        {
                "classpath:configuration/mvc-dispatcher.xml"
        }
)
@Transactional
public class UserControllerTest {

    @Autowired
    private UserController controller;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Before
    public void init() {
        userRepository.deleteAll();
    }


    @Test
    public void createUser() throws Exception {
        User user = make(a(UserMaker.User));
        userRepository.save(user);
        String API = "/users";

        MvcResult result = mockMvc.perform(post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("user", user)
                .content(JsonUtils.toJson(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body.name").value(user.getName()))
                .andReturn();
    }

    @Test
    public void updateUser() throws Exception {
        User user = make(a(UserMaker.User));
        User updateuser = new User();
        updateuser.setId(101);
        updateuser.setEmailId("abcdxyz@gmail.com");
        updateuser.setName("abcdxyz");
        userRepository.save(user);
        //  UserResource userResources = new UserResource(user);
        String API = "/users";


        MvcResult result = mockMvc.perform(put(API)
                .contentType(MediaType.APPLICATION_JSON)
                .requestAttr("user", updateuser)
                .content(JsonUtils.toJson(updateuser)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body.name").value(updateuser.getName()))
                .andReturn();
    }

    @Test
    public void getUser() throws Exception {
        User user = make(a(UserMaker.User));
        userRepository.save(user);
        UserResource userResources = new UserResource(user);
        String emailId = userResources.getEmailId();

        String API = "/users/" + emailId;
        mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void getAllUser() throws Exception {

        User user = make(a(UserMaker.User));
        userRepository.save(user);
        List<User> users = (List<User>) userRepository.findAll();

        String API = "/users";
        mockMvc.perform(get(API).contentType(MediaType.APPLICATION_JSON)
                .requestAttr("users", users))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.body[0].emailId").value(users.get(0).getEmailId()))
                // .andExpect(jsonPath("$.body.emailId").value(user.getEmailId()))
                .andReturn();
    }

    @Test
    public void deleteUser() throws Exception {
        User user = make(a(UserMaker.User));
        userRepository.save(user);
        UserResource userResources = new UserResource(user);
        String emailId = userResources.getEmailId();
        String API = "/users/" + emailId;
        mockMvc.perform(
                delete(API)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}