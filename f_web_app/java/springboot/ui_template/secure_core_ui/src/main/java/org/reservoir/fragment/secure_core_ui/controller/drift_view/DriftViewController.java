package org.reservoir.fragment.secure_core_ui.controller.drift_view;

import org.reservoir.fragment.secure_core_ui.model.drawer.DriftViewDrawer;
import org.reservoir.fragment.secure_core_ui.model.navigation_bar.KiteViewNavigationBar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class DriftViewController {
    @RequestMapping("drift_view")
    private String display(Model model) {
        model.addAttribute("drawer",
                new DriftViewDrawer());
        return "drift_view";
    }
}
