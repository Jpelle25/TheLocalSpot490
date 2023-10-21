package thelocalspot.application.views.list.genuser;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PageTitle("User-Welcome")
@Route(value = "user-welcome", layout = UserMainLayout.class)
@PermitAll
public class UserWelcome extends VerticalLayout {

    public UserWelcome() {

        H1 welcome = new H1("Welcome User");
        add(welcome);
    }
}
