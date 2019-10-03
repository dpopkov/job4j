package ru.job4j.vacparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.util.AppSettings;

/**
 * Application scheduler that starts processing according to the settings.
 */
public class AppScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(AppScheduler.class);
    private static final String JOB_NAME = "vacparserJob";
    private static final String TRIGGER_NAME = "vacparserTrigger";
    private static final String GROUP = "vacparserGroup";

    private final Class<? extends Job> jobClass;
    private final AppSettings appSettings;

    /**
     * Initializes scheduler with job and application settings.
     * @param jobClass class for the job that will be started
     * @param appSettings application settings used by the job
     */
    public AppScheduler(Class<? extends Job> jobClass, AppSettings appSettings) {
        this.jobClass = jobClass;
        this.appSettings = appSettings;
    }

    /**
     * Starts processing.
     * @throws SchedulerException if error within the Quartz scheduler
     */
    public void start() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler quartzScheduler = schedulerFactory.getScheduler();
        quartzScheduler.start();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("appSettings", appSettings);
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(JOB_NAME, GROUP)
                .usingJobData(dataMap)
                .build();
        String cronExpression = appSettings.cronTime();
        LOG.info("Using cron expression: {}", cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_NAME, GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .forJob(JOB_NAME, GROUP)
                .build();
        quartzScheduler.scheduleJob(jobDetail, trigger);
    }
}
