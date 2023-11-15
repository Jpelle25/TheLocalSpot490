package thelocalspot.application.views.list.genuser;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import thelocalspot.application.data.entity.GenUser;
import thelocalspot.application.data.service.GenUserService;

@PageTitle("General User")
@Route(value = "ticket-transfer-form", layout = UserMainLayout.class)
@PermitAll
public class TransferTicketForm extends FormLayout {

    GenUserService genUserService;
    ComboBox<GenUser> genUsers = new ComboBox<>("Select General User to transfer to...");
    Button transfer = new Button("Transfer");
    Button cancel = new Button("Cancel");

    public TransferTicketForm(GenUserService genUserService) {
        this.genUserService = genUserService;
        genUsers.setItems(genUserService.getUserDetails());
        genUsers.setItemLabelGenerator(GenUser::getFullName);

        add(
                genUsers,
                transfer,
                cancel
        );
    }
}
