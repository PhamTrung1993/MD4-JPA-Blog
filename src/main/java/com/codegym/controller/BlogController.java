package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogServices;

    @GetMapping("showList")
    public ModelAndView showList(){
        List<Blog> blogList = blogServices.findAll();
        return new ModelAndView("/list","list",blogList);
    }
    @GetMapping("createNewBlog")
    public ModelAndView showCreateForm(Model model){
        return new ModelAndView("/create","blog",new Blog());
    }

    @PostMapping("createNewBlog")
    public String createBlog(@ModelAttribute Blog blog){
        blogServices.save(blog);
        return "redirect:/showList";
    }
    @GetMapping("editBlog/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Blog blog = blogServices.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Blog blog){
        blogServices.save(blog);
        return "redirect:/showList";
    }
    @GetMapping("deleteBlog/{id}")
    public String deleteById(@PathVariable long id){
        blogServices.remove(id);
        return "redirect:/showList";
    }

}
