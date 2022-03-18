package org.reservoir.fragment.secure_core_ui.controller.kite_view;

import org.reservoir.fragment.secure_core_ui.model.navigation_bar.KiteViewNavigationBar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class KiteViewController {
    @RequestMapping("kite_view")
    private String display(Model model) {
        model.addAttribute("nav_bar",
                           new KiteViewNavigationBar());
        return "kite_view";
    }
}
