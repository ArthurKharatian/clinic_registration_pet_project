package clinic_registration.web;

import clinic_registration.dto.Client;
import clinic_registration.dto.ClinicBranch;
import clinic_registration.dto.Doctor;
import clinic_registration.dto.DoctorAppointment;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;
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
public class DoctorAppointmentControllerTest {
    Client client = new Client();
    ClinicBranch branch = new ClinicBranch();
    Doctor doctor = new Doctor();
    DoctorAppointment appointment = new DoctorAppointment();

    {
        client.setId(1L);
        branch.setId(1L);
        doctor.setId(1L);
        appointment.setId(1L);
        appointment.setClient(client);
        appointment.setDoctor(doctor);
        appointment.setBranch(branch);
        appointment.setVisit_date(LocalDate.of(2022, Month.APRIL, 22));
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
    public void addAppointment() throws Exception {
        String content = objectMapper.writeValueAsString(appointment);
        System.out.println(content);
        String uri = "/signToDoc";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Appointment to doctor is created!"))
                .andExpect(jsonPath("$.code").value("777"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void readAll() throws Exception {
        String uri = "/signToDoc/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..client.name", hasItem(containsString("Valentina"))))
                .andExpect(jsonPath("$..visit_date", hasItem(containsString("2022-04-22"))))
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void read() throws Exception {
        String uri = "/signToDoc/{id}";
        mockMvc.perform(get(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visit_date").value("2022-04-22"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(appointment);
        System.out.println(content);
        String uri = "/signToDoc/{id}";
        mockMvc.perform(put(uri, "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Appointment to doctor with id 1 is updated!"))
                .andExpect(jsonPath("$.code").value("555"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        String uri = "/signToDoc/{id}";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Appointment to doctor with id 1 is deleted!"))
                .andExpect(jsonPath("$.code").value("666"))
                .andDo(document(uri.replace("/", "\\")));
    }

}