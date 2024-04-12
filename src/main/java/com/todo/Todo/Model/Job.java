package com.todo.Todo.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Job {
    public String line;
    public Boolean isFinished;
    public LocalDate date;
    public UUID uuid;

    public Job() {
        this.uuid = UUID.randomUUID();
        this.line = "";
        this.isFinished = false;
        this.date =  LocalDate.now();
    }
    public Job(String line) {
        this.uuid = uuid;
        this.line = line;
        this.isFinished = false;
        this.date = date;
    }
    public Job(String line, Boolean isFinished) {
        this.line = line;
        this.isFinished = isFinished;
    }
    public Job(LocalDate date, String line, UUID uuid, Boolean isFinished) {
        this.date = date;
        this.line = line;
        this.uuid = uuid;
        this.isFinished = isFinished;
    }
    public Job(String line, Boolean isFinished, LocalDate date) {
        this.line = line;
        this.isFinished = isFinished;
        this.date = date;
    }
    public Job(String line, Boolean isFinished, LocalDate date, UUID uuid) {
        this.line = line;
        this.isFinished = isFinished;
        this.date = date;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void changeStatus() {
        isFinished = !isFinished;
    }

    @Override
    public String toString(){
        if (!isFinished) {
            return line + " " + " - On going" + "\n";
        } else {
            return line + " " + " - finished" + "\n";
        }
    }


}
