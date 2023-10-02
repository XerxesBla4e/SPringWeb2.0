package com.xercodes.RESTApitest.controler;

import com.xercodes.RESTApitest.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyBookListController {

    @Autowired
    private MyBookListService myBookListService;

    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        myBookListService.deleteById(id);
        redirectAttributes.addFlashAttribute("message","Deleted Successfully");
        return "redirect:/api/v1/book/my_books";
    }
}
