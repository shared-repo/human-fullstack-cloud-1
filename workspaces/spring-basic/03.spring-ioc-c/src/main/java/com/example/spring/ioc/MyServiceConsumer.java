package com.example.spring.ioc;

public class MyServiceConsumer implements ServiceConsumer {

	private MessageService messageService;
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	private TimeService timeService;
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

	///////////////
	public void doSomething() {
		String message = messageService.getMessage();
		System.out.println(message);
		message = timeService.getTimeString();
		System.out.println(message);
	}

}
