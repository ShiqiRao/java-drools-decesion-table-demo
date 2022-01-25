/**
 * Copyright (C) 2022 Urban Compass, Inc.
 */
package com.example.decision;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

/**
 * @author shiqi.rao
 */
public class DecisionTableModule {

  private KieServices kieServices = KieServices.Factory.get();

  public KieSession provideKieSession() {
    KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
    return kc.newKieSession("DetectionKS");
  }

  private void getKieRepository() {
    final KieRepository kieRepository = kieServices.getRepository();
    kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
  }

  private KieFileSystem getKieFileSystem() throws IOException {
    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    List<String> rules = Arrays.asList("BackwardChaining.drl", "SuggestApplicant.drl",
        "Product_rules.xls");
    for (String rule : rules) {
      kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
    }
    return kieFileSystem;
  }

  public KieContainer getKieContainer() throws IOException {
    getKieRepository();

    KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
    kb.buildAll();

    KieModule kieModule = kb.getKieModule();
    KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

    return kContainer;
  }
}
