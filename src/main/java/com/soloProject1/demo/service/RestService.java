package com.soloProject1.demo.service;

import com.soloProject1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestService {
    private RestTemplate restTemplate;

    @Autowired
    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String url = "http://localhost:8080/admin/rest";

    public List<User> readUsers() {
        User[] users = restTemplate.exchange(url + "/all", HttpMethod.GET, null, User[].class).getBody();
        return Arrays.asList(users);
    }

    public void addUser(User user) {
        HttpEntity<User> request = new HttpEntity<>(user);
        restTemplate.postForObject(url + "/create", request, User.class);
    }

    public void updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        restTemplate.put(url + "/update", request, User.class);
    }

    public void deleteUser(Integer id) {
        restTemplate.delete(url + "/delete/" + id);
    }

    @ResponseBody
    public User getUserById(Integer id) {
        return restTemplate.getForObject(url + "/update/" + id, User.class);
    }


    public User findByUsername(String username) {
        return restTemplate.getForObject(url + "/find/byUserName/" + username, User.class);
    }
}
