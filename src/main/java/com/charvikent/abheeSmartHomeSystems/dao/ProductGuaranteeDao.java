package com.charvikent.abheeSmartHomeSystems.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.Product;
import com.charvikent.abheeSmartHomeSystems.model.ProductGuarantee;



@Repository
@Transactional
public class ProductGuaranteeDao 
{
	@PersistenceContext
    private EntityManager em;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired CustomerDao customerDao;
	@Autowired  ProductDao  productDao;
	
	public void saveWarranty(ProductGuarantee productGuarantee ) 
	{
		//em.persist(productGuarantee);
		em.merge(productGuarantee);
	

	}
	public Map<String, String> getCustomersMap()
	{
		Map<String, String> rolesMap = new LinkedHashMap<String, String>();
		try
		{
		List<Customer> customersList=  customerDao.getAbheeCustomerNames();
		for(Customer bean: customersList){
			
			if(bean.isPurchaseCustomer()) {
			rolesMap.put(bean.getCustomerId(),bean.getCustomerId());
			}
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
	public Map<Integer, String> getProductsMap()
	{
		Map<Integer, String> rolesMap = new LinkedHashMap<Integer, String>();
		try
		{
		List<Product> productsList=  productDao.getProductNames(); 
		for(Product bean: productsList){
			rolesMap.put(bean.getId(), bean.getName());
		}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		return rolesMap;
				
		
	}
	public void updateWarranty(ProductGuarantee productGuarantee) 
	{
		ProductGuarantee pg=getProductWarrantyByOrderId(productGuarantee.getOrderId());
		/*pg.setCustomerid(productGuarantee.getCustomerid());
		pg.setProductmodelid(productGuarantee.getProductmodelid());*/
		pg.setPurchaseddate(productGuarantee.getPurchaseddate());
		pg.setExpireddate(productGuarantee.getExpireddate());
		em.flush();
		
	}
	/*public void updateProductWarranty(ProductGuarantee productGuarantee) 
	{
		String sql="update abheeproductguarantee abg set abg.purchaseddate='"+productGuarantee.getPurchaseddate()+"'abg.expireddate='"+productGuarantee.getExpireddate()+"'";
		jdbcTemplate.execute(sql);
	}*/
	public ProductGuarantee getProductWarrantyByOrderId(String orderId) 
	{
		return em.find(ProductGuarantee.class, orderId);	
	}
	
	public String productid(String productname) 
	{
		String hql ="select p.id from Product p where p.name='"+productname+"'";
		Integer otpStr =(Integer) em.createQuery(hql).getResultList().get(0);
		return String.valueOf(otpStr);
		
	}
	
	/*public String customerid(String customerid) 
	{
		String hql ="select p.id from Product p where p.name='"+productname+"'";
		String otpStr =	(String) em.createQuery(hql).getResultList().get(0);
		return otpStr;
		
	}*/
	
	/*@SuppressWarnings("unchecked")
	public  List<ProductGuarantee> getProductWarrantyDetails() 
	{
		List<ProductGuarantee> listProducts =new ArrayList<ProductGuarantee>();
		String hql="from ProductGuarantee";
		List<Object[]> rows = em.createQuery(hql).getResultList();
		for (Object[] row : rows) 
		{
			ProductGuarantee pw=new ProductGuarantee();
					
			pw.setId(Integer.parseInt(String.valueOf(row[0])));
			pw.setCustomerid((String) row[1]);
			pw.setProductmodelid((String) row[2]);
			pw.setPurchaseddate((String) row[3]);
			pw.setExpireddate((String) row[4]);
					
			listProducts.add(pw);
		
		
			}

		return listProducts;
		
	}*/
	@SuppressWarnings("unchecked")
	public ProductGuarantee getProductWarrantyDetailsByObject(ProductGuarantee productGuarantee) 
	{
		String hql ="from ProductGuarantee where productmodelid ='"+ productGuarantee.getProductmodelid()+"' and customerid ='"+productGuarantee.getCustomerid()/*"'and purchaseddate='"+productGuarantee.getPurchaseddate()*/+"'";
		List<ProductGuarantee> pwd= em.createQuery(hql).getResultList();
		if(pwd.size() > 0)
			return pwd.get(0);
		return null;
	}
	
	
	public List<Map<String, Object>> getProductWarrantyList()
	 {
			
		//return em.createQuery("  abg.id,abg.customerid,abg.expireddate,abg.expireddate,p.name as productmodelname from ProductGuarantee abg,Product p where abg.productmodelid=p.id and status='1'").getResultList();
			String sql="select abg.order_id as orderId,abg.customerid,abg.productmodelid,abg.purchaseddate,abg.expireddate,p.name as productmodelname,abg.status from abheeproductguarantee abg,abhee_product p where abg.productmodelid=p.id and abg.status='1'  order by abg.updated_time desc";
			System.out.println(sql);
			
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
			System.out.println(retlist);
			return retlist;
		
	 }
	
	public List<Map<String, Object>> getWarrantyList()
	 {
			
		//return em.createQuery("  abg.id,abg.customerid,abg.expireddate,abg.expireddate,p.name as productmodelname from ProductGuarantee abg,Product p where abg.productmodelid=p.id and status='1'").getResultList();
			String sql="select abg.order_id as orderId,abg.customerid,abg.productmodelid,abg.purchaseddate, abg.expireddate,p.name as productmodelname,abg.status from abheeproductguarantee abg,abhee_product p where abg.productmodelid=p.id and abg.status='1' order by abg.updated_time desc";
			System.out.println(sql);
			
			List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
			System.out.println(retlist);
			return retlist;
		
	 }
	
	public boolean deleteProductWarranty(String id, String status) 
	{
		Boolean delete=false;
		try{
			
			ProductGuarantee pg= (ProductGuarantee)em.find(ProductGuarantee.class ,id);
			   pg.setStatus(status);
			   em.merge(pg);
			if(!status.equals(pg.getStatus()))
			{
				delete=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return delete;
	}

	public List<Map<String, Object>> getAllInActiveList() 
	{
		String sql="select abg.order_id,abg.customerid,abg.productmodelid,abg.purchaseddate,abg.expireddate,p.name as productmodelname,abg.status from abheeproductguarantee abg,abhee_product p where abg.productmodelid=p.id and abg.status='0' order by abg.updated_time desc";
		System.out.println(sql);
		
		List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(sql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	

	public List<Map<String, Object>> getProductWarrantyDetailsByCustomerId(String customerId) 
	{
		String hql ="select * from abheeproductguarantee where customerid='"+customerId+"'";
		System.out.println(hql);
		
		List<Map<String,Object>>  ordersList = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(ordersList);
		return ordersList;
	}
	
	public void deactiveProductWarranty(String status, String id) 
	{
		String sql="update abheeproductguarantee set status='"+status+"'where id='"+id+"'";
		jdbcTemplate.execute(sql);		
	}
	public List<ProductGuarantee> getProductModelNames(ProductGuarantee productGuarantee)
	{
		String sql="select pw.productmodelid,p.name as prodctmodelname from abheeproductguarantee pw,abhee_product p where pw.productmodelid=p.id";
		RowMapper<ProductGuarantee> rowMapper = new BeanPropertyRowMapper<ProductGuarantee>(ProductGuarantee.class);
		return  this.jdbcTemplate.query(sql, rowMapper);	
	}
}
