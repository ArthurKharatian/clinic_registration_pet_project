package clinic_registration.web;

import clinic_registration.dto.Doctor;
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

import static org.hamcrest.Matchers.*;
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
public class DoctorControllerTest {
    Doctor doctor = new Doctor();
    {
        doctor.setId(1L);
        doctor.setName("John H. Watson");
        doctor.setPositionName("military doctor");
        doctor.setAddPositionName("medical doctor");
        doctor.setEmail("watson@gmail.com");
        doctor.setPhoneNumber(911);
        doctor.setBirthdate(LocalDate.of(1850, Month.JULY, 7));
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
    public void addDoctor() throws Exception {
        String content = objectMapper.writeValueAsString(doctor);
        System.out.println(content);
        String uri = "/doctor";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Doctor is created!"))
                .andExpect(jsonPath("$.code").value("777"))
                .andDo(document(uri.replace("/", "\\")));
    }
    @Test
    @Transactional
    public void readAll() throws Exception {
        String uri = "/doctor/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..name", hasItem(containsString("Watson"))))
                .andExpect(jsonPath("$..positionName", hasItem(containsString("military"))))
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void read() throws Exception {
        String uri = "/doctor/{id}";
        mockMvc.perform(get(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John H. Watson"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(doctor);
        System.out.println(content);
        String uri = "/doctor";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Doctor with id 1 is updated!"))
                .andExpect(jsonPath("$.code").value("555"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        String uri = "/doctor/{id}";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Doctor with id 1 is deleted!"))
                .andExpect(jsonPath("$.code").value("666"))
                .andDo(document(uri.replace("/", "\\")));
    }
}