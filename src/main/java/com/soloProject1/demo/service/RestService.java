package com.soloProject1.demo.service;

import com.soloProject1.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
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

    public List<User> readUsers() {
        String url = "http://localhost:8080/admin/rest/all";
        User[] users = restTemplate.exchange(url, HttpMethod.GET, null, User[].class).getBody();
        return Arrays.asList(users);
    }

    public void addUser(User user) {
        String url = "http://localhost:8080/admin/rest/create";
        HttpEntity<User> request = new HttpEntity<>(user);
//        Создайте новый ресурс, поместив указанный объект в URL-адрес, и вернет представление, найденное в ответе
        restTemplate.postForObject(url, request, User.class);
    }

    public User updateUser(User user) {
        String url = "http://localhost:8080/admin/rest/update";
        HttpHeaders headers = new HttpHeaders();    /*Представляет заголовки HTTP-запросов и ответов, отображая имена
                                                     заголовков строк в список значений строк.*/
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        restTemplate.put(url, request, User.class);
        return user;
    }

    public void deleteUser(Integer id) {
        String url = "http://localhost:8080/admin/rest/delete/" + id;
        restTemplate.delete(url);
    }

    public User getUserById(Integer id) {
        String url = "http://localhost:8080/admin/rest/update/" + id;
        return restTemplate.getForObject(url, User.class);
    }

    public User findByUsername(String username) {
        String url = "http://localhost:8080/admin/rest/find/byUserName" + username;
        return restTemplate.getForObject(url, User.class);
    }
}
