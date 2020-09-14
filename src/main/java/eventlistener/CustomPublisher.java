package eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomPublisher {


    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public CustomPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCustomEvent() {
        UpdateEvent event = new UpdateEvent();
        applicationEventPublisher.publishEvent(event);
    }
}
