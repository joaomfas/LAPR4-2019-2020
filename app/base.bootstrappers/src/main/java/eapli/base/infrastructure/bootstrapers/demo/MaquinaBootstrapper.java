/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.MaquinaBootstrapperBase;
import eapli.framework.actions.Action;

/**
 *
 * @author 35193
 */
public class MaquinaBootstrapper extends MaquinaBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        
        this.registarMaquina("10", 1l, "101", "101", "Maquina10A", "m10a1", "10/10/2015", "marcaA", "modeloA");
        this.registarMaquina("10", 2l, "102", "102", "Maquina10B", "m10b2", "02/03/2013", "marcaA", "modeloA");
        this.registarMaquina("10", 3l, "103", "103", "Maquina10C", "m10c3", "10/01/2018", "marcaA", "modeloB");
        
        this.registarMaquina("20", 1l, "106",  "106", "Maquina20A", "m10a6", "05/11/2016", "marcaXPTO", "modeloXPTO");
        this.registarMaquina("20", 2l, "107", "107", "Maquina20B", "m10b7", "02/04/2014", "marcaA", "modeloA");
        this.registarMaquina("20", 3l, "108", "108", "Maquina20C", "m10c8", "03/02/2017", "marcaZ", "modeloZZB");
        
        this.registarMaquina("30", 1l, "109", "T3", "Maquina30A", "m10a9", "10/10/2015", "marcaA", "modeloA");
        this.registarMaquina("30", 2l, "110", "DD4", "Maquina30B", "m10b10", "02/03/2013", "marcaA", "modeloA");
        
        return true;
    }
    
}
