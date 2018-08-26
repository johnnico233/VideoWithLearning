package com.yosoro.video.domain.timer;

import javax.servlet.http.HttpSession;

public class EmailTimer implements ScheduleTimer{
    private String mail;
    private volatile long startTime;
    private volatile int life;
    private String sessionId;

    public EmailTimer(String mail) {
        this.mail = mail;
    }

    public EmailTimer(String mail, long startTime, int life) {
        this.mail = mail;
        this.startTime = startTime;
        this.life = life;
    }

    public EmailTimer(String mail, long startTime, int life, String sessionId) {
        this.mail = mail;
        this.startTime = startTime;
        this.life = life;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public EmailTimer() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getStartTime() {
        return startTime;
    }

    public synchronized void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLife() {
        return life;
    }

    public synchronized void setLife(int life) {
        this.life = life;
    }

    @Override
    public synchronized boolean isValid() {
        return (System.currentTimeMillis()-startTime)/1000>life;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj !=null){
            if(obj instanceof EmailTimer){
                return mail.equals(((EmailTimer) obj).mail);
            }
            return false;
        }
        return false;
    }
}
