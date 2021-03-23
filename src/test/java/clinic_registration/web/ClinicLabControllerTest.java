package clinic_registration.web;

import clinic_registration.dto.ClinicBranch;
import clinic_registration.dto.ClinicLab;
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
public class ClinicLabControllerTest {
    ClinicBranch branch = new ClinicBranch();
    ClinicLab lab = new ClinicLab();
    {
        branch.setId(1L);
        lab.setId(1L);
        lab.setWorker_name("Borisov Aleksandr Petrovich");
        lab.setPosition_name("Laboratory assistant");
        lab.setOpen_time("7:00");
        lab.setClose_time("16:00");
        lab.setBranch(branch);
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
    public void addLab() throws Exception {
        String content = objectMapper.writeValueAsString(lab);
        System.out.println(content);
        String uri = "/lab";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Laboratory is created!"))
                .andExpect(jsonPath("$.code").value("777"))
                .andDo(document(uri.replace("/", "\\")));
    }
    @Test
    @Transactional
    public void readAll() throws Exception {
        String uri = "/lab/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..worker_name", hasItem(containsString("Petrovich"))))
                .andExpect(jsonPath("$..position_name", hasItem(containsString("assistant"))))
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void read() throws Exception {
        String uri = "/lab/{id}";
        mockMvc.perform(get(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.worker_name").value("Borisov Aleksandr Petrovich"));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(lab);
        System.out.println(content);
        String uri = "/lab/{id}";
        mockMvc.perform(put(uri, "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Laboratory with id 1 is updated!"))
                .andExpect(jsonPath("$.code").value("555"))
                .andDo(document(uri.replace("/", "\\")));
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        String uri = "/lab/{id}";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri, "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Laboratory with id 1 is deleted!"))
                .andExpect(jsonPath("$.code").value("666"))
                .andDo(document(uri.replace("/", "\\")));
    }
}