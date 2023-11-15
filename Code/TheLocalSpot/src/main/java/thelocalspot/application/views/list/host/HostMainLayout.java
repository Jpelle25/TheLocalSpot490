package thelocalspot.application.views.list.host;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.views.list.AfterAuthenticationView;
import thelocalspot.application.views.list.sharedfunctions.CheckAccountView;
public class HostMainLayout extends AppLayout {
    public HostMainLayout() {
        createHeader();
        createDrawer();
    }
    private void createHeader() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        H1 logo = new H1("The Local Spot");
        logo.addClassNames("text-l", "m-m");
        Button accountDetails = new Button("Account Details");
        accountDetails.addClickListener(buttonClickEvent -> accountDetails.getUI().ifPresent(ui -> ui.navigate(CheckAccountView.class)));
        Button homeScreen = new Button("Home");
        homeScreen.addClickListener(buttonClickEvent -> homeScreen.getUI().ifPresent(ui -> ui.navigate(AfterAuthenticationView.class)));
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, accountDetails, homeScreen);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");
        addToNavbar(header);
    }
    private void createDrawer() {
        RouterLink places = new RouterLink("Places", PlacesView.class);
        RouterLink pendingEvents = new RouterLink("Pending Events", HostEventsView.class);
        addToDrawer(new VerticalLayout(
                places,
                pendingEvents
        ));
    }
}