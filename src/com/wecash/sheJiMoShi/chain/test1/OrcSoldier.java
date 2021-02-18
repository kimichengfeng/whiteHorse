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
package com.wecash.sheJiMoShi.chain.test1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * OrcSoldier
 *
 */
public class OrcSoldier extends RequestHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrcSoldier.class);


  public OrcSoldier(RequestHandler handler,Boolean synSwitch) {
    super(handler,synSwitch);
  }
  public OrcSoldier(RequestHandler handler,Boolean synSwitch,String tppCode) {
    super(handler,synSwitch,tppCode);
  }

  @Override
  public void handleRequest(Request req) {
    if (req.getRequestType().equals(RequestType.COLLECT_TAX)) {
      printHandling(req);
      req.markHandled();
    } else {
      LOGGER.info("super class = {}",super.getClass());
      super.handleRequest(req);
    }
  }

  @Override
  public Invoker beforeHander(Request req) {
    return null;
  }

  @Override
  public Invoker printHandling(Request req) {
    LOGGER.info("OrcSoldier print: \"{}----{}\"。next={}", this, req);
    Invoker invoker = new Invoker();
    return invoker;
  }

  @Override
  public Invoker afterHander(Request req) {
    return null;
  }


  @Override
  public String toString() {
    return "Orc soldier";
  }
}
