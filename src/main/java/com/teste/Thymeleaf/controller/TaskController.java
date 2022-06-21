package com.teste.Thymeleaf.controller;

import com.teste.Thymeleaf.model.Task;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/cadastro")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("task", new Task());
        return mv;
    }

    @PostMapping("/cadastro")
    public String cadastro(Task task){

        if(task.getId() != null){
            Task taskFind = tasks.stream().filter(taskItem -> task.getId().equals(taskItem.getId())).findFirst().get();
            tasks.set(tasks.indexOf(taskFind), task);
        }else{
            Long id = tasks.size() + 1L;
            tasks.add(new Task(id, task.getName(), task.getLastName(), task.getEmail(), task.getPassword(), task.getAddress(),
                    task.getAddress2(), task.getCity(), task.getState(), task.getZipcode()));
        }

        return "redirect:/pessoas";
    }

    @GetMapping("/pessoas")
    public ModelAndView pessoas(){
        ModelAndView mv = new ModelAndView("pessoas");
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("cadastro");
        Task taskFind = tasks.stream().filter(task -> id.equals(task.getId())).findFirst().get();
        mv.addObject("task", taskFind);
        return mv;
    }
}
