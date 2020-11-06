package eapli.base.gestaomensagens.dominio;

import eapli.framework.domain.model.ValueObject;
import javax.persistence.Embeddable;


@Embeddable
public class RawData implements ValueObject {
    String rawData;

    public RawData() {
        
    }
    
    public RawData(String data) {
        rawData = data;
    }

    public RawData(byte[] rawData) {
        this.rawData = new String(rawData);
    }

    @Override
    public String toString() {
        return this.rawData;
    }

    public byte[] toBytes(){
        return rawData.getBytes();
    }
}
