package thelocalspot.application.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.GenUserService;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.views.list.admin.AdminLogin;
import thelocalspot.application.views.list.coordinator.CoordinatorWelcome;
import thelocalspot.application.views.list.genuser.UserWelcome;
import thelocalspot.application.views.list.host.HostWelcome;

import java.util.List;

@Route("")
@PermitAll
public class AfterAuthenticationView extends VerticalLayout {

    private static final String LOGOUT_SUCCESS_URL = "/";
    GenUserService genUserService;
    CoordUserService coordUserService;
    HostService hostService;

    public AfterAuthenticationView(GenUserService genUserService, CoordUserService coordUserService, HostService hostService) {
        addClassName("after-auth-view");

        this.genUserService = genUserService;
        this.coordUserService = coordUserService;
        this.hostService = hostService;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        H2 header = new H2("Hello, " + givenName + " " + familyName);
        Image image = new Image(picture, "User Image");

        Button adminLogin = new Button("Admin Login");
        adminLogin.addClickListener(buttonClickEvent -> {
            adminLogin.getUI().ifPresent(ui -> ui.navigate(AdminLogin.class));
        });

        VerticalLayout adminNav = new VerticalLayout();
        adminNav.add(adminLogin);
        adminNav.setAlignItems(Alignment.END);
        adminNav.addClassName("admin-nav-after-auth");


        Button logoutButton = new Button("Logout", click -> {
            UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
            SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
            logoutHandler.logout(
                    VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                    null);
        });

        List<GenUser> genUsers = genUserService.findGenUser(email);
        List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(email);
        List<Host> hosts = hostService.getHostSelf(email);

        if(!genUsers.isEmpty()){
            setAlignItems(Alignment.CENTER);
            Button enterApp = new Button("Enter Application");
            enterApp.addClickListener(buttonClickEvent -> {
                enterApp.getUI().ifPresent(ui -> ui.navigate(UserWelcome.class));
            });
            add(header, image, enterApp, logoutButton);
            add(image);
        }
        else if(!coordUsers.isEmpty()){
            setAlignItems(Alignment.CENTER);
            Button enterApp = new Button("Enter Application");
            enterApp.addClickListener(buttonClickEvent -> {
                enterApp.getUI().ifPresent(ui -> ui.navigate(CoordinatorWelcome.class));
            });
            add(header, image, enterApp, logoutButton);
            add(image);
        }else if(!hosts.isEmpty()){
            setAlignItems(Alignment.CENTER);
            Button enterApp = new Button("Enter Application");
            enterApp.addClickListener(buttonClickEvent -> {
                enterApp.getUI().ifPresent(ui -> ui.navigate(HostWelcome.class));
            });
            add(header, image, enterApp, logoutButton);
            add(image);
        }
        else{
//            setAlignItems(Alignment.CENTER);
            Button register = new Button("Register Role");
            register.addClickListener(buttonClickEvent -> {
                register.getUI().ifPresent(ui -> ui.navigate(RegistrationView.class));
            });
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.add(header, register,logoutButton);
            verticalLayout.setAlignItems(Alignment.CENTER);
            add(adminNav, verticalLayout);
//            add(image);
        }
    }
}
