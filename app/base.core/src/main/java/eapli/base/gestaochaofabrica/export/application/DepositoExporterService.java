/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.base.gestaodepositos.domain.Deposito;

/**
 *
 * @author mdias
 */
public class DepositoExporterService {    
    
    private static final Logger logger = LogManager.getLogger(DepositoExporterService.class);

    public void export(Iterable<Deposito> depositos, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final Deposito d : depositos) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(d, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting depositos", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
