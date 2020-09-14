package messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SystemInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import service.SystemInfoService;

@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    private SystemInfoService systemInfoService;

    private ObjectMapper objectMapper;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate, SystemInfoService systemInfoService,
                         ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.systemInfoService=systemInfoService;
        this.objectMapper=objectMapper;
    }


    public void sendMessage() {
        SystemInfoDto systemInfoDto = systemInfoService.getSystemStatistics();
        try {
            String jsonMessage = objectMapper.writeValueAsString(systemInfoDto);
            sendMessage(jsonMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(final String message) {
        jmsTemplate.send(session -> session.createTextMessage(message));
    }

}
