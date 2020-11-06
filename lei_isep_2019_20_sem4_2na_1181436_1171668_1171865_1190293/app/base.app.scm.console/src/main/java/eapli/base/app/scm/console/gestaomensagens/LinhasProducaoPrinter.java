/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.scm.console.gestaomensagens;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author 35193
 */
public class LinhasProducaoPrinter implements Visitor<LinhaProducao>{

    @Override
    public void visit(LinhaProducao visitee) {
        System.out.printf("%-10s", visitee.identity());
    }
    
}
