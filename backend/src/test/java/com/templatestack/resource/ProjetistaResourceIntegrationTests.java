package com.templatestack.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.templatestack.util.tests.SpringIntegrationTests;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Tag("integration")
public class ProjetistaResourceIntegrationTests extends SpringIntegrationTests {

    private static final String RESOURCE = "/v1/projetistas";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListAll() throws Exception {
        mockMvc.perform(get(RESOURCE).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$.[*].name", Matchers.containsInAnyOrder("A", "B", "C")));
    }

    @Test
    public void shouldReturnOne() throws Exception {
        mockMvc.perform(get(RESOURCE + "/{id}", 1)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("A"));
    }

    @Test
    public void shouldNotReturnOne() throws Exception {
        mockMvc.perform(get(RESOURCE + "/{id}", 4)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


}
