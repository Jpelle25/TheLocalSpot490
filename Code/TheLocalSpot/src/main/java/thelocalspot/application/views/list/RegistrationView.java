package thelocalspot.application.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.service.GenUserService;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.views.list.admin.AdminWelcome;
import thelocalspot.application.views.list.coordinator.CoordinatorWelcome;
import thelocalspot.application.views.list.genuser.UserWelcome;
import thelocalspot.application.views.list.host.HostWelcome;

import java.util.Objects;

@Route("registration-role")
@PermitAll
public class RegistrationView extends VerticalLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";
    GenUserService genUserService;
    HostService hostService;

    public RegistrationView(GenUserService genUserService, HostService hostService) {
        this.genUserService = genUserService;
        this.hostService = hostService;

        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        //Google Authenticated Inherited Fields
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

        //Role-Selection
        Button finalize = new Button("Finalize");
        ComboBox<String> roleSelection = new ComboBox<>("Role Selection");
        roleSelection.setItems("Admin", "Coordinator", "General User", "Host");
        add(firstName, lastName, emailAddress, roleSelection, logoutButton);

        //Role-Selected Specific Fields
        roleSelection.addValueChangeListener(comboBoxStringComponentValueChangeEvent -> {
            if(Objects.equals(roleSelection.getValue(), "Admin")) {
                removeAll();
                finalize.addClickListener(buttonClickEvent -> finalize.getUI().ifPresent(ui -> ui.navigate(AdminWelcome.class)));
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        finalize,
                        logoutButton
                );
            }
            if(Objects.equals(roleSelection.getValue(), "Coordinator")) {
                removeAll();
                finalize.addClickListener(buttonClickEvent -> finalize.getUI().ifPresent(ui -> ui.navigate(CoordinatorWelcome.class)));
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        finalize,
                        logoutButton
                );
            }
            if(Objects.equals(roleSelection.getValue(), "General User")) {
                removeAll();
                TextField address = new TextField("Home Address");
                address.setHelperText("Format: 101 Cherry Lane");
                TextField zipCode = new TextField("Zip Code");
                zipCode.setMaxLength(5);
                TextField phoneNumber = new TextField("Phone Number");
                phoneNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
                phoneNumber.setAllowedCharPattern("[0-9()+-]");
                phoneNumber.setMinLength(5);
                phoneNumber.setMaxLength(18);
                phoneNumber.setHelperText("Format: (123)456-7890");
                MultiSelectComboBox<String> preferences = new MultiSelectComboBox<>("Preferences");
                preferences.setItems("Music", "Comedy", "Theatre", "Gaming", "Sports", "Recreational");
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        address,
                        zipCode,
                        phoneNumber,
                        preferences,
                        finalize,
                        logoutButton
                );
                finalize.addClickListener(buttonClickEvent ->{
                    if(address.isEmpty() ||
                            zipCode.isEmpty() ||
                            zipCode.isEmpty() ||
                            phoneNumber.isEmpty() ||
                            preferences.isEmpty()){
                        Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
                        nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                    else {
                        genUserService.saveUser(new GenUser(givenName, familyName, email, roleSelection.getValue(), address.getValue(), Integer.valueOf(zipCode.getValue()), phoneNumber.getValue(), preferences.getSelectedItems()));
                        finalize.getUI().ifPresent(ui -> ui.navigate(UserWelcome.class));
                    }
                });

            }
            if(Objects.equals(roleSelection.getValue(), "Host")) {
                removeAll();
                TextField address = new TextField("Home Address");
                address.setHelperText("Format: 101 Cherry Lane");
                TextField zipCode = new TextField("Zip Code");
                zipCode.setMaxLength(5);
                TextField phoneNumber = new TextField("Phone Number");
                phoneNumber.setPattern("^[+]?[(]?[0-9]{3}[)]?[-s.]?[0-9]{3}[-s.]?[0-9]{4,6}$");
                phoneNumber.setAllowedCharPattern("[0-9()+-]");
                phoneNumber.setMinLength(5);
                phoneNumber.setMaxLength(18);
                phoneNumber.setHelperText("Format: (123)456-7890");
                add(
                        firstName,
                        lastName,
                        emailAddress,
                        roleSelection,
                        address,
                        zipCode,
                        phoneNumber,
                        finalize,
                        logoutButton);
                finalize.addClickListener(buttonClickEvent -> {
                    if(address.isEmpty() ||
                            zipCode.isEmpty() ||
                            zipCode.isEmpty() ||
                            phoneNumber.isEmpty()){
                        Notification nonCompleteRegistration = Notification.show("Please enter in all the fields for registration", 3000, Notification.Position.BOTTOM_CENTER);
                        nonCompleteRegistration.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                    else{
                        hostService.saveHost(new Host(givenName, familyName, email, roleSelection.getValue(), address.getValue(), Integer.valueOf(zipCode.getValue()), phoneNumber.getValue()));
                        finalize.getUI().ifPresent(ui -> ui.navigate(HostWelcome.class));
                    }
                });

            }
        });
    }
}
