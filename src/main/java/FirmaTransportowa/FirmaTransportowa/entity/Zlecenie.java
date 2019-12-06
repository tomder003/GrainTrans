/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Tomek
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Zlecenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zlecenia")
    private int idZlecenie;
    @ManyToOne
    @JoinColumn(name = "id_kierowcy")
    private Kierowcy kierowca;
    @ManyToOne
    @JoinColumn(name = "id_pojazdu")
    private Pojazdy pojazd;
    @Column(name = "data_zaladunku")
    private LocalDateTime dataZaladunku;
    @Column(name = "data_rozladunku")
    private LocalDateTime dataRozladunku;
    @ManyToOne
    @JoinColumn(name = "id_ladunek")
    private Ladunek ladunek;
    @ManyToOne
    @JoinColumn(name = "id_trasy")
    private Trasa trasa;
    @Column(name = "wartosc_zlecenia")
    private double wartoscZlecenia;
    
    public void setWartoscZlecenia(Trasa trasa)
    {
        this.wartoscZlecenia=trasa.getOdleglosc()*0.4;
    }

    @Override
    public String toString() {
        return "Ładunek: "+getLadunek().getLadunek()+"\nKierowca: "+getKierowca().toString()+"\nPojazd:"+getPojazd().getNrRejestracyjny();
    }
    
    
}
