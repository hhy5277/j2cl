package com.google.j2cl.transpiler.integration.gwtincompatible;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.io.Files; // Non-emulated j/c/g/common/io class.
import java.text.SimpleDateFormat; // Non-emulated jre class.

public class Main {
  public static void main(String... args) {
    compatible();
  }

  public static void compatible() {
    SomeEnum a = SomeEnum.COMPATIBLE;
  }

  @GwtIncompatible
  public static void incompatible() {
    SomeEnum a = SomeEnum.INCOMPATIBLE;
  }

  enum SomeEnum {
    COMPATIBLE {
      @Override
      void method() {}
    },

    @GwtIncompatible
    INCOMPATIBLE {
      @Override
      void method() {
        new SimpleDateFormat();
      }
    };

    abstract void method();
  }

  @GwtIncompatible
  public static void incompatibleMissingInDep() {
    Files.toByteArray(null);
  }
}