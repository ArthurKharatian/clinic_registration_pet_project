package clinic_registration.web;

import clinic_registration.db.entity.ClientEntity;
import clinic_registration.db.entity.ClinicBranchEntity;
import clinic_registration.db.entity.ClinicProcedureEntity;
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
public class ProcedureAssignmentControllerTest {

    ClientEntity client = new ClientEntity();
    ClinicBranchEntity branch = new ClinicBranchEntity();
    ClinicProcedureEntity procedure = new ClinicProcedureEntity();
    ProcedureAssignment assignment = new ProcedureAssignment();
    {
        client.setId(4L);
        branch.setId(4L);
        procedure.setId(1L);

        assignment.setId(0L);
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
    public void addAssigment() throws Exception {
        String content = objectMapper.writeValueAsString(assignment);
        System.out.println(content);
        String uri = "/signToProcedure";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document(uri));
    }

    @Test
    public void readAll() throws Exception {
        String uri = "/signToProcedure/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void read() throws Exception {
        String uri = "/signToProcedure/1";
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visit_date").value("2122-01-09"));
    }

    @Test
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(assignment);
        System.out.println(content);
        String uri = "/signToProcedure/1";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception {
        String uri = "/signToProcedure/1";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

}