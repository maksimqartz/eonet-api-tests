package org.qapower.utils;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

  private final Supplier<T> initializer;
  private T value;
  private boolean initialized = false;

  public Lazy(Supplier<T> initializer) {
    this.initializer = initializer;
  }

  @Override
  public T get() {
    if (!initialized) {
      value = initializer.get();
      initialized = true;
    }
    return value;
  }
}
