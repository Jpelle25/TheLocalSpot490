package thelocalspot.application.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import thelocalspot.application.views.list.admin.AdminWelcome;
import thelocalspot.application.views.list.coordinator.CoordinatorWelcome;
import thelocalspot.application.views.list.genuser.UserWelcome;
import thelocalspot.application.views.list.host.HostWelcome;

import java.util.Objects;

@Route("registration-role")
@PermitAll
public class RegistrationView extends VerticalLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";

    public RegistrationView() {

        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        Button complete = new Button("Finalize");
//        complete.addClickListener(buttonClickEvent -> complete.getUI().ifPresent(ui -> ui.navigate(TicketsView.class)));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");

        TextField firstName = new TextField("First Name", givenName, "");
        firstName.setReadOnly(true);
        TextField lastName = new TextField("Last Name", familyName, "");
        lastName.setReadOnly(true);
        TextField emailAddress = new TextField("Email Address", email, "");
        emailAddress.setReadOnly(true);
        emailAddress.setWidth("300px");
        emailAddress.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        ComboBox<String> roleSelection = new ComboBox<>("Role Selection");
        roleSelection.setItems("Admin", "Coordinator", "General User", "Host");
        roleSelection.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            if(Objects.equals(roleSelection.getValue(), "Admin")) {
                removeAll();
                complete.addClickListener(buttonClickEvent -> complete.getUI().ifPresent(ui -> ui.navigate(AdminWelcome.class)));
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        complete,
                        logoutButton
                );
            }
            if(Objects.equals(roleSelection.getValue(), "Coordinator")) {
                removeAll();
                complete.addClickListener(buttonClickEvent -> complete.getUI().ifPresent(ui -> ui.navigate(CoordinatorWelcome.class)));
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        complete,
                        logoutButton
                );
            }
            if(Objects.equals(roleSelection.getValue(), "General User")) {
                removeAll();
                complete.addClickListener(buttonClickEvent -> complete.getUI().ifPresent(ui -> ui.navigate(UserWelcome.class)));
                TextField address = new TextField("Home Address");
                TextField phoneNumber = new TextField("Phone Number");
                MultiSelectComboBox<String> preferences = new MultiSelectComboBox<>("Preferences");
                preferences.setItems("Music", "Comedy", "Theatre", "Gaming", "Sports", "Recreational");
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        address,
                        phoneNumber,
                        preferences,
                        complete,
                        logoutButton
                );

            }
            if(Objects.equals(roleSelection.getValue(), "Host")) {
                removeAll();
                complete.addClickListener(buttonClickEvent -> complete.getUI().ifPresent(ui -> ui.navigate(HostWelcome.class)));
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        complete,
                        logoutButton);
            }
        });
        add(firstName, lastName, emailAddress, roleSelection, logoutButton);

    }
}
