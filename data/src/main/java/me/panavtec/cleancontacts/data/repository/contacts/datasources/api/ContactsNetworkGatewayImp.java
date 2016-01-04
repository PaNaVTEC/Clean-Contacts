package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactResult;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import me.panavtec.cleancontacts.domain.mappers.Mapper;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;
import me.panavtec.cleancontacts.domain.model.NetworkException;

public class ContactsNetworkGatewayImp implements ContactsNetworkGateway {

  private final ContactsApiService apiService;
  private final Mapper<ApiContact, Contact> mapper;

  public ContactsNetworkGatewayImp(ContactsApiService apiService,
      Mapper<ApiContact, Contact> mapper) {
    this.apiService = apiService;
    this.mapper = mapper;
  }

  @Override public List<Contact> obtainContacts() throws NetworkException {
    try {
      ApiContactsResponse apiContactsResponse = apiService.obtainUsers(100).execute().body();
      List<ApiContactResult> results = apiContactsResponse.getResults();
      List<Contact> contacts = new ArrayList<>();
      if (results != null) {
        for (ApiContactResult apiContact : results) {
          contacts.add(mapper.map(apiContact.getUser()));
        }
      }
      return contacts;
    } catch (Throwable e) {
      throw new NetworkException();
    }
  }
}
