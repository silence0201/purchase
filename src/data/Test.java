package data;

import java.text.SimpleDateFormat;
import java.util.*;

import request.*;
import stockman.*;


public class Test {
	public static void main(String[] args) throws Exception{
		RequestMan requestMan=new RequestMan();
		requestMan.setRequestNotices() ;
		ArrayList<RequestNotice> requestNotices = requestMan.getRequestNotices() ;
		
		Iterator<RequestNotice> requestNoticeIterator = requestNotices.iterator() ;
		
		while(requestNoticeIterator.hasNext()){
			RequestNotice rn = requestNoticeIterator.next() ;
			System.out.print("<p class='bg-info noticeCon'>") ;
			System.out.print("你有订单号为"+rn.getRequestID()+"的订单已经"+rn.getRequestStatement()) ; 
			System.out.print("</p>") ;
		}
		AddExport im =new AddExport();
		im.setRequestID(100001);
		im.setUserID("S00002");
		Date dnow = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dtime="";				
		dtime=sdf.format(dnow);
		im.setExportTime(dtime);
		im.setImportSql();
	}
}
