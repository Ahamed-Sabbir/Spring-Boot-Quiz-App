package com.sabbir.controller;

import com.sabbir.model.QuestionForm;
import com.sabbir.model.Result;
import com.sabbir.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuizController {
    private final QuizService quizService;
    private Boolean submitted;
    private Result result;
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
        result = new Result();
    }

    @GetMapping("/")
    public String getHomePage(){
        return "HomePage";
    }

    @PostMapping("/quiz")
    public String quizPage(@RequestParam String username, RedirectAttributes redirectAttributes, Model model){
        if(username.equals("")){
            redirectAttributes.addFlashAttribute("warning", "Enter Username");
            return "redirect:/";
        }
        submitted = false;
        result = new Result();
        result.setUserName(username);

        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(quizService.getTasks());
        model.addAttribute("questionForm", questionForm);
        return "QuizPage";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model model){
        if(!submitted) {
            int score = quizService.getResult(questionForm.getQuestions());
            result.setTotalCorrect(score);
            quizService.saveResult(result);
            submitted = true;
        }
        model.addAttribute("result", result);
        return "ResultPage";
    }

    @GetMapping("/score")
    public String score(Model model) {
        List<Result> result = quizService.getTopScore();
        model.addAttribute("result", result);
        return "ScorePage";
    }
}
