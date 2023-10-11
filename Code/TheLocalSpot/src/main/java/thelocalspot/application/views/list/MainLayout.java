package thelocalspot.application.views.list;

import com.vaadin.flow.router.HighlightConditions;
import thelocalspot.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import thelocalspot.application.views.list.admin.CoordinatorView;
import thelocalspot.application.views.list.admin.GenUserView;
import thelocalspot.application.views.list.admin.HostView;
import thelocalspot.application.views.list.coordinator.CoordinatorEventsView;
import thelocalspot.application.views.list.genuser.TicketsView;
import thelocalspot.application.views.list.genuser.UserEventsView;
import thelocalspot.application.views.list.host.HostEventsView;
import thelocalspot.application.views.list.host.PlacesView;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    public MainLayout(SecurityService securityService) {

        this.securityService = securityService;

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("The Local Spot");
        logo.addClassNames("text-l", "m-m");

        Button accountDetails = new Button("Account Details");
        Button logout = new Button("Log Out", e -> securityService.logout());

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, accountDetails, logout);
        
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0", "px-m");
        
        addToNavbar(header);
    }

    private void createDrawer() {

        //Admin Navigation
        if(securityService.getAuthenticatedUser().getUsername() == "admin"){
            addToDrawer(new VerticalLayout(
                    new RouterLink("Coordinator View", CoordinatorView.class),
                    new RouterLink("General User View", GenUserView.class),
                    new RouterLink("Host View", HostView.class)
            ));
        }

        //Coordinator Navigation
        if(securityService.getAuthenticatedUser().getUsername() == "coordinator"){
            addToDrawer(new VerticalLayout(
                    new RouterLink("Events", CoordinatorEventsView.class)
            ));
        }

        //General User Navigation
        if(securityService.getAuthenticatedUser().getUsername() == "user"){
            addToDrawer(new VerticalLayout(
                    new RouterLink("Tickets", TicketsView.class),
                    new RouterLink("Events", UserEventsView.class)
            ));
        }

        //Host Navigation
        if(securityService.getAuthenticatedUser().getUsername() == "host"){
            addToDrawer(new VerticalLayout(
                    new RouterLink("Events", HostEventsView.class),
                    new RouterLink("Places", PlacesView.class)
            ));
        }

//        RouterLink listView = new RouterLink("List", ListView.class);
//        listView.setHighlightCondition(HighlightConditions.sameLocation());
//
//        addToDrawer(new VerticalLayout(
//                listView,
//                new RouterLink("Dashboard", DashboardView.class)
//        ));
    }
}
