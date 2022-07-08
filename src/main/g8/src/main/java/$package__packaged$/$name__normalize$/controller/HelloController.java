package $package$.$name;format="normalize"$.controller;

import $package$.$name;format="normalize"$.core.model.Resp;
import $package$.$name;format="normalize"$.core.annotation.PassAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @PassAuth
    @GetMapping
    public Resp<?> hello(){
        return Resp.succeed("server is ready 🚀 🚀 🚀");
    }

}