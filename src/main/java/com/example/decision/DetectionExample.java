package com.example.decision;


import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DetectionExample {

  public static void main(String[] args) {
    KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
    execute(kc);
  }

  public static void execute(KieContainer kc) {
    KieSession session = kc.newKieSession("DetectionKS");

    //now create some test data
    AnalysisResult analysisResult = new AnalysisResult();
    analysisResult.setSignature(new Double[]{0.0});
    analysisResult.setDate(new Double[]{0.0});

    ValidationResult validationResult = new ValidationResult();

    session.insert(analysisResult);
    session.insert(validationResult);

    session.fireAllRules();

    session.dispose();

    if (validationResult.getTriggerHumanLoop()){
      System.out.println("HumanReviewLoop");
    }
  }
}
