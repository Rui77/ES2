/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to_do_list;

/**
 *
 * @author Rui
 */
public class Etiqueta {

    String tag;
    String created;

    public Etiqueta(String tmpTag) throws InstantiationError {
        if (tmpTag != null && !tmpTag.equals("") && tmpTag.length() <= 20) {
            this.tag = tmpTag;
            this.created = "Etiqueta creada com sucesso";
        } else {
            throw new InstantiationError("Há algum problema com a tag");
        }
    }

    public String getCreated() {
        return created;
    }

    /**
     * Imprime uma String com a informação sobre a etiqueta.
     *
     * @return String com a etiqueta.
     */
    @Override
    public String toString() {
        return this.tag;
    }

}
