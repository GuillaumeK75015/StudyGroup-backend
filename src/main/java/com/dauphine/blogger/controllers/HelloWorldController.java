package com.dauphine.blogger.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
		name = "Hello world API",
		description = "My first hello world endpoints"
)
public class HelloWorldController
{
	
	@GetMapping("hello-world")
	public String helloWorld()
	{
		return "Hello world";
	}
	
	@GetMapping("hello/{name}")
	@Operation
	public String helloByName(
			@Parameter(description = "Name to greet")
			@PathVariable String name)
	{
		return "Hello " + name;
	}
	
}
