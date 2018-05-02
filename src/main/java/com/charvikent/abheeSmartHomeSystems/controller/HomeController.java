package com.charvikent.abheeSmartHomeSystems.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.charvikent.abheeSmartHomeSystems.dao.CategoryDao;
import com.charvikent.abheeSmartHomeSystems.dao.CustomerDao;
import com.charvikent.abheeSmartHomeSystems.model.Category;
import com.charvikent.abheeSmartHomeSystems.model.Customer;
import com.charvikent.abheeSmartHomeSystems.model.User;
import com.charvikent.abheeSmartHomeSystems.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired UserService userService;
	
	@Autowired CustomerDao customerDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	
 static 	String loginurl=""; 
 
 static boolean falg =true;
	
	
	@RequestMapping("/admin")
	public String customlogin(Model model) {
		
		LOGGER.debug("Calling Admin Login page index::{} at controller");
		
		 /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 if (null != auth){    
		        return "redirect:dashboard";
		    }
		 else*/
		
		return "login";
	}
	
	@RequestMapping("/userlogin")
	public String userLogin(Model model) {
		
		LOGGER.debug("Calling User Login page index::{} at controller");
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		return "userlogin";
	}
	
	
	@RequestMapping("/login")
	public String loginView(Model model) {
		LOGGER.debug("Calling Login page index::{} at controller");
		System.out.println("login called at /login page");
		//User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 //userService.setLoginRecord(objuserBean.getId(),"login");

		return "login";
	}
	
	@RequestMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		
		LOGGER.debug("Calling Logout page index::{} at controller");
		
		User objuserBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    userService.setLoginRecord(objuserBean.getId(),"logout");
	    
	   // userService.setLoginRecord(objuserBean.getId(),"logout");
	    if (null != auth){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    SecurityContextHolder.clearContext();
	    if(null != auth) {
	    	SecurityContextHolder.getContext().setAuthentication(null);
	    }
	    System.out.println("Called Logout");
	    return "redirect:/login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
	
	/*@RequestMapping("/403")
	public String failureLogin(Model model) {
		return "403";
	}*/
	@RequestMapping("/customerlogin")
	public String ShowCustomerLoginPage(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling Customer Login page index::{} at controller");
		if(falg)
		loginurl =request.getHeader("referer");
		
		return "customerlogin";
	}
	
	@PostMapping("/customerlogin")
	public String validateCustomerLogin(Model model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JsonProcessingException {
		
		LOGGER.debug("Validating Customer Login page index::{} at controller");
		String loginid=request.getParameter("username");
		String password=request.getParameter("password");
		
		Customer customer =customerDao.validateCustomer(loginid,password);
		String referalUrl=request.getHeader("referer");
		
		if(null ==customer)
		{
			System.out.println("Customer does not exists"+referalUrl);
			falg=false;
			redir.addFlashAttribute("msg", "Invalid Details");
			redir.addFlashAttribute("cssMsg", "danger");
			return "redirect:customerlogin";
		}
		else if(null==loginurl)
				{
			session.setAttribute("customer", customer);
			session.setAttribute("loggedstatus", "login");
			session.setAttribute("customerId", customer.getCustomerId());
			return "redirect:/";
			
				}
		else
			
		{
		session.setAttribute("customer", customer);
		session.setAttribute("loggedstatus", "login");
		session.setAttribute("customerId", customer.getCustomerId());
			System.out.println("()()()()()()()()()()("+loginurl);
			return "redirect:"+ loginurl;
		}
		
	}
	
	@PostMapping("/test")
	public String test(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling Admin Login page index::{} at controller");
		//URI str= hrequest.getURI();
		
		System.out.println(request.getContextPath());
		
		System.out.println(request.getRequestURL());
		
		System.out.println(request.getHeader("referer"));
		
		//System.out.println(hrequest.getHeaders());
		
		return "admin";
	}
	/*@RequestMapping("/dummypage")
	public String dummy(Model model) {
		return "dummypage";
	}*/
	
	@RequestMapping("/")
	public String ShowAbhee(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException 
	{
		LOGGER.debug("Calling Abhee site Main page at controller");
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		//model.addAttribute("categories", listOrderBeans);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(listOrderBeans);	
		request.setAttribute("allOrders1", sJson);
		
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		
		 
		return "abheeindex";
	}
	
	
	@RequestMapping("/signout")
	public String SignOut(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Signout page at controller");
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		
		falg=true;
		
		session.invalidate();
		 
		return "redirect:"+ referalUrl;
	}
	
	@RequestMapping("/getCategoryList")
	public @ResponseBody String getCategoryList(Model model,HttpServletRequest request,HttpSession session) throws JSONException, JsonProcessingException 
	{
		LOGGER.debug("Retrieving Categories list at controller");
		List<Category> listOrderBeans = categoryDao.getCategoryNames();
		//model.addAttribute("categories", listOrderBeans);
		ObjectMapper objectMapper = new ObjectMapper();
		String sJson = objectMapper.writeValueAsString(listOrderBeans);	
		request.setAttribute("allOrders1", sJson);
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("list", listOrderBeans);
		String referalUrl=request.getHeader("referer");
		System.out.println(referalUrl);
		
		 
		return String.valueOf(jsonObj);
	}
	
	
	@RequestMapping("*")
	public String erro404(Model model,HttpServletRequest request) {
		LOGGER.debug("Calling 404 error page at controller");
		String referalUrl=request.getHeader("referer");
		return "redirect:"+ referalUrl;
	}
	
	
	@RequestMapping("/customerprofile")
	public String customerProfile(@ModelAttribute("customerProfile") Customer customer,Model model,HttpServletRequest request,HttpSession session,RedirectAttributes redir) throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		Customer customerProfile=(Customer) session.getAttribute("customer");
		//String id=String.valueOf(objuserBean.getId());


		model.addAttribute("customerProfile", customerProfile);

		
		/*String customerid=request.getParameter("custId");
		String firstname=request.getParameter("firstname");
		String Lastname=request.getParameter("lastname");
		String mobileno=request.getParameter("mobilenumber");
		String Address=request.getParameter("address");
		Customer customer =customerDao.findCustomerByCustId(customerid);
		String referalUrl=request.getHeader("referer");
		if(null==loginurl)
			{
		session.setAttribute("customer", customer);
		session.setAttribute("loggedstatus", "login");
		//session.setAttribute("customerId", customer.getCustomerId());
		session.setAttribute("firstname", customer.getFirstname());
		session.setAttribute("lastname", customer.getLastname());
		session.setAttribute("mobileno", customer.getMobilenumber());
		session.setAttribute("address",customer.getAddress());
		return "redirect:/";
		}*/
		return "customerprofile";
	}
	
	@RequestMapping(value="/editCustomerProfile", method= RequestMethod.POST )
	public String editProfile(@ModelAttribute("customerProfile") Customer customer,RedirectAttributes redir,HttpServletRequest request){
		
		LOGGER.debug("Calling editCustomerProfile at controller");
		/*String custId=request.getParameter("customerid");
		Customer user=customerDao.findCustomerByCustId(custId);
		user.setFirstname(user.getFirstname());
		user.setLastname(user.getLastname());
		user.setEmail(user.getEmail());
		user.setMobilenumber(user.getMobilenumber());
		user.setAddress(user.getAddress());*/
		customerDao.saveAbheeCustomer(customer);
		redir.addFlashAttribute("msg", "Your Details Updated Successfully");
		redir.addFlashAttribute("cssMsg", "warning");

			return "redirect:customerprofile";

	}
	
	
	@RequestMapping("/about")
	public String about() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "about";
	}
	@RequestMapping("/career")
	public String career() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "career";
	}
	@RequestMapping("/contact")
	public String contact() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "contact";
	}
	@RequestMapping("/gallery")
	public String gallery() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "gallery";
	}
	@RequestMapping("/location")
	public String location() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "location";
	}
	@RequestMapping("/mission")
	public String mission() throws JSONException, JsonProcessingException {
		LOGGER.debug("Calling Customer Profile  page at controller");
		
		 
		return "mission";
	}
	
	

}
