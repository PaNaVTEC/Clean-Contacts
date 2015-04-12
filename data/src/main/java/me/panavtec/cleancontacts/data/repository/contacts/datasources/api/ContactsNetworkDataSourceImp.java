package me.panavtec.cleancontacts.data.repository.contacts.datasources.api;

import com.mobandme.android.transformer.Transformer;
import java.util.ArrayList;
import java.util.List;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.entities.ApiContact;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactResult;
import me.panavtec.cleancontacts.data.repository.contacts.datasources.api.responses.ApiContactsResponse;
import me.panavtec.cleancontacts.presentation.outputs.entities.Contact;
import me.panavtec.cleancontacts.repository.contacts.datasources.ContactsNetworkDataSource;
import me.panavtec.cleancontacts.repository.contacts.datasources.exceptions.ContactsNetworkException;

public class ContactsNetworkDataSourceImp implements ContactsNetworkDataSource {

  private ContactsApiService apiService;
  private static final Transformer transformer = new Transformer.Builder().build(ApiContact.class);

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
          contacts.add(transformer.transform(apiContact.getUser(), Contact.class));
        }
      }
      return contacts;
    } catch (Throwable e) {
      throw new ContactsNetworkException();
    }
  }
}
