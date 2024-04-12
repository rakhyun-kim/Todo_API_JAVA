package com.todo.Todo;

import com.todo.Todo.Model.CheckList;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(TodoApplication.class, args);
	}

}
