package com.econotravel.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        experienceRepository.deleteAll();
        addSampleData();
    }

    @Test
    void returnsTheExistingExperiences() throws Exception {

        mockMvc.perform(get("/api/experiences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("Paseo por el Montseny")))
                .andExpect(jsonPath("$[1].name", equalTo("Visita a la sagrada familia")))
                .andDo(print());
    }

    @Test
    void createsNewExperiences() throws Exception {

        experienceRepository.deleteAll();

        mockMvc.perform(post("/api/experiences")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Paseo en Bici por el Montseny\"}")
        ).andExpect(status().is(200));

        var experiences = experienceRepository.findAll();

        assertThat(experiences, contains(
                hasProperty("name", is("Paseo en Bici por el Montseny"))
        ));
    }

    @Test
    void getsExistingCategories() throws Exception {

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo("0")))
                .andExpect(jsonPath("$[0].name", equalTo("Cultural")))
                .andExpect(jsonPath("$[1].id", equalTo("1")))
                .andExpect(jsonPath("$[1].name", equalTo("Naturaleza")))
                .andDo(print());
    }

    @Test
    void getsExperiencesByCategory() throws Exception {

        mockMvc.perform(get("/api/categories/0/experiences"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Visita a la sagrada familia")))
                .andDo(print());
    }

    private void addSampleData() {

        List<Category> categories = List.of(
                new Category(0L, "Cultural"),
                new Category(1L, "Naturaleza")
        );

        categoryRepository.saveAll(categories);

        Experience experience1 = new Experience("Paseo por el Montseny");
        experience1.setCategory(categoryRepository.getById(1L));

        Experience experience2 = new Experience("Visita a la sagrada familia");
        experience2.setCategory(categoryRepository.getById(0L));

        List<Experience> experiences = List.of(experience1, experience2);
        experienceRepository.saveAll(experiences);

    }

}
