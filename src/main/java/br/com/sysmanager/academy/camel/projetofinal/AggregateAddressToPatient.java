package br.com.sysmanager.academy.camel.projetofinal;

import br.com.sysmanager.academy.camel.projetofinal.model.Patient;
import javax.xml.crypto.dsig.XMLObject;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.json.JSONObject;

/**
 * @author Davi-PC
 */
public class AggregateAddressToPatient implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        //System.out.println("original:\n" + original.getIn().getBody(String.class));
        //System.out.println("resource:\n" + resource.getIn().getBody(String.class));
        
        //System.out.println("\n\n\n\n");
        
        JSONObject patient = new JSONObject(original.getIn().getBody(String.class));
        JSONObject correios = new JSONObject(resource.getIn().getBody(String.class));
        
        if(correios.has("bairro"))
            patient.put("bairro", correios.get("bairro"));
        
        if(correios.has("cep"))
            patient.put("cep", correios.get("cep"));
        
        if(correios.has("cidade"))
            patient.put("cidade", correios.get("cidade"));
        
        if(correios.has("end"))
            patient.put("rua", correios.get("end"));
        
        if(correios.has("uf")) 
            patient.put("uf", correios.get("uf"));
        
        if(correios.has("complemento2")) 
            patient.put("complemento", correios.get("complemento2"));
        
        original.getIn().setBody(patient.toString());
        
        return original;
    }
    
}
