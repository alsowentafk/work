package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.ui.Model;


import task1.exception.NotFoundException;

@RestController
@RequestMapping("first")
public class FirstController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    public List <User> obj_list = new ArrayList<User>() {{
    	add(new User(1,"First"));
    	add(new User(2,"Second"));
    	add(new User(3,"Three"));
    }};

    /*@RequestMapping("/greeting")
    public User greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new User(counter.incrementAndGet(),
                            String.format(template, name));
    }*/
       
    @RequestMapping("/collection")
    public List <User> getList()
    {
    	return obj_list;
    }
    
    @GetMapping("{id}")
    public User getUserbyID(@PathVariable long id)
    {
    	return obj_list.stream().filter(obj_list -> obj_list.getId() == id).findFirst().orElseThrow(NotFoundException::new);	
    }
    	
    
    
}