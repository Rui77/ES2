/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Enumeracoes.EstadoTarefa;
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

        Date data = new Date(2016, 12, 20);
        Lista lista1 = new Lista("SuperMercado");
        ManagerListas manager = new ManagerListas();
        System.out.println(lista1.getNomeLista());
        Tarefa tarefa1 = new Tarefa("Comprar leite", 1);
        Tarefa tarefa2 = new Tarefa("Comprar ovos", 10);
        //tarefa2.setEstadoDate(EstadoTarefa.POR_REALIZAR, new Date(116, 11, 30));
        tarefa1.setPriority(null);
        tarefa2.setPriority(PrioridadeTarefa.P1);
        Tarefa tarefa3 = new Tarefa("Comprar água", 10);
        //tarefa3.setEstadoSeconds(EstadoTarefa.REALIZADA, null);
        tarefa3.setPriority(PrioridadeTarefa.P1);
        tarefa1.setLembrete(5, "Só para testar", TipoAlerta.THREAD);

        lista1.addTarefa(tarefa1);
        lista1.addTarefa(tarefa3);
        lista1.addTarefa(tarefa2);
        lista1.groupTasksByStatus();
        Lista lista2 = new Lista("Loja");

        tarefa1.addTag(new Etiqueta("#trabalho"));

        System.out.println(tarefa1.toString());
        System.out.println(tarefa2.toString());

        tarefa1.setLembrete(5, "Testar esta coisa", TipoAlerta.THREAD);

        System.out.println("*****************************************");
        manager.addLista(lista1);
        manager.addLista(lista2);
        manager.printAllList();
    }

}
