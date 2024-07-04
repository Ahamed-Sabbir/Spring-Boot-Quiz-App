package com.sabbir.service;

import com.sabbir.model.Result;
import com.sabbir.model.Tasks;
import com.sabbir.repository.ResultRepository;
import com.sabbir.repository.TasksRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    private final TasksRepository taskRepo;
    private final ResultRepository resultRepo;
    public QuizService(TasksRepository taskRepo, ResultRepository resultRepo) {
        this.taskRepo = taskRepo;
        this.resultRepo = resultRepo;
    }
    public List<Tasks> getTasks() {
        List<Tasks> allTasks = taskRepo.findAll();
        List<Tasks> tasksList = new ArrayList<>();

        Random random = new Random();
        for(int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(allTasks.size());
            tasksList.add(allTasks.get(randomNumber));
            allTasks.remove(randomNumber);
        }
        return tasksList;
    }

    public int getResult(List<Tasks> tasksList){
        int correct = 0;
        for(Tasks task : tasksList) {
            if(task.getAns() == task.getChosen()){
                correct++;
            }
        }
        return correct;
    }

    public void saveResult(Result result){
        resultRepo.save(result);
    }

    public List<Result> getTopScore(){
        return resultRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
}
