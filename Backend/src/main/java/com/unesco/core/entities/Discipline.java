package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //private String index;
    private String name;
    private Date datecreate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    /*public String getIndex() { return index; }
    public void setIndex(String index) { this.index = index; }*/

    public Date getDatecreate() {
        return datecreate;
    }
    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Discipline(){}
    public Discipline(/*String index,*/ String name, Date datecreate)
    {
        //this.index = index;
        this.name = name;
        this.datecreate = datecreate;
    }
}
