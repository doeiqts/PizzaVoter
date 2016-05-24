package com.doeiqts.pizzavoter.repositories;

import com.doeiqts.pizzavoter.domain.UserProfile;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserProfileRepository {
    public static UserProfile loadUserProfile(String username) {
        UserProfile userProfile;
        userProfile = ofy().load().type(UserProfile.class).id(username).now();

        if (userProfile == null) {
            userProfile = new UserProfile(username);
        }

        return userProfile;
    }

    public static void saveUserProfile(UserProfile userProfile) {
        ofy().save().entity(userProfile).now();
    }
}
