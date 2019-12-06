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
import javax.persistence.OneToOne;
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
public class Kierowcy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kierowcy")
    private int idPracownik;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "nr_tel")
    private String nrTelefonu;
    @Column(name = "ubezpieczenie")
    private LocalDateTime ubezpieczenie;
    @Column(name = "testy_psychologiczne")
    private LocalDateTime testyPsychologiczne;
    @Column(name = "badania_lekarskie")
    private LocalDateTime badaniaLekarskie;
    @Column(name = "pensja")
    private int pensja;
    @OneToOne
    @JoinColumn(name = "id_uzytkownika")
    private Uzytkownik uzytkownik;

    @Override
    public String toString() {
        return getImie()+" "+getNazwisko();
    }
    
    
}
