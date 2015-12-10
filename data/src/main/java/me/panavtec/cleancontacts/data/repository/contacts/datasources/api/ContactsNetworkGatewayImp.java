package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import com.mobandme.android.transformer.Transformer;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactResult;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import me.panavtec.cleancontacts.domain.model.Contact;
import me.panavtec.cleancontacts.domain.model.ContactsNetworkGateway;
import me.panavtec.cleancontacts.domain.model.NetworkException;

public class ContactsNetworkGatewayImp implements ContactsNetworkGateway {

  private ContactsApiService apiService;
  private static final Transformer transformer = new Transformer.Builder().build(ApiContact.class);

  public ContactsNetworkGatewayImp(ContactsApiService apiService) {
    this.apiService = apiService;
  }

  @Override public List<Contact> obtainContacts() throws NetworkException {
    try {
      ApiContactsResponse apiContactsResponse = apiService.obtainUsers(100).execute().body();
      List<ApiContactResult> results = apiContactsResponse.getResults();
      List<Contact> contacts = new ArrayList<>();
      if (results != null) {
        for (ApiContactResult apiContact : results) {
          contacts.add(transformer.transform(apiContact.getUser(), Contact.class));
        }
      }
      return contacts;
    } catch (Throwable e) {
      throw new NetworkException();
    }
  }
}
