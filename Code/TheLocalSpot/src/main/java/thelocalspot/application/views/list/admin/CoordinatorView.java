package thelocalspot.application.views.list.admin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import thelocalspot.application.data.entity.CoordUser;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.service.CoordUserService;

import java.util.List;

@PageTitle("Admin")
@Route(value = "admin-coordinator", layout = AdminMainLayout.class)
@AnonymousAllowed
public class CoordinatorView extends VerticalLayout{

    Grid<CoordUser> grid;

    private final CoordUserService coordUserService;

    public CoordinatorView(CoordUserService coordUserService) {
        this.coordUserService = coordUserService;
        setSizeFull();
        configureGrid();
        add(getContent());
        updateList();

    }

    private void configureGrid() {

        grid = new Grid<>(CoordUser.class);
        grid.setSizeFull();
        grid.setColumns("id", "firstName", "lastName", "coordGenre", "email", "address", "zipCode", "phoneNumber");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {

        List<CoordUser> coordinators = coordUserService.getCoordUserDetails();
        grid.setItems(coordinators);
    }
}
