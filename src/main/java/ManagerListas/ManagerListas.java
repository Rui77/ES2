/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerListas;

import Enumeracoes.EstadoTarefa;
import Exceptions.TaskNotFound;
import to_do_list.Lista;

/**
 *
 * @author Rui
 */
public class ManagerListas {

    private Lista[] listas;
    private int listCount;
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Método construtor que permite a inicialização de um gestor de listas
     * (To-do lists)
     */
    public ManagerListas() {
        this.listas = new Lista[DEFAULT_CAPACITY];
        this.listCount = 0;
    }

    /**
     * Método que permite saber se a lista recebida por argumento já existe no
     * array de listas ( através do seu nome)
     *
     * @param list Lista a ser comparada
     * @return True -> Se existe || False -> Senão existe
     */
    private boolean checkListName(Lista list) {
        for (int i = 0; i < listCount; i++) {
            if (listas[i].getNomeLista().equals(list.getNomeLista())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que permite adicionar uma nova lista ao nosso manager de listas. A
     * lista apenas será adicionada senão existir no nosso contentor de listas.
     *
     * @param list a ser adicionada
     * @return True -> Se for corretamente adicionada || False -> Senão for
     * adicionada.
     */
    public boolean addLista(Lista list) {
        if (this.listCount == 0) {
            this.listas[listCount] = list;
            this.listCount++;
            return true;
        } else if (!checkListName(list)) {
            this.listas[listCount] = list;
            this.listCount++;
            return true;
        } else {
            return false;
        }

    }

    public int getSpecifiedTasksStatusCount(Lista list) throws TaskNotFound {
        int counter = 0;
        for (int j = 0; j < list.getTaskNum(); j++) {
            if (list.getTarefa(j).getEstado() == EstadoTarefa.POR_REALIZAR) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * Método que imprime o nome todas as listas, e o número total de tarefas no
     * estado Por_Realizar por cada lista
     *
     * @return
     * @throws TaskNotFound Caso não seja encontrada nenhuma tarefa.
     */
    public String[] printAllList() throws TaskNotFound {

        if (this.listCount > 0) {
            String[] arrayInfoListas = new String[this.listCount];
            for (int i = 0; i < this.listCount; i++) {
                int counter = 0;
                counter = getSpecifiedTasksStatusCount(this.listas[i]);
                arrayInfoListas[i] = "Lista " + (i + 1) + " -> " + this.listas[i].getNomeLista() + " e tem " + counter + " tarefas por realizar";
                System.out.println("Lista " + (i + 1) + " -> " + this.listas[i].getNomeLista() + " e tem " + counter + " tarefas por realizar");

            }
            return arrayInfoListas;
        } else {
            throw new TaskNotFound("No tasks were found");
        }
    }

    public int getListCount() {
        return listCount;
    }

    public Lista[] getListas() {
        return listas;
    }

    /**
     * Método que permite a expansão do número de posições do nosso array de
     * listas
     */
    private void expandCapacity() {
        Lista[] newArray = new Lista[DEFAULT_CAPACITY + (DEFAULT_CAPACITY / 2)];
        System.arraycopy(listas, 0, newArray, 0, listas.length);

        listas = newArray;

    }
}
