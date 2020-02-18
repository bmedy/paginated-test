package io.github.bmedy.paginatedtest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TestApi {

    @GetMapping("/api")
    public Page<Contact> get(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "50") int size) {
        final List<Contact> contacts = loadData();
        final Page<Contact> contactPage = new Page<>(contacts, size, page);
        return contactPage;
    }

    private List<Contact> loadData() {
        try (FileReader reader = new FileReader("data.json")){
            final ObjectMapper om = Jackson2ObjectMapperBuilder.json()
                    .build();
            final Contact[] contacts = om.readValue(reader, Contact[].class);
            return Arrays.asList(contacts);
        }catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
