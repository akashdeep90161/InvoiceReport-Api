package com.walkover.user.api.utils.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Component;

/**
 * @author Akash Deep Gupta
 **/
@Component
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        registerModule(new JodaModule());
    }
}
