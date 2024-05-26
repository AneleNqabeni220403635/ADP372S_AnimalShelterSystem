package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.ac.cput.domain.AnimalsAvailable;
import za.ac.cput.domain.MedicalRecord;
import za.ac.cput.factory.AnimalsAvailableFactory;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalsAvailableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private AnimalsAvailable animalsAvailable1;
    private AnimalsAvailable animalsAvailable2;

    @BeforeEach
    void setUp() {
        MedicalRecord medicalRecord1 = new MedicalRecord();
        MedicalRecord medicalRecord2 = new MedicalRecord();

        animalsAvailable1 = AnimalsAvailableFactory.createAnimalAvailable("Felis catus", "British ShortHair", "Male", 4.34, true, medicalRecord1);
        animalsAvailable2 = AnimalsAvailableFactory.createAnimalAvailable("", "", "Female", 5.4, true, medicalRecord2);
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalsAvailable1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".animalCode").isNotEmpty())
                .andDo(print());
    }

    @Test
    void read() throws Exception {
        AnimalsAvailable created = objectMapper.readValue(
                mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(animalsAvailable2)))
                        .andReturn().getResponse().getContentAsString(), AnimalsAvailable.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/{animalCode}", created.getAnimalCode()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.animalCode").value(created.getAnimalCode()))
                .andDo(print());
    }

    @Test
    void update() throws Exception {
        AnimalsAvailable created = objectMapper.readValue(
                mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(animalsAvailable1)))
                        .andReturn().getResponse().getContentAsString(), AnimalsAvailable.class);

        created.setGender("Male");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/animals/{animalCode}", created.getAnimalCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".gender").value("Male"))
                .andDo(print());
    }

    @Test
    void delete() throws Exception {
        AnimalsAvailable created = objectMapper.readValue(
                mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(animalsAvailable1)))
                        .andReturn().getResponse().getContentAsString(), AnimalsAvailable.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/animals/{animalCode}", created.getAnimalCode()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/{animalCode}", created.getAnimalCode()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalsAvailable1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animalsAvailable2)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("").isNotEmpty())
                .andDo(print());
    }
}