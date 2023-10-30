package thelocalspot.application.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.service.GenUserService;
import thelocalspot.application.views.list.genuser.UserWelcome;

import java.util.List;

@Route("")
@PermitAll
public class AfterAuthenticationView extends VerticalLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";
    GenUserService genUserService;

    public AfterAuthenticationView(GenUserService genUserService) {

        this.genUserService = genUserService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        H2 header = new H2("Hello " + givenName + " " + familyName + " (" + email + ")");
        Image image = new Image(picture, "User Image");

        Button register = new Button("Register Role");
        register.addClickListener(buttonClickEvent -> {
            List<GenUser> genUsers = genUserService.findGenUser(email);
            if(genUsers.isEmpty()){
                register.getUI().ifPresent(ui -> ui.navigate(RegistrationView.class));
            }
            else{
                register.getUI().ifPresent(ui -> ui.navigate(UserWelcome.class));
            }
        });
        register.addClickListener(buttonClickEvent -> register.getUI().ifPresent(ui -> ui.navigate(RegistrationView.class)));

//        TextArea textArea = new TextArea(principal.)

        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        setAlignItems(Alignment.CENTER);
        add(header, image, register, logoutButton);
        add(image);
    }
}
