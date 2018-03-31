package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;

@Repository
@Transactional
public class SalesRequestDao 
{
	@PersistenceContext
    private EntityManager entityManager;
	
	public void saveRequest(SalesRequest salesrequest) 
	{
		entityManager.persist(salesrequest);

	}
	@SuppressWarnings("unchecked")
	public  Boolean checkSalesrequestExistsorNotByEmailAndModelNo(SalesRequest salesrequest)
	{
		String hql="from SalesRequest where modelnumber='"+salesrequest.getModelnumber()+"'"+ "and "+"email='"+salesrequest.getEmail()+"'";
		List<SalesRequest> list=entityManager.createQuery(hql).getResultList();
		if(list.size()>0)
			return true;
		else
			return false;	
	}
	/*@SuppressWarnings("unchecked")
	public List<SalesRequest> getModelNumbers()
	 {

		return entityManager.createQuery("  from SalesRequest where  ").getResultList();

	 }
	public SalesRequest getModelNumberById() 
	{
			
			@SuppressWarnings("unchecked")
			List<SalesRequest> reqList =(List<SalesRequest>) entityManager.createQuery("  FROM SalesRequest where ").setParameter("",).getResultList();
			if(reqList.size() > 0)
				return reqList.get(0);
			return null;
			
	}*/
	 @SuppressWarnings("unchecked")
		public List<SalesRequest> getSalesRequestList()
		 {
			 String hql ="from SalesRequest";
			 
			
			 List<SalesRequest> reqlist= entityManager.createQuery(hql).getResultList();
			 for(SalesRequest sr:reqlist)
			 {
			 System.out.println(sr.getModelnumber()+" "+sr.getEmail());
			 }
			return reqlist;		 
		 }
}