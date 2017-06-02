package demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheerController {
    @RequestMapping("/cheer")
    public String cheer(Model model, @RequestParam(value="name", required=false, defaultValue="everyone") String name) {
        model.addAttribute("name", name);
        return "cheer";
    }
}
