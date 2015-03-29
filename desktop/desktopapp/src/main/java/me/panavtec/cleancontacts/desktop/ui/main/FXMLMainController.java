package me.panavtec.cleancontacts.desktop.ui.main;

import dagger.ObjectGraph;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import me.panavtec.cleancontacts.desktop.di.AppModule;
import me.panavtec.cleancontacts.desktop.ui.MainModule;
import me.panavtec.cleancontacts.presentation.main.MainPresenter;
import me.panavtec.cleancontacts.presentation.main.MainView;
import me.panavtec.cleancontacts.presentation.model.PresentationContact;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLMainController implements Initializable, MainView {

  @FXML private TableView tableView;
  @Inject MainPresenter presenter;

  @Override public void initialize(URL url, ResourceBundle rb) {
    ObjectGraph.create(new AppModule(), new MainModule(this)).inject(this);
    presenter.onCreate(this);
    presenter.onResume();
  }

  @Override public void showGetContactsError() {
  }

  @Override public void refreshContactsList(List<PresentationContact> contacts) {
    for (PresentationContact contact : contacts) {
        SimpleContact simpleContact = new SimpleContact();
        simpleContact.setFirstName(contact.getFirstName());
        simpleContact.setLastName(contact.getLastName());
        tableView.getItems().add(simpleContact);
    }
  }

  @Override public void refreshUi() {
  }

  @Override public void initUi() {
  }

}
