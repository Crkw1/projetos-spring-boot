package com.gerenciador_de_reserva;

import com.gerenciador_de_reserva.model.Reserva;
import com.gerenciador_de_reserva.model.User;
import com.gerenciador_de_reserva.service.ReservaService;
import com.gerenciador_de_reserva.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import com.gerenciador_de_reserva.service.UserDetailsServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpSession;
import java.util.Set;


@Controller
public class PagesController {

    final UserService userService;
    final ReservaService reservaService;

    public PagesController(UserService userService, ReservaService reservaService) {
        this.userService = userService;
        this.reservaService = reservaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/reservas")
    public String reservas(Model model, HttpSession session) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        User user = userService.getUserByUsername(name);

        if (user != null) {
            session.setAttribute("user", user);

            Reserva reserva = new Reserva();
            model.addAttribute("reserva", reserva);

            return "reservas";
        }

        return "index";
    }

    @PostMapping("/reservas-submit")
    public String reservasSubmit(@ModelAttribute Reserva reserva, Model model,
                                     @SessionAttribute("user") User user) {
        assert user != null;
        reserva.setUser(user);
        reservaService.create(reserva);
        Set<Reserva> userReservas = user.getReservas();
        userReservas.add(reserva);
        user.setReservas(userReservas);
        userService.update(user.getId(), user);
        return "redirect:/reservas";
    }
}
