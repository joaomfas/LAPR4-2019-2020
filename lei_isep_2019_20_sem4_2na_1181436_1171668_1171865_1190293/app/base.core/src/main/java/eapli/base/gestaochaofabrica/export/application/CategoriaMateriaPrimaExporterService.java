/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class CategoriaMateriaPrimaExporterService {
    private static final Logger logger = LogManager.getLogger(CategoriaMateriaPrimaExporterService.class);

    public void export(Iterable<CategoriaMateriaPrima> categorias, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final CategoriaMateriaPrima cat : categorias) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(cat, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting categoria matéria prima", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
