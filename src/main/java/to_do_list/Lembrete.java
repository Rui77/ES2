/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to_do_list;

import Enumeracoes.TipoAlerta;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Rui
 */
public class Lembrete {

    private Timer timer;
    private String remindertask;
    private String created;
    private TipoAlerta tipoalerta;

    /**
     * Construtor que nos permite criar um lembrete que será ativado em segundos
     *
     * @param seconds Atributo que ativará o lembrete
     * @param task Descrição do lembrete
     * @param tipoalerta Tipo de alerta escolhido pelo user
     */
    public Lembrete(int seconds, String task, TipoAlerta tipoalerta) throws InstantiationError {  // Creates a reminder that expires in seconds
        if (task != null && !task.equals("") && task.length() <= 100 && seconds > 0 && tipoalerta != null) {
            this.created = "Lembrete criado com sucesso";
            System.out.println("Lembrete criado com sucesso");
            this.tipoalerta = tipoalerta;
            if (this.tipoalerta.equals(TipoAlerta.THREAD)) {
                this.remindertask = task;
                timer = new Timer();
                timer.schedule(new ReminderRun(), seconds);
            }

        } else {
            throw new InstantiationError("Parece haver algum problema com o seu lembrete!");
        }
        this.tipoalerta = tipoalerta;
    }

    /**
     * Construtor que nos permite criar um lembrete que sera ativado na data
     * enviada por parametro
     *
     * @param date data enviada por parametro para ativar lembrete
     * @param task descriçao do lembrete
     * @param tipoalerta
     */
    public Lembrete(Date date, String task, TipoAlerta tipoalerta) throws InstantiationError {
        if (task != null && !task.equals("") && task.length() <= 100 && (date.after(new Date())) && tipoalerta != null) {
            this.tipoalerta = tipoalerta;
            this.created = "Lembrete criado com sucesso";
            System.out.println("Lembrete criado com sucesso");
            if (this.tipoalerta.equals(TipoAlerta.THREAD)) {
                this.remindertask = task;
                timer = new Timer();
                timer.schedule(new ReminderRun(), date);
            }
        } else {
            throw new InstantiationError("Parece haver algum problema com o seu lembrete!");
        }
    }

    /**
     * Retorna-nos a descriçao do lembrete
     *
     * @return
     */
    public String getRemindertask() {
        return remindertask;
    }

    /**
     * Atribuimos uma descriçao ao lembrete.
     *
     * @param remindertask
     */
    public void setRemindertask(String remindertask) {
        this.remindertask = remindertask;
    }

    /**
     * Método que retorna a string indicando se o construtor foi instanciado com
     * sucesso.
     *
     * @return String com a mesnagem de sucesso
     */
    public String getCreated() {
        return created;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    /**
     * Retorna o tipo de alerta deste lembrete
     *
     * @return tipo de alerta
     */
    public TipoAlerta getTipoalerta() {
        return tipoalerta;
    }

    /**
     *
     * @param tipoalerta
     */
    public void setTipoalerta(TipoAlerta tipoalerta) {
        this.tipoalerta = tipoalerta;
    }

    class ReminderRun extends TimerTask {

        /**
         * Método da classe aninhada ReminderRun que envia um thread ,
         * permitindo-nos, através deste thread a publicação do lembrete no ecrã
         * e depois o seu cancelamento.
         */
        @Override
        public void run() {
            System.out.println("A tarefa: " + getRemindertask() + " ,está a pedir para ser executada!");
            timer.cancel(); //ends the task
        }
    }

}
