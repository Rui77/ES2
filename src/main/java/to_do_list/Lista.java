/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to_do_list;

import Enumeracoes.EstadoTarefa;
import Exceptions.TaskNotFound;

/**
 *
 * @author Rui
 */
public class Lista {

    private String nomeLista;
    private Tarefa[] listaTarefas;
    private final int DEFAULT_CAPACITY = 5;
    private int taskNum;
    private String created;

    /**
     * Construtor da classe Lista que recebe como parâmetro único o nome da
     * lista pretendida. O array de tarefas é iniciado por default com uma
     * capacidade de 100.
     *
     * @param tmpNome - O nome correspondente à nova lista criada.
     */
    public Lista(String tmpNome) throws InstantiationError {
        if (tmpNome != null && tmpNome.equals("") != true && tmpNome.length() <= 50) {
            this.nomeLista = tmpNome;
            this.listaTarefas = new Tarefa[DEFAULT_CAPACITY];
            this.taskNum = 0;
            this.created = "Lista criada com sucesso";
            System.out.println("Lista criada com sucesso");
        } else {

            throw new InstantiationError("String cannot be null");
        }
    }

    public String getCreated() {
        return created;
    }

    /**
     * @return Retorna o nome da lista
     */
    public String getNomeLista() {
        return nomeLista;
    }

    /**
     * Método que nos permite atribuir um nome à lista
     *
     * @param nomeLista String que atribuirá o nome à lista.
     */
    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    /**
     * Este método retornará uma tarefa presente nesta lista, através do seu id.
     *
     * @param index index da tarefa que se pretende retornar.
     * @return Tarefa retornada
     * @throws TaskNotFound Caso não exista nenhuma tarefa com tal id
     */
    public Tarefa getTarefa(int index) throws TaskNotFound {

        if (listaTarefas[index] != null) {
            return listaTarefas[index];
        } else {
            throw new TaskNotFound("There is no task with that Index");
        }

    }

    /**
     * Metodo que retorna o numero de tarefas presentes nesta lista
     *
     * @return numero de tarefas
     */
    public int getTaskNum() {
        return taskNum;
    }

    /**
     * Método que nos indica se o array de tarefas está cheio.
     *
     * @return True -> caso esteja || False -> Caso nao esteja.
     */
    private boolean isFull() {

        return this.taskNum == listaTarefas.length;
    }

    /**
     * Método que nos permite adicionar uma tarefa à lista
     *
     * @param task Tarefa pretendida a adicionar
     * @return
     */
    public boolean addTarefa(Tarefa task) {
        if (this.taskNum <= DEFAULT_CAPACITY) {
            this.listaTarefas[taskNum] = task;
            taskNum++;
            if (this.taskNum > 1) {
                this.bubbleSort(this.listaTarefas);
            }

            return true;
        } else {
            return false;
        }

    }

    /**
     * Método privado que nos ordenará o array de tarefas desta lista por
     * prioridade de Tarefa.
     *
     * @param data Array de tarefas desta lista
     */
    private void bubbleSort(Tarefa[] data) {
        int position, scan;
        Tarefa temp;
        for (position = this.taskNum - 1; position >= 0; position--) {
            for (scan = 0; scan <= position - 1; scan++) {
                if (data[scan].getPriority().compareTo(data[scan + 1].getPriority()) > 0) {
                    temp = data[scan];
                    data[scan] = data[scan + 1];
                    data[scan + 1] = temp;
                }
            }
        }
    }

    /**
     * Método que nos permitirá ordenar as tarefas desta lista por estado de
     * tarefa
     */
    public void groupTasksByStatus() {
        int taskCounter = 0;
        int tmp = 0;
        Tarefa[] tmpTaskArray = new Tarefa[this.listaTarefas.length];

        while (taskCounter != taskNum) {
            for (int i = 0; i < this.taskNum; i++) {
                if (this.listaTarefas[i].getEstado() == EstadoTarefa.POR_REALIZAR && tmp == 0) {
                    tmpTaskArray[taskCounter] = this.listaTarefas[i];
                    taskCounter++;
                } else if (this.listaTarefas[i].getEstado() == EstadoTarefa.REALIZADA && tmp == 1) {
                    tmpTaskArray[taskCounter] = this.listaTarefas[i];
                    taskCounter++;
                } else if (this.listaTarefas[i].getEstado() == EstadoTarefa.ATRASADA && tmp == 2) {
                    tmpTaskArray[taskCounter] = this.listaTarefas[i];
                    taskCounter++;
                } else if (tmp > 2) {
                    tmpTaskArray[taskCounter] = this.listaTarefas[i];
                    taskCounter++;
                }
            }

            tmp++;
        }

        this.listaTarefas = tmpTaskArray;

    }

    public Tarefa[] getListaTarefas() {
        return listaTarefas;
    }

}
