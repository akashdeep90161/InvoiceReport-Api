/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.walkover.user.api.utils.commons;

import com.walkover.user.api.exception.InvalidParameterException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.resources.commons.BaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Akash Deep Gupta
 **/

@Component
public class ExceptionUtils {

    private static MessageUtils messageUtils;

    @Autowired
    public ExceptionUtils(MessageUtils messageUtils) {
        ExceptionUtils.messageUtils = messageUtils;
    }

    public static void throwInvalidRequestExceptionIfResourceIsNull(BaseResource resource) throws InvalidRequestException, InvalidParameterException {
        if (resource == null) {
            throw new InvalidRequestException(messageUtils.t("error.request.invalid"));
        }
    }

    public static void throwInvalidParameterExceptionForInvalidEmailId() throws InvalidParameterException {
        throw new InvalidParameterException(messageUtils.t("error.user.invalidEmail"));
    }

    public static void throwInvalidParameterExceptionForInvalidUserName() throws InvalidParameterException {
        throw new InvalidParameterException(messageUtils.t("error.user.invalidName"));
    }

}
