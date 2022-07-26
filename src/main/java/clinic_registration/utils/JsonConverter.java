package clinic_registration.utils;

import clinic_registration.exceptions.ClinicServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JsonConverter {

    private JsonConverter() {

    }

    public static String getString(Object dtoObject, ObjectMapper objectMapper) {

        String dto;
        try {
            dto = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(dtoObject);

        } catch (JsonProcessingException e) {
            String eMessage = e.getMessage();
            log.error(eMessage);
            throw new ClinicServiceException(eMessage);
        }

        return dto;
    }
}
