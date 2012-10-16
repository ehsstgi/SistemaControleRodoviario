/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.InterfaceLocal;
import entity.Passagem;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

/**
 *
 * @author Eduardo
 */
  @FacesConverter(forClass = Passagem.class)
public class PassagemConverter implements Converter {
     private InterfaceLocal<Passagem> ejbPassagemFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        try {
            ejbPassagemFacade = (InterfaceLocal<Passagem>) new InitialContext().lookup("java:app/SistemaControleRodoviario-ejb/PassagemFacade!ejb.PassagemLocal");
        } catch (Exception e) {
            e.getStackTrace();
        }
        return ejbPassagemFacade.find(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Passagem) {
            Passagem o = (Passagem) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Passagem.class.getName());
        }
    }
}