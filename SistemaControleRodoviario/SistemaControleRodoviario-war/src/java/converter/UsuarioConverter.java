/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import ejb.UsuarioFacade;
import entity.Usuario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

/**
 *
 * @author Eduardo
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
    private UsuarioFacade ejbUsuarioFacade;
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value){
            if (value == null || value.length() == 0) {
                return null;
            }
           
            try{
               ejbUsuarioFacade=(UsuarioFacade) new InitialContext().lookup("java:app/SistemaControleRodoviario-ejb/UsuarioFacade"); 
            }catch(Exception e){
               e.getStackTrace();
            }
            return ejbUsuarioFacade.find(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }
    }
    

