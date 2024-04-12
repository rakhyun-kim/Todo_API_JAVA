package com.todo.Todo.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Component
public class CheckList {
    Map<UUID, Job> jobs;
    JSONObject jsonObject = new JSONObject();
    JSONParser jsonParser = new JSONParser();

    public CheckList() throws IOException, ParseException {
        this.jobs = new HashMap<UUID, Job>();
        this.readJson();
    }

    public List<Job> getAllJobs() { // 1. all todolist items
        return new ArrayList<>(jobs.values());
    }
    public List<Job> getFinishedJob() { // 2. all todolist that are finished
        List<Job> finishedJobs = new ArrayList<>();
        for (Map.Entry<UUID, Job> j : jobs.entrySet()) {
            if (j.getValue().isFinished == true) {
                finishedJobs.add(j.getValue());
            }
        }
        return finishedJobs;
    }
    public List<Job> getUnfinishedJob() { // 3. all todolist that are unfinished
        List<Job> unFinishedJobs = new ArrayList<>();
        for(Map.Entry<UUID, Job> j : jobs.entrySet()) {
            if (j.getValue().isFinished == false) {
                unFinishedJobs.add(j.getValue());
            }
        }
        return unFinishedJobs;
    }
    public List<Job> getJobsWithDate(LocalDate date) { // 4. all todolist for a given month that one list
        System.out.println("Looking for jobs with date: " + date.toString());
        List<Job> jobWithDate = new ArrayList<>();
        for(Map.Entry<UUID, Job> j : jobs.entrySet()) {
            if (j.getValue().date.equals(date)) {
                jobWithDate.add(j.getValue());
            }
        }

        return jobWithDate;
    }
    public void addJob(Job job) { // 6. add a new todolist item
        UUID uuid = UUID.randomUUID();
        job.setUuid(uuid);
        jobs.put(uuid,job);
    }
    public void changeJobStatusDone(UUID uuid) { // 7. Update a todolist item to finished
        jobs.get(uuid).changeStatus();
    }
    public void deleteJob(UUID uuid) { // 8. Delete a todolist item.
        jobs.remove(uuid);
    }
    public void writeJson() throws IOException {
        FileWriter file = new FileWriter("checklist.json");
        for(Job j : jobs.values()) {
            jsonObject.put("line", j.line.toString());
            jsonObject.put("date", j.date.toString());
            jsonObject.put("uuid", j.uuid.toString());
            jsonObject.put("isFinished", j.isFinished.toString());
            file.write(jsonObject.toJSONString());
            file.write("\n"); //Starts a new line
        }
        file.flush();
    }
    public void readJson() throws IOException, ParseException {
        FileReader reader = new FileReader("checklist.json");
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();

            while (line != null) {
                // Make each line a json object
                System.out.println("Line:" + line);
                JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
                // Get the values of the json object
                LocalDate date = LocalDate.parse((String)jsonObject.get("date"), DateTimeFormatter.ISO_LOCAL_DATE);
                String lines = (String) jsonObject.get("line");
                UUID uuid = UUID.fromString((String)jsonObject.get("uuid"));
                Boolean isFinished = Boolean.parseBoolean((String) jsonObject.get("isFinished"));

                // Save those values in a job object
                Job job = new Job(date, lines, uuid, isFinished);

                // Save the job to the checklist
                jobs.put(uuid,job);

                line = br.readLine();
            }
    }
}