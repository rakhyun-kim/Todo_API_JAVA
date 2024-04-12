package com.todo.Todo.Controller;

import com.todo.Todo.Model.CheckList;
import com.todo.Todo.Model.Job;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class CheckListController {
    @Autowired
    private CheckList checkList;
    @GetMapping("jobs")
    public List<Job> getAllJobs() {
       return checkList.getAllJobs();
   }
   @GetMapping("finishedjobs")
   public List<Job> getFinishedJob() {
        return checkList.getFinishedJob();
   }
   @GetMapping("unfinishedjobs")
   public List<Job> getUnfinishedJob() {
        return checkList.getUnfinishedJob();
   }
   @GetMapping("date/{date}") // Writing dates
   public List<Job> getJobsWithDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return checkList.getJobsWithDate(date);
   }
   @PutMapping("change/{uuid}")
   public void getChangeJobsDone(@PathVariable String uuid) throws IOException {
       checkList.changeJobStatusDone(UUID.fromString(uuid));
       checkList.writeJson();
   }

   @DeleteMapping("delete/{uuid}")
   public void deleteJob(@PathVariable String uuid) throws IOException {
        checkList.deleteJob(UUID.fromString(uuid));
       checkList.writeJson();
   }
   @PostMapping("add")
    public void addJob(@RequestBody Job job) throws IOException {
        checkList.addJob(job);
        checkList.writeJson();
   }
}
