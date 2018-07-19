package com.walkover.user.api.utils.commons;

import org.springframework.stereotype.Component;

/**
 * @author Akash Deep Gupta
 **/

@Component
public class JsonViews {

    public interface apiResponse {
    }

    ;

    public interface userAuthKey {
    }

    ;

    public interface userEmailId {
    }

    ;

    public interface userName {
    }

    ;

    public interface userDetailsExcludingPassword extends apiResponse {
    }

    ;
}
