package thelocalspot.application.views.list;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    /**
     * URL that Spring uses to connect to Google services
     */
    private static final String OAUTH_URL = "/oauth2/authorization/google";

    public LoginView() {
        addClassName("login-view");
        H1 theLocalSpot = new H1("The Local Spot");
        H2 instructions = new H2("Please authenticate with Google to Sign in");
        Anchor loginLink = new Anchor(OAUTH_URL, "Login with Google");
        loginLink.addClassName("login-link");
        loginLink.getElement().setAttribute("router-ignore", true);
        add(theLocalSpot, instructions, loginLink);
        getStyle().set("padding", "450px");
        setAlignItems(FlexComponent.Alignment.CENTER);
    }
}