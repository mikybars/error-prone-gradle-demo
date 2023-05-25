package com.github.almibarss.demo.errorprone.refaster;

import com.google.errorprone.refaster.annotation.AfterTemplate;
import com.google.errorprone.refaster.annotation.BeforeTemplate;
import tech.picnic.errorprone.refaster.annotation.OnlineDocumentation;

@OnlineDocumentation("https://error-prone.mycompany.com/myrules/" +
        OnlineDocumentation.TOP_LEVEL_CLASS_URL_PLACEHOLDER +
        "#" +
        OnlineDocumentation.NESTED_CLASS_URL_PLACEHOLDER)
class MyStringRules {
  static class IrrelevantFirstCharSubstring {
    @BeforeTemplate
    String before(String str) {
      return str.substring(0, 1);
    }

    @AfterTemplate
    String after(String str) {
      return String.valueOf(str.charAt(0));
    }
  }
}
