package com.vak.oop.controller;

import com.vak.oop.model.User;
import com.vak.oop.service.UserService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public String list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "username") String field, @RequestParam(defaultValue = "asc") String direction, Model model) {
    try {
      Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
      Pageable pageable = PageRequest.of(page, size, sort);
      Page<User> userPage = keyword.isBlank() ? service.findByRole("user", pageable) : service.searchByUsernameAndRole("user", keyword, pageable);
      model.addAttribute("users", userPage.getContent());
      model.addAttribute("currentPage", page);
      model.addAttribute("totalPages", userPage.getTotalPages());
      model.addAttribute("keyword", keyword);
      model.addAttribute("field", field);
      model.addAttribute("direction", direction);
      model.addAttribute("reverseSortDir", direction.equals("asc") ? "desc" : "asc");
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
    }
    model.addAttribute("view", "user/list");
    return "index";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    try {
      model.addAttribute("user", new User());
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
    }
    model.addAttribute("view", "user/form");
    return "index";
  }

  @PostMapping
  public String save(@ModelAttribute("user") User user, Model model) {
    try {
      boolean isNew = (user.getUserId() == null);
      boolean usernameExisted = service.existsByUsername(user.getUsername());
      if (isNew && usernameExisted) {
        model.addAttribute("error", "Already Existed!");
        model.addAttribute("user", user);
        model.addAttribute("view", "user/form");
        return "index";
      }
      if (!isNew) {
        User existing = service.findById(user.getUserId()).orElseThrow();
        if (!existing.getUsername().equalsIgnoreCase(user.getUsername()) && usernameExisted) {
          model.addAttribute("error", "Already Existed");
          model.addAttribute("user", user);
          model.addAttribute("view", "user/form");
          return "index";
        }
      }
      service.save(user);
      return "redirect:/users";
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("user", user);
      model.addAttribute("view", "user/form");
      return "index";
    }
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable UUID id, Model model) {
    try {
      model.addAttribute("user", service.findById(id).orElseThrow());
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
    }
    model.addAttribute("view", "user/form");
    return "index";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable UUID id, Model model) {
    try {
      service.deleteById(id);
      return "redirect:/users";
    } catch (Exception e) {
      model.addAttribute("error", "Sth Went Wrong!");
      model.addAttribute("view", "user/list");
      return "index";
    }
  }
}