package thelocalspot.application.views.list.sharedfunctions;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.service.CoordUserService;
import thelocalspot.application.data.service.GenUserService;
import thelocalspot.application.data.service.HostService;
import thelocalspot.application.views.list.coordinator.CoordinatorWelcome;
import thelocalspot.application.views.list.genuser.UserWelcome;
import thelocalspot.application.views.list.host.HostWelcome;
import java.util.List;
@PageTitle("Account Info")
@Route(value = "Account-Info")
@PermitAll
public class CheckAccountView extends VerticalLayout {
    GenUserService userService;
    CoordUserService coordUserService;
    HostService hostService;
    public CheckAccountView(GenUserService userService, CoordUserService coordUserService, HostService hostService) {
        this.userService = userService;
        this.coordUserService = coordUserService;
        this.hostService = hostService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
        String picture = principal.getAttribute("picture");
        Image image = new Image(picture, "User Image");
        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");
        String email = principal.getAttribute("email");
        TextField firstName = new TextField("First Name", givenName, "");
        firstName.setReadOnly(true);
        firstName.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        TextField lastName = new TextField("Last Name", familyName, "");
        lastName.setReadOnly(true);
        lastName.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        TextField emailAddress = new TextField("Email Address", email, "");
        emailAddress.setReadOnly(true);
        emailAddress.setWidth("300px");
        emailAddress.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        Button goBack = new Button("Go Back");
        setSpacing(false);
        add(image, firstName, lastName, emailAddress, goBack);
        goBack.addClickListener(buttonClickEvent -> {
            List<GenUser> genUsers = userService.findGenUser(email);
            List<CoordUser> coordUsers = coordUserService.getCoordUserEmail(email);
            List<Host> hosts = hostService.getHostSelf(email);
            if (!genUsers.isEmpty()){
                goBack.getUI().ifPresent(ui -> ui.navigate(UserWelcome.class));
            }
            else if (!coordUsers.isEmpty()){
                goBack.getUI().ifPresent(ui -> ui.navigate(CoordinatorWelcome.class));
            }else if(!hosts.isEmpty()){
                goBack.getUI().ifPresent(ui -> ui.navigate(HostWelcome.class));
            }
        });
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}