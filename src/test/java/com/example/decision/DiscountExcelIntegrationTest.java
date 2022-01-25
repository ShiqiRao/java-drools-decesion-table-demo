/**
 * Copyright (C) 2022 Urban Compass, Inc.
 */
package com.example.decision;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import static org.junit.Assert.assertEquals;

/**
 * @author shiqi.rao
 */
public class DiscountExcelIntegrationTest {

  private KieSession kSession;

  @Before
  public void setup() {
    Resource dt
        = ResourceFactory
        .newClassPathResource("com/example/decisiontable/Discount.xls",
            getClass());

    KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
    KieSession session = kc.newKieSession("DetectionKS");
    kSession = new DroolsBeanFactory().getKieSession(dt);
  }

  @Test
  public void
  giveIndvidualLongStanding_whenFireRule_thenCorrectDiscount()
      throws Exception {
    Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 15);
  }

  @Test
  public void
  giveIndvidualRecent_whenFireRule_thenCorrectDiscount()
      throws Exception {
    Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 1);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 5);
  }

  @Test
  public void
  giveBusinessAny_whenFireRule_thenCorrectDiscount()
      throws Exception {
    Customer customer = new Customer(Customer.CustomerType.BUSINESS, 0);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 20);
  }
}
