package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public enum FileFormat {
    XML("xml"),
    HTML("html"),
    JSON("json"),
    TXT("txt"),
    XSL("xsl");

    private String desc;

    public String getValue(){
        return this.desc;
    }

    FileFormat(String desc){
        this.desc = desc;
    }
}
