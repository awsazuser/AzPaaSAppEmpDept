package com.example.demo;

import com.example.demo.Emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.microsoft.azure.storage.queue.*;

@Controller
@Configuration
@EnableCaching
@RestController

public class EmpRepositoryController {
    @Autowired
    private EmpRepository empRepository;
    
    @Autowired
    private CloudStorageAccount storageAccount;
 
    @RequestMapping(method=RequestMethod.GET, value="/emps")
   // @Cacheable("allemp")
    public Iterable<Emp> emp() {
       return empRepository.findAll();
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/emps/{id}")
    //@Cacheable("empsbyid")
    public Emp show(@PathVariable Integer id) {
    	return empRepository.findOne(id);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/deps/{id}")
   // @Cacheable("empsbydeptid")
    public  Iterable<Emp> show1(@PathVariable Integer id) {
    	return empRepository.findByDid(id);
    }
    
  //  @RequestMapping(method=RequestMethod.GET, value="/message/{id}")
  //   public String sendtoq(@PathVariable Integer id) throws ServiceBusException, InterruptedException {
  //       final String messageBody = empRepository.findOne(id).toString();
  //       System.out.println("Sending message to Azure service Bus: " + messageBody);
  //       final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
  //      queueClient.send(message);
  //		return empRepository.findOne(id) + "  --   Message sent to Azure service Bus";
  //  }
    
   @RequestMapping(method=RequestMethod.GET, value="/message/{id}")
       public String sendtoq(@PathVariable Integer id) {
	   
		   final String messageBody = empRepository.findOne(id).toString();
		  System.out.println("Sending message to Azure service Bus: " + messageBody);
	   
	   try
	   {
	       // Retrieve storage account from connection-string.
	  
	       // Create the queue client.
	       CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

	       // Retrieve a reference to a queue.
	       CloudQueue queue = queueClient.getQueueReference("empq");

	       // Create the queue if it doesn't already exist.
	       queue.createIfNotExists();

	       // Create a message and add it to the queue.
	       CloudQueueMessage message = new CloudQueueMessage(messageBody);
	       queue.addMessage(message);
	   }
	   catch (Exception e)
	   {
	       // Output the stack trace.
	       e.printStackTrace();
	   }
	   
	   	   return empRepository.findOne(id).toString() + "  --   Message sent to Azure Storage Account Queue";
         
       }
   
   @RequestMapping(method=RequestMethod.GET, value="/dequeue")
   public String getfromq() {
   
     try
   {
       // Retrieve storage account from connection-string.
  
       // Create the queue client.
       CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

       // Retrieve a reference to a queue.
       CloudQueue queue = queueClient.getQueueReference("empq");

    // Retrieve the first visible message in the queue.
       CloudQueueMessage retrievedMessage = queue.retrieveMessage();
       CloudQueueMessage peekedMessage = queue.peekMessage();
       
       String delmessage = peekedMessage.getMessageContentAsString();
       System.out.println(peekedMessage.getMessageContentAsString());
    		   
       if (retrievedMessage != null)
       {
           // Process the message in less than 30 seconds, and then delete the message.
           queue.deleteMessage(retrievedMessage);
          
          }
   }
   catch (Exception e)
   {
       e.printStackTrace();
   }
   
    return   "Message dequeued Azure Storage Account Queue";
     
   }
    
    @RequestMapping(method=RequestMethod.POST, value="/emps")
    @Cacheable("empspost")
    public String save(@RequestBody Emp emp) {
        empRepository.save(emp);
        return emp.getName();
    }
}