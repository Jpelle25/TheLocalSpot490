package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import thelocalspot.application.views.list.genuser.TicketsView;
import thelocalspot.application.views.list.genuser.UserEventsView;

public class AdminMainLayout extends AppLayout {
    private static final String LOGOUT_SUCCESS_URL = "/";

    public AdminMainLayout() {

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("The Local Spot");
        logo.addClassNames("text-l", "m-m");

//        Button accountDetails = new Button("Account Details");
//        accountDetails.addClickListener(buttonClickEvent -> accountDetails.getUI().ifPresent(ui -> ui.navigate(CheckAccountView.class)));
        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logoutButton);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }
    private void createDrawer() {

        RouterLink coordinatorView = new RouterLink("Coordinator View", CoordinatorView.class);
        RouterLink generalUserView = new RouterLink("General User View", GenUserView.class);
        RouterLink hostView = new RouterLink("Host View", HostView.class);

        addToDrawer(new VerticalLayout(
                coordinatorView,
                generalUserView,
                hostView
        ));
    }
}