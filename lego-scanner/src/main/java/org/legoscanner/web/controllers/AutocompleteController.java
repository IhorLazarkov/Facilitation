package org.legoscanner.web.controllers;

import com.google.gson.Gson;
import info.facilitator.bean.LegoBean;
import info.facilitator.persister.SessionProvider;
import io.swagger.annotations.ApiOperation;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class AutocompleteController {

    private List<LegoBean> legos = new LinkedList<>();

    @Autowired
    private Function<Session, List<LegoBean>> fetcher;

    @ApiOperation(value="Gets all elements that contains search parameter", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public String autocomplete(@RequestParam("name") String name) {
        SessionProvider.withSession(session -> {
            if (legos.isEmpty())
                legos.addAll(fetcher.apply(session));
        });

        final Set<String> result = legos.stream()
                .map(LegoBean::getLegoName)
                .filter(legoName -> legoName.contains(name))
                .collect(Collectors.toSet());

        Gson gson = new Gson();
        String response = gson.toJson(result);

        return response;
    }
}
