/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.utils.commons;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Akash Deep Gupta
 */

@Component
public class MessageUtils implements MessageSourceAware {

    public static final String DEFAULT_LANG = "en";
    private MessageSource messageSource;

    public String t(String messageCode, Object... replacements) {
        return t(messageCode, replacements, DEFAULT_LANG);
    }

    private String t(String messageCode, Object[] replacements, String locale) {
        return messageSource.getMessage(messageCode, replacements, new Locale(locale));
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

}
