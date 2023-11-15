package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.entity.Place;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.data.service.PlaceService;

import java.util.List;

@PageTitle("Host Places")
@Route(value = "Places", layout = HostMainLayout.class)
@PermitAll
public class PlacesView extends VerticalLayout {
    PlaceService placeService;
    HostService hostService;
    Grid<Place> grid;
    PlacesForm form;

    public PlacesView(PlaceService placeService, HostService hostService) {
        this.placeService = placeService;
        this.hostService = hostService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        addClassName("places-view");
        setSizeFull();
        configureGrid();
        configureForm(principal);

        add(getToolbar(principal), getContent());
        updateList(principal);
        closeEditor();


    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm(OAuth2AuthenticatedPrincipal principal) {
        form = new PlacesForm(placeService.findAllPlaces());
        form.setWidth("25em");
        form.create.addClickListener(buttonClickEvent -> {
            if (form.placeName.isEmpty() ||
                    form.placeAddress.isEmpty() ||
                    form.placeCapacity.isEmpty() ||
                    form.placeInfo.isEmpty()) {
                Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
                nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                List<Host> hostSelf = hostService.getHostSelf(principal.getAttribute("email"));
                placeService.savePlace(new Place(hostSelf.get(0).getId(), form.placeName.getValue(), form.placeAddress.getValue(), form.placeCapacity.getValue(), form.placeInfo.getValue()));
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            }
        });
        form.cancel.addClickListener(buttonClickEvent -> {
            closeEditor();
        });
    }


    private void configureGrid() {
        grid = new Grid<>(Place.class);
        grid.addClassNames("place-grid");
        grid.setSizeFull();
        grid.setColumns("placeName", "placeAddress", "placeCapacity", "placeInfo");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(gridPlaceComponentValueChangeEvent -> {
            editPlace(gridPlaceComponentValueChangeEvent.getValue());
        });
    }

    private void editPlace(Place place) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        if (place == null) {
            closeEditor();
        } else {
            form.placeName.setValue(place.getPlaceName());
            form.placeAddress.setValue(place.getPlaceAddress());
            form.placeCapacity.setValue(place.getPlaceCapacity());
            form.placeInfo.setValue(place.getPlaceInfo());
            form.remove(form.create);
            form.remove(form.cancel);
            form.add(form.edit);
            form.edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            form.add(form.delete);
            form.delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
            form.add(form.cancel);
            form.cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            form.edit.addClickListener(buttonClickEvent -> {
                if (form.placeName.isEmpty() ||
                        form.placeAddress.isEmpty() ||
                        form.placeCapacity.isEmpty() ||
                        form.placeInfo.isEmpty()) {
                    Notification nonCompleteRegistration = Notification.show("One or more fields is empty", 3000, Notification.Position.BOTTOM_CENTER);
                    nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    place.setPlaceName(form.placeName.getValue());
                    place.setPlaceAddress(form.placeAddress.getValue());
                    place.setPlaceCapacity(form.placeCapacity.getValue());
                    place.setPlaceInfo(form.placeInfo.getValue());
                    placeService.savePlace(place);
                    closeEditor();
                    removeAll();
                    setSizeFull();
                    configureGrid();
                    configureForm(principal);
                    add(
                            getToolbar(principal),
                            getContent()
                    );
                    updateList(principal);
                    closeEditor();
                }
            });
            form.delete.addClickListener(buttonClickEvent -> {
                closeEditor();
                placeService.deletePlace(place);
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            });
            form.cancel.addClickListener(buttonClickEvent -> {
                closeEditor();
                removeAll();
                setSizeFull();
                configureGrid();
                configureForm(principal);
                add(
                        getToolbar(principal),
                        getContent()
                );
                updateList(principal);
                closeEditor();
            });
            form.setVisible(true);
        }
    }

    private Component getToolbar(OAuth2AuthenticatedPrincipal principal) {
        Button addPlaceButton = new Button("Add place");
        addPlaceButton.addClickListener(click -> addPlace());

        var toolbar = new HorizontalLayout(addPlaceButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void createPlace(Place place) {
        if (place == null) {
            closeEditor();
        } else {
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addPlace() {
        grid.asSingleSelect().clear();
        createPlace(new Place());
    }

    private void updateList(OAuth2AuthenticatedPrincipal principal) {
        List<Host> hostSelf = hostService.getHostSelf(principal.getAttribute("email"));
        grid.setItems(placeService.getPlacesSelf(hostSelf.get(0).getId()));
    }
}
