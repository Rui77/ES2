/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to_do_list;

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

    /**
     * Construtor que nos permite criar um lembrete que será ativado em segundos
     *
     * @param seconds Atributo que ativará o lembrete
     * @param task Descrição do lembrete
     */
    public Lembrete(int seconds, String task) throws InstantiationError {  // Creates a reminder that expires in seconds
        if (task != null && !task.equals("") && task.length() <= 100 && seconds > 0) {
            this.created = "Lembrete criado com sucesso";
            System.out.println("Lembrete criado com sucesso");
            timer = new Timer();
            this.remindertask = task;
            timer.schedule(new ReminderRun(), seconds);
        } else {
            throw new InstantiationError("Parece haver algum problema com o seu lembrete!");
        }
    }

    /**
     * Construtor que nos permite criar um lembrete que sera ativado na data
     * enviada por parametro
     *
     * @param date data enviada por parametro para ativar lembrete
     * @param task descriçao do lembrete
     */
    public Lembrete(Date date, String task) throws InstantiationError {
        if (task != null && !task.equals("") && task.length() <= 100 && (date.after(new Date()))) {
            this.created = "Lembrete criado com sucesso";
            System.out.println("Lembrete criado com sucesso");
            timer = new Timer();
            this.remindertask = task;
            timer.schedule(new ReminderRun(), date);
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

    public String getCreated() {
        return created;
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
