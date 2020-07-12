/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.entity;

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
 * @author Tomek
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trasy")
    private int idTrasy;
    @Column(name = "start")
    private String start;
    @Column(name = "cel")
    private String cel;
    @Column(name = "odleglosc")
    private double odleglosc;
    @Column(name = "czy_platna")
    private boolean platna;

    @Override
    public String toString() {
        return start + " -> " + cel;
    }
}
