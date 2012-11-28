/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generic.domain;

/**
 *
 * @author vincent
 */
public class Process
{
    private Long id;
    private String name;
    
    public Process(Long id, String name)
    {
        setId(id);
        setName(name);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
}
