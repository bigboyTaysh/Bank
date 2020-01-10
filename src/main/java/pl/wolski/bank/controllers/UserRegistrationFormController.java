package pl.wolski.bank.controllers;

import lombok.extern.log4j.Log4j2;
import pl.wolski.bank.models.AccountType;
import pl.wolski.bank.models.Address;
import pl.wolski.bank.models.User;
import pl.wolski.bank.services.AccountTypeService;
import pl.wolski.bank.services.AddressService;
import pl.wolski.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
public class UserRegistrationFormController {
    @Autowired(required = false)
    private UserService userService;

    @Autowired(required = false)
    private AddressService addressService;

    @Autowired(required = false)
    private AccountTypeService accountTypeService;


    @GetMapping("/registrationForm.html")
    public String registration(Model model) {
        model.addAttribute("userAddress", new Address());
        model.addAttribute("userCommand", new User());
        return "registrationForm";
    }

    @PostMapping("/registrationForm.html")
    public String registration(@Valid @ModelAttribute("userCommand") User userForm,
                               @Valid @ModelAttribute("userAddress") Address userAddress,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "registrationForm";
        }

        userService.save(userForm, addressService.findExistAddress(userAddress));
        return "registrationSuccess";
    }


    @ModelAttribute("accountTypes")
    public List<AccountType> loadTypes(){
        List<AccountType> types = accountTypeService.getAllTypes();
        log.info("Ładowanie listy "+types.size()+" typów ");
        return types;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //aby użytkownik nie mógł sobie wstrzyknąć aktywacji konta oraz ról (np., ADMIN)
        //roles są na wszelki wypadek, bo warstwa serwisów i tak ustawia ROLE_USER dla nowego usera
        binder.setDisallowedFields("enabled", "roles");
    }


}