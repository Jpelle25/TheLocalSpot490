package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@PageTitle("Admin-Welcome")
@Route(value = "admin-welcome", layout = AdminMainLayout.class)
@AnonymousAllowed
public class AdminWelcome extends VerticalLayout {

    public AdminWelcome() {

        H1 welcome = new H1("Welcome Admin");
        add(welcome);
    }
}
