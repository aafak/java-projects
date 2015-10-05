package com.cloud.pool;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cb_pool")
public class PoolVO implements Pool{
@Id	
int id;
String name;
@Override
public int getId() {
	return id;
}
@Override
public String getName() {
	return name;
}
@Override
public void setId(int id) {
this.id=id;	
}
@Override
public void setName(String name) {
this.name=name;	
}

}
