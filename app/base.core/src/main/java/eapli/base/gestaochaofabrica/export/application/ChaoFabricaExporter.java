/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import java.io.IOException;
import java.util.Date;


/**
 * Padrão Strategy
 * @author mdias
 * @param <T>
 */
public interface ChaoFabricaExporter<T extends Object> {

    void begin(String filename) throws IOException;

    void element(T t, Date dataI, Date dataF);

    void elementSeparator();

    void end();

    void cleanup();
}
