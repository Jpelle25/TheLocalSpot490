package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import thelocalspot.application.views.list.MainLayout;

@PageTitle("Admin")
@Route(value = "admin-host", layout = MainLayout.class)
@RolesAllowed("admin")
public class HostView extends VerticalLayout {

    public HostView() {

        setSpacing(false);
        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }
}
