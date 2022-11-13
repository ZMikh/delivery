package ru.mikhailova.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@EmbeddedKafka(brokerProperties = {
        "listeners=PLAINTEXT://localhost:9099",
        "port=9099"
})
@ActiveProfiles(value = "test")
public abstract class AbstractIntegrationTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/api/v1/delivery";

    protected <T> T performCreateDelivery(Object requestBody, Class<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(post(URL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected void performDeleteDeliveryById(Long id) throws Exception {
        mockMvc.perform(delete(URL + "/delete/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    protected <T> T performGetDeliveryById(Long id, Class<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(get(URL + "/get/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected <T> T performGetAllDeliveries(TypeReference<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(get(URL + "/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected <T> T performUpdateDeliveryById(Long id, Object requestBody, Class<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(put(URL + "/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected <T> T performConfirmDelivery(Long id, Object requestBody, Class<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(post(URL + "/confirm/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected <T> T performPickUpDelivery(Long id, Class<T> response) throws Exception {
        ResultActions resultActions = mockMvc.perform(post(URL + "/pick-up/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), response);
    }

    protected String getJsonString(String path) throws Exception {
        URI uri = Objects.requireNonNull(getClass().getResource(path)).toURI();
        return Files.readString(Path.of(uri));
    }
}
