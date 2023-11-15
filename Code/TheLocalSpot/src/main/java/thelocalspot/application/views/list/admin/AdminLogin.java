package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import thelocalspot.application.views.list.LoginView;

@PageTitle("Admin-Login")
@Route("admin-login")
@PermitAll
public class AdminLogin extends VerticalLayout {

    private final LoginForm login = new LoginForm();

    public AdminLogin() {

        addClassName("admin-login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");

        add(new H1("The Local Spot | Admin Login"), login);
    }
}
