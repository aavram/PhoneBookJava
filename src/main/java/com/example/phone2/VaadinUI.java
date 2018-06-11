package com.example.phone2;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.shared.ui.ValueChangeMode;
import org.springframework.util.StringUtils;

@SpringUI(path = "/test")
public class VaadinUI extends UI{

    private static final long serialVersionUID = -2587484560085683173L;
    private final ContactRepository repo;
    private final ContactEditor editor;
    final Grid<Contact> grid;
    final TextField filter;
    private final Button addNewBtn;

    public VaadinUI(ContactRepository repo, ContactEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Contact.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New contact", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "firstName", "lastName", "phoneNumber");

        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listContacts(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editContact(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editContact(new Contact("", "", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listContacts(filter.getValue());
        });

        listContacts(null);
    }

    private void listContacts(String filterText) {

        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
    }
}
