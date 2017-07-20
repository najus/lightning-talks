package com.vote.talks.controller;

import com.vote.talks.domain.Client;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ResourceController {

    @Autowired
    private MongoCollection users;

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/resource")
    public Map<String, Object> home(@AuthenticationPrincipal UserDetails userDetails) {
        Client client = users.findOne("{#: #}", Client.USERNAME, userDetails.getUsername()).as(Client.class);
        Map<String, Object> model = new HashMap<>();
        model.put("roles", client.getRoles());
        return model;
    }
}
