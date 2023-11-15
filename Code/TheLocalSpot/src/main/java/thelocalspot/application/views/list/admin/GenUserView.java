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
import thelocalspot.application.data.service.GenUserService;

import java.util.List;

@PageTitle("Admin")
@Route(value = "admin-general-user", layout = AdminMainLayout.class)
@AnonymousAllowed
public class GenUserView extends VerticalLayout {

    Grid<GenUser> grid;
    private final GenUserService genUserService;

    public GenUserView(GenUserService genUserService) {
        this.genUserService = genUserService;
        setSizeFull();
        configureGrid();
        add(getContent());
        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(GenUser.class);
        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "email", "address", "zipCode", "phoneNumber", "preferences");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {

        List<GenUser> genUsers = genUserService.getUserDetails();
        grid.setItems(genUsers);
    }
}
