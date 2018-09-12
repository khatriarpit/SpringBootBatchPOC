package com.test.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Arpit Khatri on 9/7/2018.
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        StringBuilder stringJobStatus = new StringBuilder();
        stringJobStatus.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        stringJobStatus.append("stringJobStatus for " + jobExecution.getJobInstance().getJobName() + " \n");
        stringJobStatus.append("  Started     : " + jobExecution.getStartTime() + "\n");
        stringJobStatus.append("  Finished    : " + jobExecution.getEndTime() + "\n");
        stringJobStatus.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
        stringJobStatus.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
        stringJobStatus.append("  Status      : " + jobExecution.getStatus() + "\n");
        stringJobStatus.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        stringJobStatus.append("Job-Parameter: \n");
        JobParameters jp = jobExecution.getJobParameters();
        for (Iterator<Map.Entry<String,JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String,JobParameter> entry = iter.next();
            stringJobStatus.append("  "+entry.getKey()+"="+entry.getValue()+"\n");
        }
        stringJobStatus.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

        stringJobStatus.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            stringJobStatus.append("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
            stringJobStatus.append("Step " + stepExecution.getStepName() + " \n");
            stringJobStatus.append("WriteCount: " + stepExecution.getWriteCount() + "\n");
            stringJobStatus.append("Commits: " + stepExecution.getCommitCount() + "\n");
            stringJobStatus.append("SkipCount: " + stepExecution.getSkipCount() + "\n");
            stringJobStatus.append("Rollbacks: " + stepExecution.getRollbackCount() + "\n");
            stringJobStatus.append("Filter: " + stepExecution.getFilterCount() + "\n");
            stringJobStatus.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
        }
        log.info(stringJobStatus.toString());
    }
}