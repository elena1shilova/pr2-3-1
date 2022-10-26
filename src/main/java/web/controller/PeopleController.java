package web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.Person;
import web.service.PersonService;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person, //@Valid
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.show(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, // @Valid
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
