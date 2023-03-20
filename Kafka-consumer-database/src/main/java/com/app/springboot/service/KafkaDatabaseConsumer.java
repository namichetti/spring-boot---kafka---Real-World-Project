package com.app.springboot.service;

import com.app.springboot.entity.WikimediaData;
import com.app.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.app.springboot.util.Constants.NEW_TOPIC;

@Service
public class KafkaDatabaseConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @Autowired
    private WikimediaDataRepository wikimediaDataRepository;

    @KafkaListener(topics = NEW_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String eventMessage){
        LOGGER.info("Mensaje de evento recibido ->" +eventMessage);

        var wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        this.wikimediaDataRepository.save(wikimediaData);
    }
}
