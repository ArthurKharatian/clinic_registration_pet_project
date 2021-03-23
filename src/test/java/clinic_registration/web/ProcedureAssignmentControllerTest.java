package clinic_registration.web;

import clinic_registration.dto.Client;
import clinic_registration.dto.ClinicBranch;
import clinic_registration.dto.ClinicProcedure;
import clinic_registration.dto.ProcedureAssignment;
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
public class ProcedureAssignmentControllerTest {

    Client client = new Client();
    ClinicBranch branch = new ClinicBranch();
    ClinicProcedure procedure = new ClinicProcedure();
    ProcedureAssignment assignment = new ProcedureAssignment();
    {
        client.setId(1L);
        branch.setId(1L);
        procedure.setId(1L);
        assignment.setId(1L);
        assignment.setProcedure(procedure);
        assignment.setBranch(branch);
        assignment.setClient(client);
        assignment.setVisit_date(LocalDate.of(2122, Month.SEPTEMBER, 1));
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
    public void addAssigment() throws Exception {
        String content = objectMapper.writeValueAsString(assignment);
        System.out.println(content);
        String uri = "/signToProcedure";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Procedure assignment is created!"))
                .andExpect(jsonPath("$.code").value("777"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void readAll() throws Exception {
        String uri = "/signToProcedure/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..client.name", hasItem(containsString("Valentina"))))
                .andExpect(jsonPath("$..visit_date", hasItem(containsString("2122-09-01"))))
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void read() throws Exception {
        String uri = "/signToProcedure/{id}";
        mockMvc.perform(get(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visit_date").value("2122-09-01"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(assignment);
        System.out.println(content);
        String uri = "/signToProcedure";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Procedure assignment with id 1 is updated!"))
                .andExpect(jsonPath("$.code").value("555"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        String uri = "/signToProcedure/{id}";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Procedure assignment with id 1 is deleted!"))
                .andExpect(jsonPath("$.code").value("666"))
                .andDo(document(uri.replace("/", "\\")));
    }

}