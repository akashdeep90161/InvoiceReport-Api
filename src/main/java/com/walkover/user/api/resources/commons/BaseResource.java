package com.walkover.user.api.resources.commons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @param
 * @author Akash Deep Gupta
 * @version v1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResource<T> {

    @Getter(onMethod = @__(@JsonIgnore))
    @Setter(value = AccessLevel.PUBLIC)
    private T model;

}
