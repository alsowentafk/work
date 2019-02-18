package task1;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;


import task1.exception.NotFoundException;

@RestController
@RequestMapping("first")
public class FirstController {
	@Autowired
	
	private UserRepository userRepository;
	
   private int counter = 4;   
    public List <User> obj_list = new ArrayList<User>() {{
    	add(new User(1,"First"));
    	add(new User(2,"Second"));
    	add(new User(3,"Three"));
    }};

          
    @RequestMapping("/collection")
    public List <User> getList()
    {
    	return obj_list;
    }
    
    @RequestMapping("/first")
    public String greeting()
    {
    	return "Hello world";
    }
    
    @GetMapping("{id}")
    public User getUserbyID(@PathVariable long id)
    {
    	return getUser(id);	
    }
    
    public User getUser(@PathVariable long id)
    {
    	return obj_list.stream().filter(obj_list -> obj_list.getId() == id).findFirst().orElseThrow(NotFoundException::new);	
    }
    
    @PostMapping
    public User addUser(@RequestBody User us)
    {
    	us.setId(counter++);
    	obj_list.add(us);
    	userRepository.save(us);
    	return us;
    }
    
    @PutMapping("{id}")
    public User update(@PathVariable long id, @RequestBody User us)
    {
    	User findMess = getUser(id);
    	if(findMess == null)
    	{    		
            throw new NotFoundException();
    	}
    	
    	if(us == null)
    	{    		
            throw new NotFoundException();
    	}
    	
    	findMess.setId(id);
    	findMess.setContent(us.getContent());
    	return findMess;
    	
    } 
       	
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id)
    {
    	User deleteobj = getUser(id);
    	obj_list.remove(deleteobj);
    }
    
    
    
}