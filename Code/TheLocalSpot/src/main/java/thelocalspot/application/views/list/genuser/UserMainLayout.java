package thelocalspot.application.views.list.genuser;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import thelocalspot.application.views.list.AfterAuthenticationView;
import thelocalspot.application.views.list.RegistrationView;
import thelocalspot.application.views.list.sharedfunctions.CheckAccountView;

public class UserMainLayout extends AppLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";

    public UserMainLayout() {


        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        H1 logo = new H1("The Local Spot");
        logo.addClassNames("text-l", "m-m");

        Button accountDetails = new Button("Account Details");
        UI currentPage = new UI();
        accountDetails.addClickListener(buttonClickEvent -> accountDetails.getUI().ifPresent(ui -> ui.navigate(CheckAccountView.class)));
        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(buttonClickEvent -> logoutButton.getUI().ifPresent(ui -> ui.navigate(AfterAuthenticationView.class)));


        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, accountDetails, logoutButton);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(
                new RouterLink("Tickets", TicketsView.class),
                new RouterLink("Browse Events", UserEventsView.class)
        );
    }
}
