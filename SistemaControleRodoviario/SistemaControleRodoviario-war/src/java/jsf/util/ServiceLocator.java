/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.util;

/**
 *
 * @author Eduardo
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class ServiceLocator {
    
    private ServiceLocator(){
        
    }
    private static final String PREFIXO_JNDI = "java:app/SistemaControleRodoviario-ejb/";

    @SuppressWarnings("unchecked")
    public static <T> T lookupInterfaceLocal(Class<T> beanClass, boolean isEspecifico) {
        Context context = null;

        try {
            context = new InitialContext();
            return (T) context.lookup(getJndi(beanClass, isEspecifico));
        } catch (NamingException ex) {
            throw new IllegalStateException("Erro ao buscar bean: " + beanClass.getCanonicalName(), ex);
        } finally {
            try {
                context.close();
            } catch (NamingException ex) {
                throw new IllegalStateException("Erro ao fechar InitialContext", ex);
            }
        }
    }
        @SuppressWarnings("unchecked")
    public static <T> T lookupNoInterface(Class<T> beanClass) {
        Context context = null;

        try {
            context = new InitialContext();
            return (T) context.lookup(getJndiNoInterface(beanClass));
        } catch (NamingException ex) {
            throw new IllegalStateException("Erro ao buscar bean: " + beanClass.getCanonicalName(), ex);
        } finally {
            try {
                context.close();
            } catch (NamingException ex) {
                throw new IllegalStateException("Erro ao fechar InitialContext", ex);
            }
        }
    }

    private static <T> String getJndi(Class<T> beanClass, boolean isEspecifico) {
        String jndi = PREFIXO_JNDI + beanClass.getSimpleName();
        if (isEspecifico) {
            jndi = jndi + "!ejb." + beanClass.getSimpleName().substring(0,beanClass.getSimpleName().indexOf("Facade"))+"Local";
        } else {
            jndi = jndi + "!ejb.InterfaceLocal";
        }

        return jndi;
    }
private static <T> String getJndiNoInterface(Class<T> beanClass) {
        return PREFIXO_JNDI + beanClass.getSimpleName();
    }
}