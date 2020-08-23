package com.journaldev.config;

import com.journaldev.model.Node;
import com.journaldev.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionConfig {
  @Autowired
  private Configuration config;

  public Session getSession() {
    config = config.configure("hibernate.cfg.xml").addAnnotatedClass(Project.class).addAnnotatedClass(Node.class);
    SessionFactory sf = config.buildSessionFactory();
    return sf.openSession();
  }

}
