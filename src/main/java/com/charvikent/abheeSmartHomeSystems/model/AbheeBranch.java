package com.charvikent.abheeSmartHomeSystems.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "abheeBranch")
public class AbheeBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer  id;
	
	private String name;
	
	private String description;
	
	private String status;
	
	private String branchhead;
	
	@Transient
	private String branchheadname;
	
	@CreationTimestamp
	protected Date createdTime ;

	@UpdateTimestamp
	protected Date updatedTime ;
	
	

	public Date getCreatedTime() 
	{
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) 
	{
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() 
	{
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) 
	{
		this.updatedTime = updatedTime;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
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

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String getBranchhead() 
	{
		return branchhead;
	}

	public void setBranchhead(String branchhead) 
	{
		this.branchhead = branchhead;
	}

	
	
	public String getBranchheadname() {
		return branchheadname;
	}

	public void setBranchheadname(String branchheadname) {
		this.branchheadname = branchheadname;
	}

	@Override
	public String toString() 
	{
		return "AbheeBranch [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", branchhead=" + branchhead + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + "]";
	}
}
