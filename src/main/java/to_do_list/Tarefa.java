/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to_do_list;

import Enumeracoes.EstadoTarefa;
import Enumeracoes.PrioridadeTarefa;
import Enumeracoes.TipoAlerta;
import Exceptions.TagNotFoundError;
import Exceptions.TaskStatusError;

import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rui
 */
public class Tarefa {
    
    private static int count = 0;
    private final int id;
    private String descricao;
    private EstadoTarefa estado;
    private Etiqueta[] listaEtiquetas;
    public final int DEFAULT_CAPACITY = 5;
    private int tagCount;
    private PrioridadeTarefa priority;
    private Lembrete[] reminders;
    private int reminderCount;
    private Timer deadline;
    private String created;

    /**
     * COnstrutor que nos permite inicializar uma tarefa com uma deadline que
     * atrasará a tarefa caso esta não seja executada antes do número de
     * segundos enviados por parámetro.
     *
     * @param tmpDesc Descrição da tarefa
     * @param deadline Tempo da deadline
     */
    public Tarefa(String tmpDesc, int deadline) throws InstantiationError {
        if (tmpDesc != null && !tmpDesc.equals("") && tmpDesc.length() <= 100 && deadline > 0) {
            this.created = "Tarefa criada com sucesso";
            System.out.println("Tarefa criada com sucesso");
            Tarefa.count = Tarefa.count + 1;
            this.id = Tarefa.count;
            this.descricao = tmpDesc;
            this.estado = EstadoTarefa.POR_REALIZAR;
            this.listaEtiquetas = new Etiqueta[DEFAULT_CAPACITY];
            this.tagCount = 0;
            this.priority = PrioridadeTarefa.SEM_PRIORIDADE;
            this.reminders = new Lembrete[DEFAULT_CAPACITY];
            this.reminderCount = 0;
            this.deadline = new Timer();
            this.deadline.schedule(new Deadline(), deadline * 1000);
            
        } else {
            throw new InstantiationError("Há algum erro com a sua tarefa");
        }
        
    }

    /**
     * COnstrutor que nos permite inicializar uma tarefa com uma deadline que
     * atrasará a tarefa caso esta não seja executada antes da data enviada por
     * parámetro.
     *
     * @param tmpDesc Descrição da tarefa
     * @param deadline Data limite da deadline
     */
    public Tarefa(String tmpDesc, Date deadline) {
        if (tmpDesc != null && !tmpDesc.equals("") && tmpDesc.length() <= 100 && (deadline.after(new Date()))) {
            this.created = "Tarefa criada com sucesso";
            System.out.println("Tarefa criada com sucesso");
            Tarefa.count = Tarefa.count + 1;
            this.id = Tarefa.count;
            this.descricao = tmpDesc;
            this.estado = EstadoTarefa.POR_REALIZAR;
            this.listaEtiquetas = new Etiqueta[DEFAULT_CAPACITY];
            this.tagCount = 0;
            this.priority = PrioridadeTarefa.SEM_PRIORIDADE;
            this.reminders = new Lembrete[DEFAULT_CAPACITY];
            this.reminderCount = 0;
            this.deadline = new Timer();
            this.deadline.schedule(new Deadline(), deadline);
        } else {
            throw new InstantiationError("Há algum erro com a sua tarefa");
        }
    }
    
    public int getTagCount() {
        return tagCount;
    }
    
    public String getCreated() {
        return created;
    }

    /**
     *
     * @return Retorna o id desta tarefa
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return a descrição desta tarefa
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao sets a new description to this task
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return Returns the task status
     */
    public EstadoTarefa getEstado() {
        return estado;
    }

    /**
     * Altera o estado da tarefa , introduzindo também uma nova deadline, se for
     * o caso
     *
     * @param estado Estado da tarefa
     * @param newDeadline deadline a ser introduzida
     * @throws TaskStatusError caso haja algum problema com o estado/deadline
     */
    public void setEstadoSeconds(EstadoTarefa estado, Integer newDeadline) throws TaskStatusError {
        
        if (estado == EstadoTarefa.REALIZADA && newDeadline == null) {
            this.estado = EstadoTarefa.REALIZADA;
            this.deadline.cancel();
        } else if (estado == EstadoTarefa.POR_REALIZAR && newDeadline != null) {
            this.estado = EstadoTarefa.POR_REALIZAR;
            setDeadline(newDeadline);
        } else {
            throw new TaskStatusError("Há algum problema com o estado da sua tarefa!");
        }
        
    }

    /**
     * Altera o estado da tarefa , introduzindo também uma nova deadline, se for
     * o caso
     *
     * @param status Estado da tarefa
     * @param newDeadline deadline a ser introduzida
     * @throws TaskStatusError caso haja algum problema com o estado/deadline
     */
    public void setEstadoDate(EstadoTarefa status, Date newDeadline) throws TaskStatusError {
        
        if (status == EstadoTarefa.REALIZADA && newDeadline == null) {
            this.estado = EstadoTarefa.REALIZADA;
            this.deadline.cancel();
        } else if (estado == EstadoTarefa.POR_REALIZAR && newDeadline != null && (newDeadline.after(new Date()))) {
            this.estado = EstadoTarefa.POR_REALIZAR;
            setDeadline(newDeadline);
        } else {
            throw new TaskStatusError("Há algum problema com o estado da sua tarefa!!");
        }
    }

    /**
     * Permite-nos adicionar uma Etiqueta a esta tarefa.
     *
     * @param ticket etiqueta a adiconar.
     */
    public boolean addTag(Etiqueta ticket) {
        
        this.listaEtiquetas[this.tagCount] = ticket;
        this.tagCount++;
        
        return true;
    }

    /**
     * Método privado da classe , que nos permite idenficar se o index de
     * etiqueta enviado por parámetro é válido.
     *
     * @param index Indíce da etiqueta a validar
     * @return true se é valido, false se não o é
     */
    private boolean isIndexValid(int index) {
        for (int i = 0; i < this.tagCount; i++) {
            if (this.listaEtiquetas[i] == this.listaEtiquetas[index]) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Retorna-nos a tag pretendida
     *
     * @param ticketNum index da tag pretendida
     * @return a tag pretendida
     * @throws TagNotFoundError Excepção lançada caso o index da tag não seja
     * válido.
     */
    public Etiqueta getTag(int ticketNum) throws TagNotFoundError {
        
        boolean isValid = isIndexValid(ticketNum);
        
        if ((ticketNum) <= this.tagCount && isValid) {
            
            return this.listaEtiquetas[ticketNum];
        }
        
        throw new TagNotFoundError("There is no ticket with the index: " + ticketNum);
        
    }

    /**
     * Permite-nos criar um lembrete através de uma data enviada por parametro.
     *
     * @param data data em que o lembrete será ativado.
     * @param desc descriçao do lembrete
     * @return
     */
    public boolean setLembrete(Date data, String desc, TipoAlerta tp) {
        this.reminders[reminderCount] = new Lembrete(data, desc, tp);
        reminderCount++;
        return true;
    }

    /**
     * É enviado um lembrete já criado, por parâmetro.
     *
     * @param tmp Lembrete a ser adicionado
     * @return
     */
    public boolean setLembrete(Lembrete tmp) {
        this.reminders[reminderCount] = tmp;
        reminderCount++;
        return true;
    }

    /**
     * Permite-nos criar um lembrete através da quantidade de segundos enviada
     * por parametro.
     *
     *
     * @param seconds Após estes segundos o lembrete será ativado
     * @param desc Descrição do lembrete
     * @param tp
     */
    public void setLembrete(int seconds, String desc, TipoAlerta tp) {
        this.reminders[reminderCount] = new Lembrete(seconds, desc, tp);
        reminderCount++;
    }

    /**
     * Permite-nos alterar o estado da tarefa para Atrasado É protected, porque
     * não deverá de estar ao alcance do utilizador , mudar o estado para
     * atrasado. Este será automaticamente alterado para Atrasado, caso seja
     * ultrapassada a deadline .
     */
    protected void setAtrasada() {
        this.estado = EstadoTarefa.ATRASADA;
    }

    /**
     * Permite-nos atribuir uma nova prioridade a esta tarefa
     *
     * @param priority prioridade a ser atribuida
     * @return
     */
    public boolean setPriority(PrioridadeTarefa priority) {
        if (priority != null) {
            this.priority = priority;
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Permite-nos obter a prioridade da tarefa
     *
     * @return prioridade da tarefa
     */
    public PrioridadeTarefa getPriority() {
        
        return this.priority;
    }

    /**
     * Cria uma nova deadline para a tarefa
     *
     * @param deadline segundos da deadline
     */
    public void setDeadline(int deadline) {
        this.deadline.cancel();
        this.deadline = new Timer();
        this.deadline.schedule(new Deadline(), deadline * 1000);
        
    }

    /**
     * Cria uma nova deadline para a tarefa
     *
     * @param deadline Data da deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline.cancel();
        this.deadline = new Timer();
        this.deadline.schedule(new Deadline(), deadline);
        
    }
    
    @Override
    public String toString() {
        return "A tarefa " + id + " tem:" + "\n" + "Descrição -> " + descricao
                + "\n" + "Estado -> " + estado + "\n" + "Prioridade -> " + priority
                + "\n" + "Tags: " + Arrays.toString(getNonNullTags(this.listaEtiquetas));
    }

    /**
     * Método privado que auxilia na impressão de apenas tags que não sejam
     * nulas
     *
     * @param data array de etiquetas
     * @return array de etiquetas sem etiquetas nulas
     */
    private Etiqueta[] getNonNullTags(Etiqueta[] data) {
        Etiqueta[] tmpTags = new Etiqueta[this.tagCount];
        
        for (int i = 0; i < this.tagCount; i++) {
            tmpTags[i] = this.listaEtiquetas[i];
            tmpTags[i].setTag("#" + tmpTags[i].getTag());
        }
        
        return tmpTags;
    }
    
    public int getReminderCount() {
        return reminderCount;
    }
    
    class Deadline extends TimerTask {
        
        @Override
        public void run() {
            System.out.println("O prazo da tarefa " + getId() + " acabou! Ficará neste momento atrasada!");
            deadline.cancel(); //ends the task
            setAtrasada();
        }
        
    }
    
}
