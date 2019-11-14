package com.zzg.batch.config;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import com.zzg.batch.domain.AuthUser;
import com.zzg.batch.processor.CVSItemProcessor;
import com.zzg.batch.processor.listener.CSVJobListener;
import com.zzg.batch.processor.validator.BeanValidator;
/**
 * springboot batch 配置类
 * @author zzg
 *
 */
@Configuration
@EnableBatchProcessing // 开启批处理的支持
public class BatchConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	
	/**
     * ItemReader定义：读取文件数据+entirty映射
     * @return
     */
    @Bean
    public ItemReader<AuthUser> reader(){
        // 使用FlatFileItemReader去读cvs文件，一行即一条数据
        FlatFileItemReader<AuthUser> reader = new FlatFileItemReader<>();
        // 设置文件处在路径
        reader.setResource(new ClassPathResource("user.csv"));
        // entity与csv数据做映射
        reader.setLineMapper(new DefaultLineMapper<AuthUser>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"id", "password"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<AuthUser>() {
                    {
                        setTargetType(AuthUser.class);
                    }
                });
            }
        });
        return reader;
    }
    
    /**
     * 注册ItemProcessor: 处理数据
     * @return
     */
    @Bean
    public ItemProcessor<AuthUser, AuthUser> processor(){
    	CVSItemProcessor cvsItemProcessor = new CVSItemProcessor();
    	cvsItemProcessor.setValidator(new BeanValidator<AuthUser>());
        return cvsItemProcessor;
    }
    
    /**
     * ItemWriter定义：指定datasource，设置批量插入sql语句，写入数据库
     * @param dataSource
     * @return
     */
    @Bean
    public ItemWriter<AuthUser> writer(){
        // 使用jdbcBcatchItemWrite写数据到数据库中
        JdbcBatchItemWriter<AuthUser> writer = new JdbcBatchItemWriter<>();
        // 设置有参数的sql语句
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AuthUser>());
        String sql = "insert into auth_user (id, password)" + " values(:id, :password)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }
    
    /**
     * JobRepository定义：设置数据库，注册Job容器
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository cvsJobRepository() throws Exception{
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDataSource(dataSource);
        return jobRepositoryFactoryBean.getObject();
    }
    
    /**
     * jobLauncher定义：
     * @param dataSource
     * @param transactionManager
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher csvJobLauncher() throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        // 设置jobRepository
        jobLauncher.setJobRepository(cvsJobRepository());
        return jobLauncher;
    }
    
    /**
     * 定义job
     * @param jobs
     * @param step
     * @return
     */
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step){
        return jobs.get("importCsvJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .listener(csvJobListener())
                .build();
    }

    /**
     * 注册job监听器
     * @return
     */
    @Bean
    public CSVJobListener csvJobListener(){
        return new CSVJobListener();
    }
    
    /**
     * step定义：步骤包括ItemReader->ItemProcessor->ItemWriter 即读取数据->处理校验数据->写入数据
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */
    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<AuthUser> reader,
                     ItemWriter<AuthUser> writer, ItemProcessor<AuthUser, AuthUser> processor){
        return stepBuilderFactory
                .get("step")
                .<AuthUser, AuthUser>chunk(65000) // Chunk的机制(即每次读取一条数据，再处理一条数据，累积到一定数量后再一次性交给writer进行写入操作)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
