package clinic_registration.web;

import clinic_registration.dto.Client;
import clinic_registration.dto.ClientGender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientControllerTest {

     Client client = new Client();
    {
        client.setId(1L);
        client.setName("Matvienko Valentina Ivanovna");
        client.setClient_gender(ClientGender.FEMALE);
        client.setBirthdate(LocalDate.of(1949, Month.APRIL, 7));
        client.setEmail("mvi@gov.ru");
        client.setPhone_number(777555);
    }

    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        ConfigurableMockMvcBuilder builder =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                        .apply(documentationConfiguration(this.restDocumentation));
        this.mockMvc = builder.build();
    }
    @Test
    @Transactional
    public void addClient() throws Exception {
        String content = objectMapper.writeValueAsString(client);
        System.out.println(content);
        String uri = "/client";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("mvi@gov.ru"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void readAll() throws Exception {
        String uri = "/client/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void read() throws Exception {
        String uri = "/client/1";
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Matvienko Valentina Ivanovna"));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(client);
        System.out.println(content);
        String uri = "/client/1";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Matvienko Valentina Ivanovna"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        String uri = "/client/1";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(uri.replace("/", "\\")));
    }


}