package eventlistener;

import messaging.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UpdateEventListener {

    private MessageSender messageSender;

    @Autowired
    public UpdateEventListener(MessageSender messageSender) {
        this.messageSender = messageSender;
    }


    @TransactionalEventListener
    @Async
    public void processEntityEvent(UpdateEvent event) {
        messageSender.sendMessage();
    }
}
