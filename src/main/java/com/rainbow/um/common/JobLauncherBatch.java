package com.rainbow.um.common;
import java.util.HashMap;


import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobLauncherBatch{
	
	private final String KEY = "params에 넣은 Key값";
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private HashMap params;
	
	@RequestMapping(value="/batch.do",method=RequestMethod.GET)
	public String launchJob(Model model) {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addString(KEY,"배치실행해서 넣은 Value값");
		try {
			jobLauncher.run(jobRegistry.getJob("executeTasklet"), builder.toJobParameters());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("배치 페이지 이동할때 가져가는 params : "+params);
		model.addAttribute("params", params);
		return "batch";
	}
	

}


