/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Admon
 */
public class MaxLengthTextDocument extends PlainDocument {
	//Store maximum characters permitted
	private int maxChars;

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if(str != null && (getLength() + str.length() < maxChars)){
			super.insertString(offs, str, a);
		}
	}
        
        

	//getter e setter omitted

    public MaxLengthTextDocument(int maxChars) {
        this.maxChars = maxChars;
    }
}