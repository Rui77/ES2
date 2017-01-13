/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Enumeracoes.PrioridadeTarefa;
import Enumeracoes.TipoAlerta;
import Exceptions.TagNotFoundError;
import Exceptions.TaskNotFoundError;
import Exceptions.TaskQuantityError;
import Exceptions.TaskStatusError;
import ManagerListas.ManagerListas;
import java.util.Date;

import to_do_list.Etiqueta;
import to_do_list.Lista;
import to_do_list.Tarefa;

/**
 *
 * @author Rui
 */
public class DEMO {

    /**
     * @param args the command line arguments
     * @throws Exceptions.TaskNotFoundError
     * @throws TagNotFoundError
     * @throws Exceptions.TaskQuantityError
     * @throws Exceptions.TaskStatusError
     */
    public static void main(String[] args) throws TaskNotFoundError, TagNotFoundError, TaskQuantityError, TaskStatusError {
        Etiqueta tag = new Etiqueta("asdas");
        Tarefa task = new Tarefa("asdsa", 2);
        task.addTag(tag);
        System.out.println(task.toString());

    }

}
