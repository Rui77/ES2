package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Enumeracoes.EstadoTarefa;
import Enumeracoes.PrioridadeTarefa;
import Enumeracoes.TipoAlerta;
import Exceptions.TaskNotFoundError;
import Exceptions.TaskStatusError;
import ManagerListas.ManagerListas;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import to_do_list.Etiqueta;
import to_do_list.Lembrete;
import to_do_list.Lista;
import to_do_list.Tarefa;

/**
 *
 * @author Rui
 */
public class To_do_list_test {

    public To_do_list_test() {

    }

    @Rule
    public final ExpectedException exception1 = ExpectedException.none();

    //UserStorie 1
    @Test
    public void VerificarListaValida() {
        Lista lista = new Lista("SuperMercado");
        String realCreated = lista.getCreated();
        String expectedCreated = "Lista criada com sucesso";
        assertEquals(expectedCreated, realCreated);

    }

    @Test
    public void VerificarListaInvalida() {

        exception1.expect(InstantiationError.class);
        Lista list = new Lista("");

    }

    @Test
    public void VerificaListaValidaComNomeComprido() {
        // Testa 49 caracteres no nome da lista
        String testString1 = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH";
        Lista lista1 = new Lista(testString1);
        String realCreated1 = lista1.getCreated();
        String expectedCreated1 = "Lista criada com sucesso";
        //Testa 50 caracteres no nome da lista 
        String testString2 = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH1";
        Lista lista2 = new Lista(testString2);
        String realCreated2 = lista2.getCreated();
        String expectedCreated2 = "Lista criada com sucesso";
        // Testa 51 caracteres no nome da lista
        exception1.expect(InstantiationError.class);
        String testString3 = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH11";
        Lista lista3 = new Lista(testString3);

        assertEquals(expectedCreated1, realCreated1);
        assertEquals(expectedCreated2, realCreated2);
    }

    @Test
    public void VerificarCriarTarefaDescricaoInvalida() {
        exception1.expect(InstantiationError.class);
        Tarefa task1 = new Tarefa("", 5);
    }

    @Test

    public void VerificarCriarTarefaDescricaoComprida() {
        //99
        String tmpString1 = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAy";
        Tarefa task1 = new Tarefa(tmpString1, 5);
        String real1 = task1.getCreated();
        String expected1 = "Tarefa criada com sucesso";
        assertEquals(expected1, real1);
        //100
        String tmpString2 = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAyA";
        Tarefa task2 = new Tarefa(tmpString2, 5);
        String real2 = task2.getCreated();
        String expected2 = "Tarefa criada com sucesso";
        assertEquals(expected2, real2);
        //101
        String tmpString = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAyAA";
        exception1.expect(InstantiationError.class);
        Tarefa task3 = new Tarefa(tmpString, 5);
    }

    @Test

    public void VerificarCriarTarefaDeadlineValida() {
        Tarefa task1 = new Tarefa("Comprar Leite", 5);
        String real = task1.getCreated();
        String expected = "Tarefa criada com sucesso";
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriarTarefaDeadlineComDataValida() {
        Date data = new Date(117, 11, 28);
        Tarefa task1 = new Tarefa("Comprar Leite", data);
        String real = task1.getCreated();
        String expected = "Tarefa criada com sucesso";
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriarTarefaDeadlineComTempoInvalido() {
//0
        exception1.expect(InstantiationError.class);

        Tarefa task1 = new Tarefa("Comprar Leite", 0);
        //-1
        exception1.expect(InstantiationError.class);

        Tarefa task2 = new Tarefa("Comprar Leite", -1);

    }

    @Test
    public void VerificarCriarTarefaDeadlineComDataInvalida() {
        Date data = new Date(116, 11, 23);
        exception1.expect(InstantiationError.class);

        Tarefa task1 = new Tarefa("Comprar Leite", data);
    }

    //UserStorie 3
    @Test
    public void VerificarAdicaoTarefaALista() {
        //Testa com 0 tarefas na lista
        Lista list1 = new Lista("Supermercado");
        Tarefa task1 = new Tarefa("ComprarOvos", 5);
        Boolean real = list1.addTarefa(task1);
        Boolean expected = true;

        //Testa com 5 tarefas na lista
        Lista list2 = new Lista("Supermercado");
        Tarefa task2 = new Tarefa("ComprarOvos", 5);
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        list2.addTarefa(task2);
        list2.addTarefa(task2);
        list2.addTarefa(task2);
        list2.addTarefa(task2);
        list2.addTarefa(task2);
        list2.addTarefa(task2);

        assertEquals(expected, real);
    }

    //UserStorie 4
    @Test
    public void VerificarAtribuicaoPrioridade() {
        //P1
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real1 = tarefa1.setPriority(PrioridadeTarefa.P1);
        boolean expected1 = true;

        //P2
        Tarefa tarefa2 = new Tarefa("Comprar leite", 5);
        boolean real2 = tarefa2.setPriority(PrioridadeTarefa.P2);
        boolean expected2 = true;

        //P3
        Tarefa tarefa3 = new Tarefa("Comprar leite", 5);
        boolean real3 = tarefa3.setPriority(PrioridadeTarefa.P3);
        boolean expected3 = true;

        //P4
        Tarefa tarefa4 = new Tarefa("Comprar leite", 5);
        boolean real4 = tarefa4.setPriority(PrioridadeTarefa.P4);
        boolean expected4 = true;

        assertEquals(expected1, real1);
        assertEquals(expected2, real2);
        assertEquals(expected3, real3);
        assertEquals(expected4, real4);

    }

    @Test
    public void RetirarPrioridadeTarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.SEM_PRIORIDADE);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test

    public void VerificarTarefaPorDefeitoIgualASemPrioridade() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        PrioridadeTarefa real = tarefa1.getPriority();
        assertEquals(PrioridadeTarefa.SEM_PRIORIDADE, real);
    }

    @Test
    public void VerificarInsucessoNaAtribuicaoDePrioridadeNula() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(null);
        boolean expected = false;
        assertEquals(expected, real);

    }

    // UserStorie 5 
    @Test

    public void VerificarEstadoTarefaAposCriarTarefaIgualAPorRealizar() {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        String expected = EstadoTarefa.POR_REALIZAR.toString();
        String real = tarefa1.getEstado().toString();
        assertEquals(expected, real);
    }

    @Test

    public void VerificaAlteracaoEstadoTarefa() throws TaskStatusError {
        //para realizada
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        String expected1 = EstadoTarefa.REALIZADA.toString();
        tarefa1.setEstadoSeconds(EstadoTarefa.REALIZADA, null);
        String real1 = tarefa1.getEstado().toString();

        //Impossibilidade Tarefa Realizada Com Deadline Nao Nula
        Tarefa tarefa2 = new Tarefa("Comprar Leite", 5);
        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.REALIZADA, 5);

        // Alteracao De Estado Tarefa Para PORREALIZAR Deadline Nao Nula
        Tarefa tarefa3 = new Tarefa("Comprar Leite", 5);
        tarefa3.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, 20);
        String expected3 = EstadoTarefa.POR_REALIZAR.toString();
        String real3 = tarefa3.getEstado().toString();

        //Alteracao De Estado Tarefa Para PORREALIZAR Deadline Nula
        Tarefa tarefa4 = new Tarefa("Comprar Leite", 5);
        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, null);

        //Alteracao De Estado Para Por Realizar Com Uma Deadline Negativa
        Tarefa tarefa5 = new Tarefa("Comprar Leite", 5);
        exception1.expect(IllegalArgumentException.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, -1);

        //Alteracao De Estado Para Por Realizar Com Uma Data Na Deadline Valida
        Date data6 = new Date(117, 11, 28);
        Tarefa tarefa6 = new Tarefa("Comprar Leite", 5);
        tarefa6.setEstadoDate(EstadoTarefa.POR_REALIZAR, data6);
        String expected6 = EstadoTarefa.POR_REALIZAR.toString();
        String real6 = tarefa1.getEstado().toString();

        // Alteracao Para Por Realizar Com Uma Deadline Data Invalida
        Date data7 = new Date(116, 11, 25);
        Tarefa tarefa7 = new Tarefa("Comprar Leite", 5);
        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoDate(EstadoTarefa.POR_REALIZAR, data7);

        // Insucesso Na Introducao De Estado Atrasada
        Tarefa tarefa8 = new Tarefa("Comprar leite", 5);

        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.ATRASADA, null);

        assertEquals(expected3, real3);
        assertEquals(expected1, real1);
        assertEquals(expected6, real6);
    }

//    @Test
//
//    public void VerificarSeTarefaFicaAtrasada() throws InterruptedException {
//        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
//        String expected = EstadoTarefa.ATRASADA.toString();
//        TimeUnit.SECONDS.sleep(6);
//        String real = tarefa1.getEstado().toString();
//        assertEquals(expected, real);
//    }
    //UserStorie 6
    @Test
    public void CriarUmaTagComSucesso() {
        Etiqueta tag1 = new Etiqueta("ola");
        String expected = "Etiqueta criada com sucesso";
        String real = tag1.getCreated();
        assertEquals(expected, real);
    }

    @Test
    public void VerificarTagValidaComNomeComprido() {
        //Tag com 21 caracteres
        exception1.expect(InstantiationError.class);
        Etiqueta tag1 = new Etiqueta("#VBgrfr72uEAnv5uenVPr");
        //Tag com 20 caracteres
        Etiqueta tag2 = new Etiqueta("#VBgrfr72uEAnv5uenVP");
        String expected2 = "Etiqueta criada com sucesso";
        String real2 = tag2.getCreated();
        //Tag com 19 caracteres
        Etiqueta tag3 = new Etiqueta("#VBgrfr72uEAnv5uenV");
        String expected3 = "Etiqueta criada com sucesso";
        String real3 = tag3.getCreated();

        assertEquals(expected2, real2);
        assertEquals(expected3, real3);

    }

    @Test
    public void VerificarCriacaoTagComInsucessoNomeNulo() {
        exception1.expect(InstantiationError.class);
        Etiqueta tag1 = new Etiqueta("");
    }

    //UserStorie 7
    @Test
    public void VerificarAdicaoTagsATarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag1 = new Etiqueta("ola");
        boolean real1 = tarefa1.addTag(tag1);
        boolean expected1 = true;

        Tarefa tarefa2 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag2 = new Etiqueta("ola");
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        tarefa2.addTag(tag2);
        tarefa2.addTag(tag2);
        tarefa2.addTag(tag2);
        tarefa2.addTag(tag2);
        tarefa2.addTag(tag2);
        tarefa2.addTag(tag2);

        Tarefa tarefa3 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag3 = new Etiqueta("ola");
        tarefa3.addTag(tag3);
        tarefa3.addTag(tag3);
        tarefa3.addTag(tag3);
        tarefa3.addTag(tag3);
        tarefa3.addTag(tag3);

        assertEquals(expected1, real1);
        assertEquals(tarefa3.DEFAULT_CAPACITY, tarefa3.getTagCount());

    }

    //UserStorie 8
    @Test

    public void VerificarCriacaoDeLembreteComSucesso() {

        Lembrete reminder1 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        String expected1 = "Lembrete criado com sucesso";
        String real1 = reminder1.getCreated();

        // Sucesso Descricao Com Caracteres Especiais        
        Lembrete reminder2 = new Lembrete(5, "A tarefa está a pedir para ser realizada ^^^#", TipoAlerta.THREAD);
        String expected2 = "A tarefa está a pedir para ser realizada ^^^#";
        String real2 = reminder2.getRemindertask();

        assertEquals(expected1, real1);
        assertEquals(expected2, real2);

    }

    @Test

    public void VerificarCriacaoLembreteComDescricaoComprida() {
//101
        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(5, "2XvIxo2Wlvo1hUhO2kNeZqRDAublhtN4lihmGwBw10VrtVg85sgDXnrSlbvzjDFRmqfAP7wSwOyKYsnrNyxDktUFhTzH98TE0EKOF", TipoAlerta.THREAD);
        //100

        Lembrete reminder2 = new Lembrete(5, "2XvIxo2Wlvo1hUhO2kNeZqRDAublhtN4lihmGwBw10VrtVg85sgDXnrSlbvzjDFRmqfAP7wSwOyKYsnrNyxDktUFhTzH98TE0EKO", TipoAlerta.THREAD);
        String expected2 = "Lembrete criado com sucesso";
        String real2 = reminder1.getCreated();

        //99
        Lembrete reminder3 = new Lembrete(5, "2XvIxo2Wlvo1hUhO2kNeZqRDAublhtN4ihmGwBw10VrtVg85sgDXnrSlbvzjDFRmqfAP7wSwOyKYsnrNyxDktUFhTzH98TE0EKO", TipoAlerta.THREAD);
        String expected3 = "Lembrete criado com sucesso";
        String real3 = reminder3.getCreated();

        assertEquals(expected2, real2);
        assertEquals(expected3, real3);

    }

    @Test

    public void VerificarCriacaoLembreteSegundoValoresTemporais() {
        //Lembrete com tempo Nulo
        exception1.expect(NullPointerException.class);
        Lembrete reminder1 = new Lembrete(null, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        //Lembrete com tempo negativo
        exception1.expect(InstantiationError.class);
        Lembrete reminder2 = new Lembrete(-1, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        //Lembrete com tempo igual a 0
        exception1.expect(InstantiationError.class);
        Lembrete reminder3 = new Lembrete(0, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        //Lembrete com tempo igual a 1 
        Lembrete reminder4 = new Lembrete(1, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        String expected4 = "Lembrete criado com sucesso";
        String real4 = reminder4.getCreated();

        //Lembrete com data superior à do sistema
        Lembrete reminder5 = new Lembrete(new Date(117, 11, 30), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        String expected5 = "Lembrete criado com sucesso";
        String real5 = reminder5.getCreated();

        //Lembrete com data inferior à do sistema
        exception1.expect(InstantiationError.class);
        Lembrete reminder6 = new Lembrete(new Date(116, 11, 26), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        //Lembrete com data igual ao sistema
        exception1.expect(InstantiationError.class);
        Lembrete reminder7 = new Lembrete(new Date(), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        assertEquals(expected4, real4);
        assertEquals(expected5, real5);
    }

    //UserStorie 9 - Atribuir Lembretes a uma tarefa
    @Test
    public void VerificarSucessoAtribuicaoLembretesAUmaTarefa() {

        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        Lembrete reminder1 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);

        boolean expected = true;
        boolean real = tarefa1.setLembrete(reminder1);

        //Verificar Sucesso Encher Array De Lembretes Numa Tarefa
        Tarefa tarefa2 = new Tarefa("Comprar leite", 5);
        Lembrete reminder2 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        for (int i = 0; i < tarefa1.DEFAULT_CAPACITY; i++) {
            tarefa2.setLembrete(reminder2);
        }


        assertEquals(expected, real);
                assertEquals(tarefa2.DEFAULT_CAPACITY, tarefa2.getReminderCount());

    }

    @Test

    public void VerificarInsucessoAtribuicaoLembreteAArrayCheioDeLembretes() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        Lembrete reminder1 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        for (int i = 0; i < tarefa1.DEFAULT_CAPACITY; i++) {
            tarefa1.setLembrete(reminder1);
        }
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        tarefa1.setLembrete(reminder1);
    }
//user stories das listagens

    @Test

    public void VerificarImpressãoDeTodasAsListas() throws TaskNotFoundError {
        Lista lista1 = new Lista("Supermercado");
        Lista lista2 = new Lista("Loja");
        ManagerListas manager = new ManagerListas();
        manager.addLista(lista1);
        manager.addLista(lista2);
        String[] realArray = manager.printAllList();
        String[] expectedArray = new String[manager.getListCount()];
        for (int i = 0; i < manager.getListCount(); i++) {
            int counter = 0;
            counter = manager.getSpecifiedTasksStatusCount(manager.getListas()[i]);
            expectedArray[i] = "Lista " + (i + 1) + " -> " + manager.getListas()[i].getNomeLista() + " e tem " + counter + " tarefas por realizar";

        }
        assertArrayEquals(expectedArray, realArray);
    }

    @Test

    public void VerificarContagemDeTarefasPorRealizarPorLista() throws TaskNotFoundError {
        Lista lista1 = new Lista("Supermercado");
        Lista lista2 = new Lista("Loja");
        ManagerListas manager = new ManagerListas();
        manager.addLista(lista1);
        manager.addLista(lista2);
        int[] realArray = new int[manager.getListCount()];
        int[] expectedArray = new int[manager.getListCount()];
        for (int i = 0; i < manager.getListCount(); i++) {
            realArray[i] = manager.getSpecifiedTasksStatusCount(manager.getListas()[i]);
            expectedArray[i] = manager.getSpecifiedTasksStatusCount(manager.getListas()[i]);
        }
        assertArrayEquals(expectedArray, realArray);

    }

    @Test

    public void VerificarInsucessoNaImpressãoNãoHavendoListas() throws TaskNotFoundError {
        ManagerListas manager = new ManagerListas();
        exception1.expect(TaskNotFoundError.class);
        manager.printAllList();

    }

    @Test

    public void VerificarInsucessoNaAdicaoDeListasComMesmoNome() {
        ManagerListas manager = new ManagerListas();
        Lista lista1 = new Lista("Loja");
        Lista lista2 = new Lista("Loja");
        manager.addLista(lista1);
        boolean real = manager.addLista(lista2);
        boolean expected = false;
        assertEquals(expected, real);

    }

    @Test

    public void VerificarSucessoNaOrdenacaoDeTarefasPorPrioridade() throws TaskNotFoundError {
        Lista lista1 = new Lista("Supermercado");
        Tarefa tarefa1 = new Tarefa("Comprar leite", 1);
        Tarefa tarefa2 = new Tarefa("Comprar ovos", 10);
        Tarefa tarefa3 = new Tarefa("Comprar água", 10);
        tarefa1.setPriority(PrioridadeTarefa.P3);
        tarefa2.setPriority(PrioridadeTarefa.P1);
        tarefa3.setPriority(PrioridadeTarefa.P2);
        lista1.addTarefa(tarefa1);
        lista1.addTarefa(tarefa2);
        lista1.addTarefa(tarefa3);
        String[] prioridadesExpected = new String[3];
        String[] prioridadesReal = new String[3];
        prioridadesExpected[0] = PrioridadeTarefa.P1.toString();
        prioridadesExpected[1] = PrioridadeTarefa.P2.toString();
        prioridadesExpected[2] = PrioridadeTarefa.P3.toString();

        for (int i = 0; i < 3; i++) {
            prioridadesReal[i] = lista1.getTarefa(i).getPriority().toString();
        }

        assertArrayEquals(prioridadesExpected, prioridadesReal);
    }

    @Test

    public void VerificarSucessoNoAgrupamentoDasTarefasPorEstado() throws TaskStatusError, TaskNotFoundError {
        Lista lista1 = new Lista("Supermercado");
        Tarefa tarefa1 = new Tarefa("Comprar leite", 1);
        Tarefa tarefa2 = new Tarefa("Comprar ovos", 1);
        Tarefa tarefa3 = new Tarefa("Comprar água", 1);
        tarefa2.setEstadoDate(EstadoTarefa.REALIZADA, null);
        lista1.addTarefa(tarefa1);
        lista1.addTarefa(tarefa2);
        lista1.addTarefa(tarefa3);
        lista1.groupTasksByStatus();
        String[] expected = {EstadoTarefa.POR_REALIZAR.toString(), EstadoTarefa.POR_REALIZAR.toString(), EstadoTarefa.REALIZADA.toString()};
        String[] real = new String[3];
        for (int i = 0; i < lista1.getTaskNum(); i++) {
            real[i] = lista1.getTarefa(i).getEstado().toString();
        }

        assertArrayEquals(expected, real);
    }

    @Test
    public void VerificarSucessoNaImpressaoDaTarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 1);
        String expected = "A tarefa 1 tem: "
                + "Descrição -> Comprar ovos "
                + "Estado -> POR_REALIZAR "
                + "Prioridade -> P1 "
                + "Tags: [] "
                + "Lembrete criado com sucesso";
        String real = tarefa1.toString();
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriacaodosdeDiferentesTiposdeLembretes() {

        //thread
        Lembrete reminder1 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
        String expected1 = "THREAD";
        String real1 = reminder1.getTipoalerta().toString();
        //mensagem
        Lembrete reminder2 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.MENSAGEM_TELEMOVEL);
        String expected2 = "MENSAGEM_TELEMOVEL";
        String real2 = reminder2.getTipoalerta().toString();
        //email
        
     Lembrete reminder3 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.EMAIL);
        String expected3 = "EMAIL";
        String real3 = reminder3.getTipoalerta().toString();
        assertEquals(expected1, real1);
             assertEquals(expected2, real2);
            assertEquals(expected3, real3);
    }

//    @Test
//    public void VerificarAlterarTipoDeAlertaDoLembrete(){
//        
//        //thread
//        Lembrete reminder1 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.THREAD);
//        String expected1 = "TipoAlerta.THREAD";
//        String real1 = reminder1.getTipoalerta().toString();
//        //mensagem
//
//        Lembrete reminder2 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.MENSAGEM_TELEMOVEL);
//        String expected2 = "Lembrete criado com sucesso";
//        String real2 = reminder2.getTipoalerta().toString();
//        //email
//
//        Lembrete reminder3 = new Lembrete(5, "A tarefa está a pedir para ser realizada", TipoAlerta.EMAIL);
//        String expected3 = "Lembrete criado com sucesso";
//        String real3 = reminder3.getTipoalerta().toString();
//        assertEquals(expected1, real1);
//        assertEquals(expected2, real2);
//        assertEquals(expected3, real3);
//        
//        
//        
    }
    
    
    
    
    
//}
