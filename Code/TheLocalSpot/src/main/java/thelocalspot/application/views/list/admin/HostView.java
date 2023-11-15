package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.entity.Host;
import thelocalspot.application.data.service.HostService;

import java.util.List;

@PageTitle("Admin")
@Route(value = "admin-host", layout = AdminMainLayout.class)
@AnonymousAllowed
public class HostView extends VerticalLayout {

    Grid<Host> grid;
    private final HostService hostService;


    public HostView(HostService hostService) {
        this.hostService = hostService;
        setSizeFull();
        configureGrid();
        add(getContent());
        updateList();
    }

    private void configureGrid() {

        grid = new Grid<>(Host.class);
        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "email", "address", "zipCode", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {

        List<Host> hosts = hostService.getUserDetails();
        grid.setItems(hosts);
    }
}
