package task1;

public class User {

    private  long id;
    private  String content;

    public User()
    {}
    
    public User(long id, String content) {
        this.id = id;
        this.content = content;
    }
    
    public void setId(long ID) 
    {
    	this.id = ID;
    }
    
    public void setContent(String Content)
    {
    	this.content = Content;
    }

    public long getId() {
       
    	return id;
    }

    public String getContent() {
        
    	return content;
    }
}
