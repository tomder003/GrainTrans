/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.entity.model;

import FirmaTransportowa.FirmaTransportowa.entity.Zlecenie;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tomek
 */
@Getter
@Setter
public class Timetable {
    private int id;
    private Zlecenie zlecPon;
    private Zlecenie zlecWt;
    private Zlecenie zlecSr;
    private Zlecenie zlecCzw;
    private Zlecenie zlecPt;
    private Zlecenie zlecSob;
}
