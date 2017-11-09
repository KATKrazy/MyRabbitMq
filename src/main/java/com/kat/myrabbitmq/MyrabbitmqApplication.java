package com.kat.myrabbitmq;

import com.kat.myrabbitmq.Po.MessageInfo;
import com.kat.myrabbitmq.recv.Receiver;
import com.kat.myrabbitmq.send.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
@ComponentScan("com.kat.myrabbitmq")
public class MyrabbitmqApplication {

	@Autowired
	private Receiver receiver;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MyrabbitmqApplication.class, args);
	}
}
