package me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses;

import com.google.gson.annotations.Expose;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;

public class ApiContactResult {

    @Expose private ApiContact user;
    @Expose private String seed;

    public ApiContact getUser() {
        return user;
    }

    public void setUser(ApiContact user) {
        this.user = user;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
