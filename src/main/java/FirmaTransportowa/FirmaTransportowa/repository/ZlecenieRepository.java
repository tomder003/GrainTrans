/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirmaTransportowa.FirmaTransportowa.repository;

import FirmaTransportowa.FirmaTransportowa.entity.Zlecenie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tomek
 */
public interface ZlecenieRepository extends JpaRepository<Zlecenie, Integer>{
    
}
