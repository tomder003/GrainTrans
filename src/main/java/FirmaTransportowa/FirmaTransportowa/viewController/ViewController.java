/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.viewController;

import FirmaTransportowa.FirmaTransportowa.entity.Kierowcy;
import FirmaTransportowa.FirmaTransportowa.entity.Uzytkownik;
import FirmaTransportowa.FirmaTransportowa.entity.Zlecenie;
import FirmaTransportowa.FirmaTransportowa.entity.model.Timetable;
import FirmaTransportowa.FirmaTransportowa.service.UzytkownikService;
import FirmaTransportowa.FirmaTransportowa.service.ZlecenieService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomek
 */
@SpringUI
public class ViewController extends UI {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private ZlecenieService zlecenieService;

    private boolean zalogowany = false;
    private Kierowcy kierowca;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (!zalogowany) {
            setContent(panelLogowania());
        } else {
            if (kierowca.getUzytkownik().getRola().equals("ADMIN")) {
                setContent(panelAdmin());
            } else {
                setContent(panelKierowcy());
            }
        }
    }

    private VerticalLayout panelLogowania() {
        VerticalLayout vl = new VerticalLayout();
        Label loginLabel = new Label("Dodaj nowy czołg");
        TextField login = new TextField();
        login.setPlaceholder("login");
        Label passwordLabel = new Label("Hasło");
        TextField password = new TextField();
        password.setPlaceholder("hasło");
        Button button = new Button("Zaloguj");

        vl.addComponents(loginLabel, login, passwordLabel, password, button);

        button.addClickListener(e -> {
            if (uzytkownikService.login(login.getValue(), password.getValue())) {
                zalogowany = true;
                kierowca = uzytkownikService.getKierowcaByLogin(login.getValue());
                if (kierowca.getUzytkownik().getRola().equals("ADMIN")) {
                    setContent(panelAdmin());
                } else {
                    setContent(panelKierowcy());
                }
            }
        });

        return vl;
    }

    private VerticalLayout panelAdmin() {
        VerticalLayout vl = new VerticalLayout();
        Button button = new Button("WYLOGUJ");
        Label label = new Label("PANEL ADMINISTRATORA");
        
        ListDataProvider<Timetable> data=DataProvider.ofCollection(harm());
        Grid<Timetable> grid=new Grid();
        grid.setSizeFull(); //rozmiar tabeli
        grid.addColumn(Timetable::getZlecPon).setCaption("Poniedziałek"); 
        grid.addColumn(Timetable::getZlecWt).setCaption("Wtorek"); 
        grid.addColumn(Timetable::getZlecSr).setCaption("Środa");  
        grid.addColumn(Timetable::getZlecCzw).setCaption("Czwartek");  
        grid.addColumn(Timetable::getZlecPt).setCaption("Piątek"); 
        grid.addColumn(Timetable::getZlecSob).setCaption("Sobota");  
        grid.setDataProvider(data);
        
        vl.addComponents(button, label, grid);
            

        button.addClickListener(e -> {
            zalogowany = false;
            setContent(panelLogowania());
        });

        return vl;
    }

    private List<Timetable> harm() {
        LocalDateTime dzis = LocalDateTime.now();
        List<Timetable> timetable=new ArrayList<>();
        switch (dzis.getDayOfWeek()) {
            case FRIDAY:
                List<Zlecenie> pt = zlecenieService.getZlecenieOfDay(dzis.plusDays(2));
                Timetable t=new Timetable();
                for(int i=0;i<pt.size();i++)
                {
                    t.setId(i);
                    t.setZlecSob(pt.get(i));
                    timetable.add(t);
                }
                break;
        }
        return timetable;
    }

    private VerticalLayout panelKierowcy() {
        VerticalLayout vl = new VerticalLayout();
        Button button = new Button("WYLOGUJ");
        Label label = new Label("PANEL KIEROWCY");
        vl.addComponents(button, label);

        button.addClickListener(e -> {
            zalogowany = false;
            setContent(panelLogowania());
        });

        return vl;
    }

}
