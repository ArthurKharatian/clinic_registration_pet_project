package clinic_registration.web;

import clinic_registration.dto.Client;
import clinic_registration.dto.ClientGender;
import clinic_registration.dto.ClinicServiceType;
import clinic_registration.dto.SignToClinicService;
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
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SingToClinicServiceControllerTest {
    SignToClinicService sign = new SignToClinicService();
    Client client = new Client();
    {
        client.setId(555);
        client.setName("Matvienko Valentina Ivanovna");
        client.setClientGender(ClientGender.FEMALE);
        client.setBirthdate(LocalDate.of(1949, Month.APRIL, 7));
        client.setEmail("mvi@gov.ru");
        client.setPhoneNumber(777555);

        sign.setId(777);
        sign.setClient_id(client.getId());
        sign.setServiceType(ClinicServiceType.MASSAGE);
        sign.setDuration(60);
        sign.setVisitDate(LocalDate.of(2021, Month.AUGUST, 19));

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
    public void addSign() throws Exception {
        String content = objectMapper.writeValueAsString(sign);
        System.out.println(sign);
        String uri = "/signToService";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));

    }

    @Test
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(sign);
        System.out.println(content);
        String uri = "/signToService/777";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception {
        String uri = "/signToService/777";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }
}