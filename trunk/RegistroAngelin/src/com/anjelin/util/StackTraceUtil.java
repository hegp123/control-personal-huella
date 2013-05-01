/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author Admon
 */
public final class StackTraceUtil {

  public static String getStackTrace(Throwable aThrowable) {
    final Writer result = new StringWriter();
    final PrintWriter printWriter = new PrintWriter(result);
    aThrowable.printStackTrace(printWriter);
    return result.toString();
  }   
}
