package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Enumeracoes.EstadoTarefa;
import Enumeracoes.PrioridadeTarefa;
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
    public void VerificaListaValidaCom49Caracteres() {
        String testString = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH";
        Lista lista = new Lista(testString);
        String realCreated = lista.getCreated();
        String expectedCreated = "Lista criada com sucesso";
        assertEquals(expectedCreated, realCreated);
    }

    @Test
    public void VerificaListaValidaCom50Caracteres() {
        String testString = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH1";
        Lista lista = new Lista(testString);
        String realCreated = lista.getCreated();
        String expectedCreated = "Lista criada com sucesso";
        assertEquals(expectedCreated, realCreated);
    }

    @Test
    public void VerificaListaInvalidaCom51Caracteres() {

        exception1.expect(InstantiationError.class);
        String testString = "xzJ25iL8D2lUo2tlvlVqwhhWxNfojhuSvkf8wqO3gg401p7uH11";
        Lista lista = new Lista(testString);

    }

    // UserStorie 2 
    @Test
    public void VerificaCriarTarefaDescricaoValida() {
        Tarefa task1 = new Tarefa("Comprar Leite", 5);
        String real = task1.getCreated();
        String expected = "Tarefa criada com sucesso";
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriarTarefaDescricaoInvalida() {
        exception1.expect(InstantiationError.class);
        Tarefa task1 = new Tarefa("", 5);
    }

    @Test

    public void VerificarCriarTarefaDescricao99Caracteres() {
        String tmpString = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAy";
        Tarefa task1 = new Tarefa(tmpString, 5);
        String real = task1.getCreated();
        String expected = "Tarefa criada com sucesso";
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriarTarefaDescricao100Caracteres() {
        String tmpString = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAyA";
        Tarefa task1 = new Tarefa(tmpString, 5);
        String real = task1.getCreated();
        String expected = "Tarefa criada com sucesso";
        assertEquals(expected, real);
    }

    @Test
    public void VerificarCriarTarefaDescricaoInvalida101Caracteres() {
        String tmpString = "lqXmS7X3cujg8pm5XwJTzU60IcxnTjAgDZWFtZnpXIsNG89S64Zls23rqpDtobz0D1QK5hCoOKQogIiNWH1Hpo8YIfiqyUyIuAyAA";
        exception1.expect(InstantiationError.class);
        Tarefa task1 = new Tarefa(tmpString, 5);

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
    public void VerificarCriarTarefaDeadlineComTempoInvalidoZero() {

        exception1.expect(InstantiationError.class);

        Tarefa task1 = new Tarefa("Comprar Leite", 0);
    }

    @Test
    public void VerificarCriarTarefaDeadlineComTempoInvalidoNegativo() {

        exception1.expect(InstantiationError.class);

        Tarefa task1 = new Tarefa("Comprar Leite", -1);
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
        Lista list1 = new Lista("Supermercado");
        Tarefa task1 = new Tarefa("ComprarOvos", 5);
        Boolean real = list1.addTarefa(task1);
        Boolean expected = true;
        assertEquals(expected, real);

    }

    @Test

    public void VerificarAdicaoTarefaAListaComArrayCheio() {
        Lista list1 = new Lista("Supermercado");
        Tarefa task1 = new Tarefa("ComprarOvos", 5);
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        list1.addTarefa(task1);
        list1.addTarefa(task1);
        list1.addTarefa(task1);
        list1.addTarefa(task1);
        list1.addTarefa(task1);
        list1.addTarefa(task1);

    }

    //UserStorie 4
    @Test
    public void VerificarAtribuicaoPrioridadeP1() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.P1);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test
    public void VerificarAtribuicaoPrioridadeP2() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.P2);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test
    public void VerificarAtribuicaoPrioridadeP3() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.P3);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test
    public void VerificarAtribuicaoPrioridadeP4() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.P4);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test
    public void RetirarPrioridadeTarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        boolean real = tarefa1.setPriority(PrioridadeTarefa.SEM_PRIORIDADE);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test

    public void VerificarDaTarefaPorDefeitoIgualASemPrioridade() {
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

    public void VerificaAlteracaoEstadoTarefaParaRealizada() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        String expected = EstadoTarefa.REALIZADA.toString();
        tarefa1.setEstadoSeconds(EstadoTarefa.REALIZADA, null);
        String real = tarefa1.getEstado().toString();
        assertEquals(expected, real);
    }

    @Test

    public void VerificarImpossibilidadeTarefaRealizadaComDeadlineNaoNula() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.REALIZADA, 5);
    }

    @Test
    public void VerificarAlteracaoDeEstadoTarefaParaPORREALIZARDeadlineNaoNula() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        tarefa1.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, 20);
        String expected = EstadoTarefa.POR_REALIZAR.toString();
        String real = tarefa1.getEstado().toString();
        assertEquals(expected, real);
    }

    @Test
    public void VerificarAlteracaoDeEstadoTarefaParaPORREALIZARDeadlineNula() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);

        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, null);

    }

    @Test
    public void VerificarAlteracaoDeEstadoParaPorRealizarComUmaDeadlineNegativa() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        exception1.expect(IllegalArgumentException.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.POR_REALIZAR, -1);
    }

    @Test

    public void VerificarAlteracaoDeEstadoParaPorRealizarComUmaDataNaDeadlineValida() throws TaskStatusError {
        Date data = new Date(117, 11, 28);
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        tarefa1.setEstadoDate(EstadoTarefa.POR_REALIZAR, data);
        String expected = EstadoTarefa.POR_REALIZAR.toString();
        String real = tarefa1.getEstado().toString();
        assertEquals(expected, real);
    }

    @Test

    public void VerificarAlteracaoParaPorRealizarComUmaDeadlineDataInvalida() throws TaskStatusError {
        Date data = new Date(116, 11, 25);
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoDate(EstadoTarefa.POR_REALIZAR, data);
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
    @Test

    public void VerificarInsucessoNaIntroducaoDeEstadoAtrasada() throws TaskStatusError {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);

        exception1.expect(TaskStatusError.class);
        tarefa1.setEstadoSeconds(EstadoTarefa.ATRASADA, null);
    }

    //UserStorie 6
    @Test
    public void CriarUmaTagComSucesso() {
        Etiqueta tag1 = new Etiqueta("#ola");
        String expected = "Etiqueta creada com sucesso";
        String real = tag1.getCreated();
        assertEquals(expected, real);
    }

    @Test

    public void VerificarInsucessoCriacaoTagCom21Caracteres() {
        exception1.expect(InstantiationError.class);
        Etiqueta tag1 = new Etiqueta("#VBgrfr72uEAnv5uenVPr");

    }

    @Test
    public void VerificarSucessoCriacaoTagCom20Caracteres() {
        Etiqueta tag1 = new Etiqueta("#VBgrfr72uEAnv5uenVP");
        String expected = "Etiqueta creada com sucesso";
        String real = tag1.getCreated();
        assertEquals(expected, real);
    }

    //UserStorie 7
    @Test

    public void VerificarSucessoAdicaoTagsATarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag1 = new Etiqueta("#ola");
        boolean real = tarefa1.addTag(tag1);
        boolean expected = true;
        assertEquals(expected, real);
    }

    @Test
    public void VerificarInsucessoAdicionarTagAArrayDeTagsCheio() {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag1 = new Etiqueta("#ola");
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);

    }

    @Test
    public void VerificarSucessoAEncherArrayDeTagsNaTarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar Leite", 5);
        Etiqueta tag1 = new Etiqueta("#ola");
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);
        tarefa1.addTag(tag1);

        assertEquals(tarefa1.DEFAULT_CAPACITY, tarefa1.getTagCount());

    }

    //UserStorie 8
    @Test

    public void VerificarCriacaoDeLembreteComSucesso() {
        Lembrete reminder1 = new Lembrete(5, "A tarefa está a pedir para ser realizada");
        String expected = "Lembrete criado com sucesso";
        String real = reminder1.getCreated();
        assertEquals(expected, real);

    }

    @Test
    public void VerificarSucessoDescricaoComCaracteresEspeciais() {
        Lembrete reminder1 = new Lembrete(5, "A tarefa está a pedir para ser realizada ^^^#");
        String expected = "A tarefa está a pedir para ser realizada ^^^#";
        String real = reminder1.getRemindertask();
        assertEquals(expected, real);
    }

    @Test

    public void VerificarInsucessoNaCriacaoLembreteCom101Caracteres() {

        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(5, "2XvIxo2Wlvo1hUhO2kNeZqRDAublhtN4lihmGwBw10VrtVg85sgDXnrSlbvzjDFRmqfAP7wSwOyKYsnrNyxDktUFhTzH98TE0EKOF");
    }

    @Test

    public void VerificarSucessoNaCriacaoLembreteCom100Caracteres() {
        Lembrete reminder1 = new Lembrete(5, "2XvIxo2Wlvo1hUhO2kNeZqRDAublhtN4lihmGwBw10VrtVg85sgDXnrSlbvzjDFRmqfAP7wSwOyKYsnrNyxDktUFhTzH98TE0EKO");
        String expected = "Lembrete criado com sucesso";
        String real = reminder1.getCreated();
        assertEquals(expected, real);
    }

    @Test

    public void VerificarInsucessoNaCriacaoLembreteComTempoNulo() {
        exception1.expect(NullPointerException.class);
        Lembrete reminder1 = new Lembrete(null, "A tarefa está a pedir para ser realizada");
    }

    @Test

    public void VerificarInsucessoNaCriacaoLembreteComTempoNegativo() {
        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(-1, "A tarefa está a pedir para ser realizada");
    }

    @Test

    public void VerificarInsucessoNaCriacaoLembreteComTempoIgualA0() {
        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(0, "A tarefa está a pedir para ser realizada");
    }

    @Test

    public void VerificarSucessoNaCriacaoLembreteComTempoIgualA1() {
        Lembrete reminder1 = new Lembrete(1, "A tarefa está a pedir para ser realizada");
        String expected = "Lembrete criado com sucesso";
        String real = reminder1.getCreated();
        assertEquals(expected, real);
    }

    @Test
    public void VerificarSucessoNaCriacaoLembreteComData() {
        Lembrete reminder1 = new Lembrete(new Date(117, 11, 30), "A tarefa está a pedir para ser realizada");
        String expected = "Lembrete criado com sucesso";
        String real = reminder1.getCreated();
        assertEquals(expected, real);
    }

    @Test
    public void VerificarInsucessoNaCriacaoLembreteComDataInferiorAoSistema() {
        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(new Date(116, 11, 26), "A tarefa está a pedir para ser realizada");
    }

    @Test
    public void VerificarInsucessoNaCriacaoLembreteComDataIgualAoSistema() {
        exception1.expect(InstantiationError.class);
        Lembrete reminder1 = new Lembrete(new Date(), "A tarefa está a pedir para ser realizada");
    }

    //UserStorie 9 - Atribuir Lembretes a uma tarefa
    @Test
    public void VerificarSucessoAtribuicaoLembreteAUmaTarefa() {

        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        Lembrete reminder1 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada");

        boolean expected = true;
        boolean real = tarefa1.setLembrete(reminder1);
        assertEquals(expected, real);

    }

    @Test
    public void VerificarSucessoEncherArrayDeLembretesNumaTarefa() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        Lembrete reminder1 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada");
        for (int i = 0; i < tarefa1.DEFAULT_CAPACITY; i++) {
            tarefa1.setLembrete(reminder1);
        }

        assertEquals(tarefa1.DEFAULT_CAPACITY, tarefa1.getReminderCount());
    }

    @Test

    public void VerificarInsucessoAtribuicaoLembreteAArrayCheioDeLembretes() {
        Tarefa tarefa1 = new Tarefa("Comprar leite", 5);
        Lembrete reminder1 = new Lembrete(new Date(117, 12, 30), "A tarefa está a pedir para ser realizada");
        for (int i = 0; i < tarefa1.DEFAULT_CAPACITY; i++) {
            tarefa1.setLembrete(reminder1);
        }
        exception1.expect(ArrayIndexOutOfBoundsException.class);
        tarefa1.setLembrete(reminder1);
    }

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

}
