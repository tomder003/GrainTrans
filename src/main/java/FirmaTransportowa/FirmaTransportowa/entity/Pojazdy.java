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
public class Pojazdy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pojazdu")
    private int idPojazdu;
    @Column(name = "nr_rejestracyjny")
    private String nrRejestracyjny;
    @Column(name = "marka")
    private String marka;
    @Column(name = "typ")
    private String typ;
    @Column(name = "ubezpieczenie")
    private LocalDateTime ubezpieczenie;
    @Column(name = "przeglad")
    private LocalDateTime przeglad;
    @Column(name = "licznik_km")
    private double licznikKm;

    @Override
    public String toString()
    {
        return nrRejestracyjny;
    }
}
