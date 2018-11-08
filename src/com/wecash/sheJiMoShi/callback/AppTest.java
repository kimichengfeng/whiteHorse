/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wecash.sheJiMoShi.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Callback pattern is more native for functional languages where functions are treated as
 * first-class citizens. Prior to Java 8 callbacks can be simulated using simple (alike command)
 * interfaces.
 * 
 */
public class AppTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

  /**
   * Program entry point
   */
  public static void main(String[] args) {
    Task task = new SimpleTask();
    Callback callback = new Callback() {
      @Override
      public void call() {
        LOGGER.info("I'm done now.");
      }
    };
    task.executeWith(callback);
  }
}
