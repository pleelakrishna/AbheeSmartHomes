package com.charvikent.abheeSmartHomeSystems.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.charvikent.abheeSmartHomeSystems.config.FilesStuff;
import com.charvikent.abheeSmartHomeSystems.dao.SalesRequestDao;
import com.charvikent.abheeSmartHomeSystems.model.SalesRequest;

@Controller
public class SalesRequestController 
{
	@Autowired
	SalesRequestDao srequestDao;
	@Autowired
	FilesStuff fileTemplate;
	@RequestMapping(value = "/salesRequest" ,method = RequestMethod.GET)
	public String saveRequest(Model model)
	{
		model.addAttribute("srequestf", new SalesRequest());
		return "salesrequest";
		
	}
	@RequestMapping(value = "/salesRequest", method = RequestMethod.POST)
	public String saveRequestDetails(@Valid @ModelAttribute SalesRequest salesrequest,@RequestParam("imgfile") MultipartFile[] uploadedFiles) throws IllegalStateException, IOException
	{
	
		int filecount =0;
   	 
   	 for(MultipartFile multipartFile : uploadedFiles) {
				String fileName = multipartFile.getOriginalFilename();
				if(!multipartFile.isEmpty())
				{
					filecount++;
				 multipartFile.transferTo(fileTemplate.moveFileTodir(fileName));
				}
			}
   	 
   	 if(filecount>0)
   	 {
   		 salesrequest.setImgfiles(fileTemplate.concurrentFileNames());
   		 fileTemplate.clearFiles();
   		 
   	 }
	   	Boolean result =srequestDao.checkSalesrequestExistsorNotByEmailAndModelNo(salesrequest);
	   	if(result==false)
			srequestDao.saveRequest(salesrequest);
	   	else
	   		System.out.println("Record Already exists");
		return "redirect:salesRequest";
		
	}
	@RequestMapping("/allsalesrequest")
	public String  totalSalesList( @ModelAttribute("salesrequestf")  SalesRequest salesrequest,Model model ,HttpServletRequest request) 
	{
		List<SalesRequest> listOrderBeans = null;
		ObjectMapper objectMapper = null;
		String sJson = null;
		model.addAttribute("salesrequestf", new SalesRequest());
		
		try {
			listOrderBeans = srequestDao.getSalesRequestList();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "allsalesrequest";
	}
	
	
}
