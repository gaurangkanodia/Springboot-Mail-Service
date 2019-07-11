package com.sap.mailservice.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController 
{
	@Autowired
	MailServiceInterface msi;
	
	@PostMapping("/mail")
	public void mailService(@RequestBody MailMessage mm)
	{
		msi.mailMethod(mm);
	}

}
