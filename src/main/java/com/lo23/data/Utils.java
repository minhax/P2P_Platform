package com.lo23.data;

/**
 * Regroupe les méthodes utiles au package Data
 */
public class Utils
{
    /**
     * Teste si un objet est null et lève une exception si c'est le cas
     * @param text Texte de l'exception à lever
     * @param object Objet à tester
     * @throws NullPointerException Exception levée
     */
    public static void throwExceptionIfNull(String text, Object object) throws NullPointerException
    {
        if (object == null)
        {
            throw new NullPointerException(text);
        }
    }
}
