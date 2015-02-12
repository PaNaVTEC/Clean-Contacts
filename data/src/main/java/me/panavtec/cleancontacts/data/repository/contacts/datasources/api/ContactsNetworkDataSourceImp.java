package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.mappers.ApiContactMapper;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactResult;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import me.panavtec.cleancontacts.domain.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;

import java.util.ArrayList;
import java.util.List;

public class ContactsNetworkDataSourceImp implements ContactsNetworkDataSource {

    private ContactsApiService apiService;
    private static final ApiContactMapper contactMapper = new ApiContactMapper();

    public ContactsNetworkDataSourceImp(ContactsApiService apiService) {
        this.apiService = apiService;
    }

    @Override public List<Contact> obtainContacts() throws ContactsNetworkException {
        try {
            ApiContactsResponse apiContactsResponse = apiService.obtainUsers(100);
            List<ApiContactResult> results = apiContactsResponse.getResults();
            List<Contact> contacts = new ArrayList<>();
            if (results != null) {
                for (ApiContactResult apiContact : results) {
                    contacts.add(contactMapper.dataToModel(apiContact.getUser()));
                }
            }
            return contacts;
        } catch (Throwable e) {
            throw new ContactsNetworkException();
        }
    }
}
