package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import thelocalspot.application.data.entity.Place;

import java.util.List;

public class PlacesForm extends FormLayout {
    TextField placeName = new TextField("Place Name");
    TextField placeAddress = new TextField("Place Address");
    TextField placeCapacity = new TextField("Place Capacity");
    TextField placeInfo = new TextField("Place Information");
    Button create = new Button("Create Place");
    Button delete = new Button("Delete");
    Button edit = new Button("Edit");
    Button cancel = new Button("Cancel");
    public PlacesForm(List<Place> places) {
        addClassName("places-form");
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(
                placeName,
                placeAddress,
                placeCapacity,
                placeInfo,
                create,
                cancel
        );
    }
}