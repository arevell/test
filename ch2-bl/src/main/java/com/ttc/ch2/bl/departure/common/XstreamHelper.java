package com.ttc.ch2.bl.departure.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;
import com.ttc.ch2.bl.departure.common.tourdeparturegen.TourDepartureAndSC;

public class XstreamHelper {
	
//	private static final File resource=new File("D:/develope/workspaces/workspace-ch2/ch2-test/src/test/java/com/ttc/ch2/bl/departure/tourDeparturesAndSCAll.xml");
	private static final File basePath=new File("D:/develope/workspaces/workspace-ch2/ch2-test/src/test/java/com/ttc/ch2/bl/departure");
	private static final String fileName="tourDeparturesAndSC";
	private static final int partSize=500;
	
	XStream xstream = new XStream();
	
	private List<TourDepartureAndSC> read(File in) throws FileNotFoundException
	{		
		List<TourDepartureAndSC> list = (List<TourDepartureAndSC>)xstream.fromXML(new FileReader(in));
		return list;
	}
	
	private List<TourDepartureAndSC> readAllParts(File in) throws FileNotFoundException
	{
		List<TourDepartureAndSC> list=Lists.newArrayList();
		for (File f : FileUtils.listFiles(in, new String []{"xml"}, false)) {
			if(f.getName().startsWith(fileName))
			{
				list.addAll(read(f));
			}			
		}
		return list;
	}
	
	public void write(List<TourDepartureAndSC> listAll ) throws IOException
	{
		write(listAll, basePath);
	}
	
	public void write(List<TourDepartureAndSC> listAll,File out ) throws IOException
	{
		Iterable<List<TourDepartureAndSC>> partialList=Iterables.partition(listAll, partSize);		
		int i=0;
		for (List<TourDepartureAndSC> list : partialList) {
			String xml = xstream.toXML(list);
			FileUtils.writeStringToFile(new File(out,"tourDeparturesAndSCPart"+(i++)+".xml"),xml);	
		}		
	}
	
	public void writeSimple(List<TourDepartureAndSC> listAll,File out ) throws IOException
	{		
		String xml = xstream.toXML(listAll);
		FileUtils.writeStringToFile(out,xml);
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		XstreamHelper h=new XstreamHelper();
//		h.write(h.read(resource),resourceOut);
			
		System.out.println("All lement Size:"+h.readAllParts(basePath).size());	
		System.out.println("koniec");
	}

}
