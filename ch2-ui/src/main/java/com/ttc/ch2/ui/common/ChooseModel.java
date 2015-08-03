package com.ttc.ch2.ui.common;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

public class ChooseModel<T> {

	private List<T> choicesList;
	private List<T> selectedList;
	
	private T selectdElementFromChoicesList;
	private T selectdElementFromSelectedList;

	public ChooseModel(Set<T> choicesList,Set<T> selectedList)
	{
		this.choicesList=Lists.newArrayList(choicesList);
		this.selectedList=Lists.newArrayList(selectedList);
	}
	
	public ChooseModel(List<T> choicesList,List<T> selectedList)
	{
		this.choicesList=Lists.newArrayList(choicesList);
		this.selectedList=Lists.newArrayList(selectedList);
	}
	
	
	public void chooseAll(){	
		selectedList.addAll(choicesList);
		choicesList.clear();
	}
		
	public void removeAll(){		
		choicesList.addAll(selectedList);
		selectedList.clear();
	}
	
	public void remove() {
		if (selectdElementFromSelectedList == null)
			return;
		if(!choicesList.contains(selectdElementFromSelectedList))
			choicesList.add(selectdElementFromSelectedList);
		
		selectedList.remove(selectdElementFromSelectedList);
	}
	
	public void choose(){		
		if(selectdElementFromChoicesList==null || selectedList.contains(selectdElementFromChoicesList))
			return;
		selectedList.add(selectdElementFromChoicesList);
		choicesList.remove(selectdElementFromChoicesList);
	}


	public T getSelectdElementFromChoicesList() {
		return selectdElementFromChoicesList;
	}


	public void setSelectdElementFromChoicesList(T selectdElementFromChoicesList) {
		this.selectdElementFromChoicesList = selectdElementFromChoicesList;
	}


	public T getSelectdElementFromSelectedList() {
		return selectdElementFromSelectedList;
	}


	public void setSelectdElementFromSelectedList(T selectdElementFromSelectedList) {
		this.selectdElementFromSelectedList = selectdElementFromSelectedList;
	}


	public List<T> getChoicesList() {
		return choicesList;
	}


	public List<T> getSelectedList() {
		return selectedList;
	}
}
