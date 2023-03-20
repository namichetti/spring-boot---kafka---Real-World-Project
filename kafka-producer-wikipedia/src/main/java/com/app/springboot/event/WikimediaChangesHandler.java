package com.app.springboot.event;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.app.springboot.util.Constants.NEW_TOPIC;


@Component
public class WikimediaChangesHandler implements EventHandler {

    private KafkaTemplate<String,String> kafkaTemplate;

    private final static Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);

    public WikimediaChangesHandler(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info("Event data -> " +messageEvent.getData());
        this.kafkaTemplate.send(NEW_TOPIC, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
