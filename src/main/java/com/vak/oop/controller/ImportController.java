package com.vak.oop.controller;

import com.vak.oop.model.User;
import com.vak.oop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping
    public String save(@ModelAttribute User user, Model model) {
        boolean success = userService.save(user);
        if (!success) {
            model.addAttribute("error", "Username or Email already exists or save failed.");
            return "user/form";
        }
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable UUID id, Model model) {
        userService.findById(id).ifPresentOrElse(
                u -> model.addAttribute("user", u),
                () -> model.addAttribute("error", "User not found.")
        );
        return "user/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable UUID id, @ModelAttribute User user, Model model) {
        boolean success = userService.update(id, user);
        if (!success) {
            model.addAttribute("error", "Failed to update user.");
            return "user/form";
        }
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id, Model model) {
        boolean success = userService.delete(id);
        if (!success) {
            model.addAttribute("error", "Failed to delete user.");
        }
        return "redirect:/users";
    }
}
