/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.app.backoffice.presentation.depositos.RegistarDepositoAction;
import eapli.base.app.backoffice.presentation.gestaochaofabrica.ExportarChaoFabricaXMLAction;
import eapli.base.app.backoffice.presentation.gestaoerros.ArquivarErrosAction;
import eapli.base.app.backoffice.presentation.gestaoerros.ConsultarErrosArquivadosAction;
import eapli.base.app.backoffice.presentation.gestaoerros.ConsultarErrosPendentesAction;
import eapli.base.app.backoffice.presentation.linhasproducao.RegistarLinhaProducaoAction;
import eapli.base.app.backoffice.presentation.maquinas.AdicionarFicheiroConfigMaquinaAction;
import eapli.base.app.backoffice.presentation.maquinas.EspecificarMaquinaAction;
import eapli.base.app.backoffice.presentation.maquinas.MarcarFicheiroConfigParaEnvioAction;
import eapli.base.app.backoffice.presentation.materiasprimas.RegistarCategoriaMateriaPrimaAction;
import eapli.base.app.backoffice.presentation.materiasprimas.RegistarMateriaPrimaAction;
import eapli.base.app.backoffice.presentation.ordensproducao.AdicionarOrdemProducaoAction;
import eapli.base.app.backoffice.presentation.ordensproducao.ImportarOrdensProducaoCSVAction;
import eapli.base.app.backoffice.presentation.ordensproducao.OrdensProducaoComEstadoAction;
import eapli.base.app.backoffice.presentation.ordensproducao.OrdensProducaoDeEncomendaAction;
import eapli.base.app.backoffice.presentation.spm.AlterarEstadoProcessamentoAction;
import eapli.base.app.backoffice.presentation.produtos.ApresentarProdutosSemFichaAction;
import eapli.base.app.backoffice.presentation.produtos.ImportarProdutosCSVAction;
import eapli.base.app.backoffice.presentation.produtos.RegistarFichaProducaoAction;
import eapli.base.app.backoffice.presentation.produtos.RegistarProdutoAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Sair ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    // GESTOR PRODUCAO
    private static final int GERIR_PRODUTOS_OPTION = 1;
    private static final int GERIR_MATERIAS_PRIMAS_OPTION = 2;
    private static final int GERIR_ORDENS_PRODUCAO_OPTION = 3;
    private static final int EXPORTAR_XML = 4;

    // PRODUTOS
    private static final int REGISTAR_PRODUTO_OPTION = 1;
    private static final int IMPORTAR_PRODUTOS_CSV_OPTION = 2;
    private static final int CONSULTAR_PRODUTOS_SEM_FICHA_PROD_OPTION = 3;
    private static final int INSERIR_FICHA_PRODUCAO_OPTION = 4;

    // MATERIAS PRIMAS
    private static final int REGISTAR_CAT_MAT_PRIMA_OPTION = 1;
    private static final int REGISTAR_MAT_PRIMA_OPTION = 2;

    // ORDENS DE PRODUÇÃO
    private static final int REGISTAR_ORDEM_PRODUCAO = 1;
    private static final int IMPORTAR_ORDENS_CSV_OPTION = 2;
    private static final int LISTAR_ORDENS_ESTADO = 3;
    private static final int LISTAR_ORDENS_ENCOMENDA = 4;

    // CHÃO DE FÁBRICA
    private static final int REGISTAR_LINHA_PROD_OPTION = 1;
    private static final int REGISTAR_MAQUINA_OPTION = 2;
    private static final int REGISTAR_DEPOSITO_OPTION = 3;
    private static final int ADICIONAR_FICHEIRO_CONFIGURACAO_MAQUINA_OPTION = 4;
    private static final int MARCAR_FICHEIRO_CONFIG_PARA_ENVIO_OPTION = 5;
    private static final int ATIVAR_DESATIVAR_PROCESSAMENTO_OPTION = 6;

    //ERROS PROCESSAMENTO
    private static final int CONSULTAR_ERROS_ARQUIVADOS = 1;
    private static final int CONSULTAR_ERROS_PENDENTES = 2;
    private static final int ARQUIVAR_ERROS = 3;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SECOND_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "GOP [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("GOP [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_PRODUCAO)) {
            final Menu gestorMenu = buildGestorProducaoMenu();
            mainMenu.addSubMenu(USERS_OPTION, gestorMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.GESTOR_CHAO_FABRICA)) {
            final Menu gestorChaoFabricaMenu = buildGestaoChaoFabricaMenu();
            mainMenu.addSubMenu(USERS_OPTION, gestorChaoFabricaMenu);
            final Menu gestaoErrosMenu = buildGestaoErrosMenu();
            mainMenu.addSubMenu(SECOND_OPTION, gestaoErrosMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Sair", new ExitWithMessageAction());

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Definições >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGestorProducaoMenu() {
        final Menu menu = new Menu("Gestão Produção >");

        Menu produtoMenu = buildGestaoProdutosMenu();
        menu.addSubMenu(GERIR_PRODUTOS_OPTION, produtoMenu);
        Menu matPrimaMenu = buildGestaoMateriasPrimasMenu();
        menu.addSubMenu(GERIR_MATERIAS_PRIMAS_OPTION, matPrimaMenu);
        Menu ordemProducaoMenu = buildOrdensProducaoMenu();
        menu.addSubMenu(GERIR_ORDENS_PRODUCAO_OPTION, ordemProducaoMenu);
        menu.addItem(EXPORTAR_XML, "Exportar/Converter informação para XML e outros formatos", new ExportarChaoFabricaXMLAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGestaoProdutosMenu() {
        final Menu menu = new Menu("Gestão de Produtos >");
        menu.addItem(REGISTAR_PRODUTO_OPTION, "Registar produto", new RegistarProdutoAction());
        menu.addItem(IMPORTAR_PRODUTOS_CSV_OPTION, "Importar produtos de ficheiro CSV",
                new ImportarProdutosCSVAction());
        menu.addItem(CONSULTAR_PRODUTOS_SEM_FICHA_PROD_OPTION, "Consultar produtos sem ficha de produção",
                new ApresentarProdutosSemFichaAction());
        menu.addItem(INSERIR_FICHA_PRODUCAO_OPTION, "Inserir ficha de produção relativa a um produto",
                new RegistarFichaProducaoAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGestaoMateriasPrimasMenu() {
        final Menu menu = new Menu("Gestão de Matérias Primas >");
        menu.addItem(REGISTAR_CAT_MAT_PRIMA_OPTION, "Registar categoria de matéria prima",
                new RegistarCategoriaMateriaPrimaAction());
        menu.addItem(REGISTAR_MAT_PRIMA_OPTION, "Registar matéria prima",
                new RegistarMateriaPrimaAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOrdensProducaoMenu() {
        final Menu menu = new Menu("Gestão de Ordens de Produção >");
        menu.addItem(REGISTAR_ORDEM_PRODUCAO, "Registar ordem de produção",
                new AdicionarOrdemProducaoAction());
        menu.addItem(IMPORTAR_ORDENS_CSV_OPTION, "Importar ordens de produção de ficheiro CSV",
                new ImportarOrdensProducaoCSVAction());
        menu.addItem(LISTAR_ORDENS_ESTADO, "Consultar Ordens de Produção por estado",
                new OrdensProducaoComEstadoAction());
        menu.addItem(LISTAR_ORDENS_ENCOMENDA, "Consultar Ordens de Produção por encomenda",
                new OrdensProducaoDeEncomendaAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGestaoChaoFabricaMenu() {
        final Menu menu = new Menu("Gestão de Chão de Fábrica >");
        menu.addItem(REGISTAR_LINHA_PROD_OPTION, "Registar linha de produção",
                new RegistarLinhaProducaoAction());
        menu.addItem(REGISTAR_MAQUINA_OPTION, "Registar máquina",
                new EspecificarMaquinaAction());
        menu.addItem(REGISTAR_DEPOSITO_OPTION, "Registar depósito",
                new RegistarDepositoAction());
        menu.addItem(ADICIONAR_FICHEIRO_CONFIGURACAO_MAQUINA_OPTION, "Adicionar Ficheiro de Configuração a máquina",
                new AdicionarFicheiroConfigMaquinaAction());
        menu.addItem(MARCAR_FICHEIRO_CONFIG_PARA_ENVIO_OPTION, "Marcar ficheiro de configuração para envio",
                new MarcarFicheiroConfigParaEnvioAction());
        menu.addItem(ATIVAR_DESATIVAR_PROCESSAMENTO_OPTION, "Ativar/Desativar estado de processamento",
                new AlterarEstadoProcessamentoAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildGestaoErrosMenu() {
        final Menu menu = new Menu("Gestão de Erros de Processamento >");
        menu.addItem(CONSULTAR_ERROS_ARQUIVADOS, "Consultar erros arquivados",
                new ConsultarErrosArquivadosAction());
        menu.addItem(CONSULTAR_ERROS_PENDENTES, "Consultar erros pendentes",
                new ConsultarErrosPendentesAction());
        menu.addItem(ARQUIVAR_ERROS, "Arquivar erros",
                new ArquivarErrosAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Utilizadores >");

        menu.addItem(ADD_USER_OPTION, "Adicionar utilizador", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "Listar todos os utilizadores", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Desativar utilizador", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Aceitar/Rejeitar pedidos de registo",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
