package maker;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import com.natpryce.makeiteasy.PropertyLookup;
import com.walkover.user.api.dao.model.User;


/**
 * @author Akash Deep Gupta
 **/

public class UserMaker {

    public static final Property<com.walkover.user.api.dao.model.User, Long> id = new Property<>();
    public static final Property<com.walkover.user.api.dao.model.User, String> name = new Property<>();
    public static final Property<com.walkover.user.api.dao.model.User, String> emailId = new Property<>();

    public final static Instantiator<com.walkover.user.api.dao.model.User> User = (PropertyLookup<com.walkover.user.api.dao.model.User> lookup) -> {
        com.walkover.user.api.dao.model.User user = new User();
        user.setId(lookup.valueOf(id, 500l));
        user.setName(lookup.valueOf(name, "Foo"));
        user.setEmailId(lookup.valueOf(emailId, "abcdxyz@gmail.com"));
        return user;
    };
}
