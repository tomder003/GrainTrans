/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.viewController;

import FirmaTransportowa.FirmaTransportowa.entity.*;
import FirmaTransportowa.FirmaTransportowa.entity.model.Timetable;
import FirmaTransportowa.FirmaTransportowa.repository.KierowcyRepository;
import FirmaTransportowa.FirmaTransportowa.service.*;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Page;
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
 * @author Tomek
 */
@SpringUI
public class ViewController extends UI {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private ZlecenieService zlecenieService;
    @Autowired
    private PracownikService pracownikService;
    @Autowired
    private PojazdyService pojazdyService;
    @Autowired
    private LadunekService ladunekService;
    @Autowired
    private TrasaService trasaService;

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
        Label loginLabel = new Label("Login");
        TextField login = new TextField();
        login.setPlaceholder("login");
        login.setValue("admin");
        Label passwordLabel = new Label("Hasło");
        PasswordField password = new PasswordField();
        password.setPlaceholder("hasło");
        Button button = new Button("Zaloguj");
        password.setValue("admin");

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
        HorizontalLayout hl = new HorizontalLayout();
        VerticalLayout panel1 = new VerticalLayout();
        VerticalLayout panel2 = new VerticalLayout();
        VerticalLayout panel3 = new VerticalLayout();
        Button button = new Button("WYLOGUJ");
        Label label = new Label("PANEL ADMINISTRATORA");
        Label dodawanieZlecenia = new Label("Dodawanie zlecenia");
        Label pojazdy = new Label("Pojazdy");

//        ListDataProvider<Timetable> data=DataProvider.ofCollection(harm());
//        Grid<Timetable> grid=new Grid();
//        grid.setSizeFull(); //rozmiar tabeli
//        grid.addColumn(Timetable::getZlecPon).setCaption("Poniedziałek");
//        grid.addColumn(Timetable::getZlecWt).setCaption("Wtorek");
//        grid.addColumn(Timetable::getZlecSr).setCaption("Środa");
//        grid.addColumn(Timetable::getZlecCzw).setCaption("Czwartek");
//        grid.addColumn(Timetable::getZlecPt).setCaption("Piątek");
//        grid.addColumn(Timetable::getZlecSob).setCaption("Sobota");
//        grid.setDataProvider(data);


        ComboBox<Kierowcy> pracownicyCombo = new ComboBox<>();
        pracownicyCombo.setCaption("Wybierz kierowcę");
        ListDataProvider<Kierowcy> kierowcy = DataProvider.ofCollection(pracownikService.findAll());
        pracownicyCombo.setDataProvider(kierowcy);

        ComboBox<Pojazdy> pojazdyCombo = new ComboBox<>();
        pojazdyCombo.setCaption("Wybierz pojazd");
        ListDataProvider<Pojazdy> pojazy = DataProvider.ofCollection(pojazdyService.findAll());
        pojazdyCombo.setDataProvider(pojazy);

        ComboBox<Ladunek> ladunekCombo = new ComboBox<>();
        ladunekCombo.setCaption("Wybierz ładunek");
        ListDataProvider<Ladunek> ladunek = DataProvider.ofCollection(ladunekService.findAll());
        ladunekCombo.setDataProvider(ladunek);

        ComboBox<Trasa> trasaCombo = new ComboBox<>();
        trasaCombo.setCaption("Wybierz trasę");
        ListDataProvider<Trasa> trasa = DataProvider.ofCollection(trasaService.findAll());
        trasaCombo.setDataProvider(trasa);

        TextField wartosc = new TextField();
        wartosc.setCaption("Wartość zamówienia");

        DateTimeField zaladunek = new DateTimeField();
        zaladunek.setCaption("Załadunek");

        DateTimeField rozladunek = new DateTimeField();
        rozladunek.setCaption("Rozładunek");

        Button dodajZlec = new Button("Dodaj zlecenie");


        ListDataProvider<Pojazdy> data = DataProvider.ofCollection(pojazdyService.findAll());
        Grid<Pojazdy> grid = new Grid();
        grid.setDataProvider(data);
        grid.addColumn(Pojazdy::getMarka).setCaption("Marka");
        grid.addColumn(Pojazdy::getTyp).setCaption("Typ");
        grid.addColumn(Pojazdy::getNrRejestracyjny).setCaption("Nr rej.");
        grid.setSizeFull(); //rozmiar tabeli

        Label pracownicyLabel = new Label("Pracownicy");
        ListDataProvider<Kierowcy> pracownicy = DataProvider.ofCollection(pracownikService.findAll());
        Grid<Kierowcy> pracownicyGrid = new Grid();
        pracownicyGrid.setDataProvider(pracownicy);
        pracownicyGrid.addColumn(Kierowcy::getImie).setCaption("Imię");
        pracownicyGrid.addColumn(Kierowcy::getNazwisko).setCaption("Nazwisko");
        pracownicyGrid.setSizeFull(); //rozmiar tabeli

        panel1.addComponents(dodawanieZlecenia, pracownicyCombo, pojazdyCombo, ladunekCombo, trasaCombo, zaladunek, rozladunek, dodajZlec);
        panel2.addComponents(pojazdy, grid);
        panel3.addComponents(pracownicyLabel, pracownicyGrid);
        hl.addComponents(panel1, panel2, panel3);
        vl.addComponents(button, label, hl);


        button.addClickListener(e -> {
            zalogowany = false;
            setContent(panelLogowania());
        });

        dodajZlec.addClickListener(e -> {
            Zlecenie zlecenie = new Zlecenie();
            zlecenie.setKierowca(pracownicyCombo.getValue());
            zlecenie.setPojazd(pojazdyCombo.getValue());
            zlecenie.setLadunek(ladunekCombo.getValue());
            zlecenie.setTrasa(trasaCombo.getValue());
            zlecenie.setWartoscZlecenia(zlecenie.getTrasa());
            zlecenie.setDataZaladunku(zaladunek.getValue());
            zlecenie.setDataRozladunku(rozladunek.getValue());
            zlecenieService.dodajZlecenie(zlecenie);
            Notification notification = new Notification("Zlecenie dodane", "Sukces!");
            notification.show(Page.getCurrent());
            System.out.println(zlecenieService.iloscZlecen());
        });

        return vl;
    }

    private List<Timetable> harm() {
        LocalDateTime dzis = LocalDateTime.now();
        List<Timetable> timetable = new ArrayList<>();
        switch (dzis.getDayOfWeek()) {
            case FRIDAY:
                List<Zlecenie> pt = zlecenieService.getZlecenieOfDay(dzis.plusDays(2));
                Timetable t = new Timetable();
                for (int i = 0; i < pt.size(); i++) {
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


        Grid<Zlecenie> tabelka = new Grid<>();
        List<Zlecenie> zlecenia = zlecenieService.getZlecenia();
        ListDataProvider<Zlecenie> dataProvider = DataProvider.ofCollection(zlecenia);
        tabelka.setDataProvider(dataProvider);
        tabelka.addColumn(Zlecenie::getDataZaladunku).setCaption("Data załadunku");
        tabelka.addColumn(Zlecenie::getDataRozladunku).setCaption("Data rozadunku");
        tabelka.addColumn(Zlecenie::getTrasa).setCaption("Trasa");
        tabelka.addColumn(Zlecenie::getPojazd).setCaption("Pojazd");
        tabelka.addColumn(Zlecenie::getLadunek).setCaption("ładunek");
        tabelka.addColumn(Zlecenie::getWartoscZlecenia).setCaption("Wartość");
        tabelka.setWidth("100%");

        vl.addComponents(button, label, tabelka);


        button.addClickListener(e -> {
            zalogowany = false;
            setContent(panelLogowania());
        });

        return vl;
    }

}
