package vn.hcmus.fit.mycontact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.hcmus.fit.mycontact.entity.Contact;
import vn.hcmus.fit.mycontact.service.ContactServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping("/contact")
    public String list(Model model){
        model.addAttribute("contacts",contactService.findAll());
        return "list";
    }

    @GetMapping("contact/search")
    public String search(@RequestParam("term") String term, Model model){
        if(StringUtils.isEmpty(term)) {
//            Nếu không có strSearch thì show ra list đang có
            return "redirect:/contact";
        }
        List<Contact> list = contactService.search(term);
        model.addAttribute("contacts",list);

        return "list";
    }

    @GetMapping("/contact/add")
    public String add(Model model){
        model.addAttribute("contact",new Contact());
        return "form";
    }

    @PostMapping("/contact/save")
    public String  save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors())
            return "form";
        contactService.save(contact);
        redirect.addFlashAttribute("successMessage","Saved contact successfully!");
        return "redirect:/contact";
    }

    @GetMapping("/contact/{id}/edit")
    public  String edit(@PathVariable("id") Long id,Model model){
//        Tương tụ như thêm mới chỉ khác là không new Contact mà tìm 1 Contact đã tồn tại để update
        model.addAttribute("contact",contactService.findById(id));
        return "form";
    }

    @GetMapping("/contact/{id}/delete")
    public  String delete(@PathVariable("id") Long id,RedirectAttributes redirect){
        contactService.delete(id);
        redirect.addFlashAttribute("successMessage","Deleted contact successfully!");
        return "redirect:/contact";
    }
}
