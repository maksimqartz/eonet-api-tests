package org.qapower.utils;

import java.util.function.Supplier;

/**
 * Потокобезопасный lazy-supplier с double-checked locking. Используется для шаринга одного
 * результата запроса между несколькими тестами одного класса.
 */
public final class Lazy<T> implements Supplier<T> {

  private final Supplier<T> initializer;
  private volatile T value;
  private volatile boolean initialized;

  public Lazy(Supplier<T> initializer) {
    this.initializer = initializer;
  }

  @Override
  public T get() {
    if (!initialized) { // быстрый путь — без блокировки
      synchronized (this) {
        if (!initialized) { // повторная проверка под блокировкой
          value = initializer.get();
          initialized = true;
        }
      }
    }
    return value;
  }
}
