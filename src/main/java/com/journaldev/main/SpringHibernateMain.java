package com.journaldev.main;


import com.journaldev.config.SessionConfig;
import com.journaldev.dao.NodeDAO;
import com.journaldev.dao.NodeDAOImpl;
import com.journaldev.model.Project;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.journaldev.model.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author oleksii
 * test - prendiamo "x" dei progectti e
 * estrarriamo tutti i nodi di questi progetti, dopo di che facciamo swap delle tipologie
 * firstly check out and set up hibernate.cfg.xml file
 * after that you may want to create tables locally (check database.sql file inside resources). If not, you should
 * configure pojos's (Node and Project classes) to match your rows.
 */

public class SpringHibernateMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring4.xml");
		NodeDAOImpl nodeDAO = context.getBean("nodeDAOImpl", NodeDAOImpl.class);
		Scanner scanner = new Scanner(System.in);
		nodeDAO.list();
		System.out.println("Swap? (y/n)");
		String answer = scanner.nextLine();
		if(answer.equals("y") || answer.equals("yes")) {
			System.out.println("=============SWAPING============");
			nodeDAO.swap();
		} else {
			System.out.println("Bye!");
		}
		scanner.close();
		nodeDAO.closeSession();
		context.close();
	}

}
