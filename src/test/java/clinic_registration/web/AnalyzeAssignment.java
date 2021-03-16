package clinic_registration.web;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnalyzeAssignment {

    clinic_registration.dto.AnalyzeAssignment sign = new clinic_registration.dto.AnalyzeAssignment();
    {
        sign.setId(0L);
        sign.setName("Extended blood test");
        sign.setClient_id(3L);
        sign.setLab_id(0L);
        sign.setVisit_date(LocalDate.of(2125, Month.SEPTEMBER, 15));
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
        System.out.println(content);
        String uri = "/signToTest";
        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }
    @Test
    public void readAll() throws Exception {
        String uri = "/signToTest/all";
        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void read() throws Exception {
        String content = objectMapper.writeValueAsString(sign);
        System.out.println(content);
        String uri = "/signToTest/0";
        mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void update() throws Exception {
        String content = objectMapper.writeValueAsString(sign);
        System.out.println(content);
        String uri = "/signToTest/0";
        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }

    @Test
    public void delete() throws Exception {
        String uri = "/signToTest/0";
        mockMvc.perform(MockMvcRequestBuilders.delete(uri))
                .andExpect(status().isOk())
                .andDo(document(uri));
    }
}