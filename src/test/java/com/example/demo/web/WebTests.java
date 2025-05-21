package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.StatistiqueImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WebTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatistiqueImpl statistique;

    @Test
    public void testAjoutVoiture() throws Exception {
        // Création de l'objet voiture
        Voiture voiture = new Voiture("f", 100);

        // Conversion de l'objet en JSON
        ObjectMapper mapper = new ObjectMapper();
        String voitureJson = mapper.writeValueAsString(voiture);

        // Test de l'appel REST
        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(voitureJson))
                .andExpect(status().isOk());

        // Vérification que le service a bien été appelé
        verify(statistique, times(1)).ajouter(voiture);
    }
}