package com.rainbow.um.common;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecuteTasklet implements Tasklet{
	
	private Map<Object, String> params;

	public Map<Object, String> getParams() {
		return params;
	}
	
	public void setParams(Map<Object, String> params) {
		this.params = params;
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		Map<?, ?> jobParameters = chunkContext.getStepContext().getJobParameters();
		params.clear();
		for(Map.Entry<?, ?> entry : jobParameters.entrySet()) {
			params.put(entry.getKey(),entry.getValue().toString());
			System.out.println("params Key"+entry.getKey());
			System.out.println("params Value"+entry.getValue());
		}
		System.err.println("params 는 ? "+params);
		return RepeatStatus.FINISHED;
	}
	
}
