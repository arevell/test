package com.ttc.ch2.ui.moduls.info;

import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

//@Component("SystemInfo")
public class SystemInfo extends SelectorComposer<Hbox> {

	@WireVariable
	 Label label1;
		
	@Wire
	Textbox txt;
	
	@Init
	public void init()
	{
		int x=0;
		System.out.println(x++);
	}
	
	
	
	
	@Override
	public void doAfterCompose(Hbox comp) throws Exception {	
		super.doAfterCompose(comp);
	}

	public Label getLabel1() {
		return label1;
	}

	public void setLabel1(Label label1) {
		this.label1 = label1;
	}

	public Textbox getTxt() {
		return txt;
	}

	public void setTxt(Textbox txt) {
		this.txt = txt;
	}


}
