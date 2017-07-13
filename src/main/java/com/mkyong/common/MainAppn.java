package com.mkyong.common;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mkyong.interceptor.StockAuditLogInterceptor;
import com.mkyong.persistence.HibernateUtil;

public class MainAppn {
	public static void main(String[] args) throws ParseException {

		Session session = null;
		Transaction tx = null;

		try {

			StockAuditLogInterceptor interceptor = new StockAuditLogInterceptor();
			
			session = HibernateUtil.getSessionFactory().openSession(interceptor);
			interceptor.setSession(session); 
			
			
			Date date = new Date();
			System.out.println(date);
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String s = formatter.format(date);
			
			Date date1=new SimpleDateFormat().parse(s);
			System.out.println("date :"+date1);        
			
			
			
			//test insert
		/*	tx = session.beginTransaction();
			Stock stockInsert = new Stock();
			stockInsert.setStockCode("222");
			stockInsert.setStockName("abcdefg");
			session.saveOrUpdate(stockInsert);
			tx.commit();
			
			
			
			CricketAuditLogInterceptor cui = new CricketAuditLogInterceptor();
			Session cricketSession = HibernateUtil.getSessionFactory().openSession(cui);
			cui.setSession(cricketSession);
			
			//insert cricket
			tx = cricketSession.beginTransaction();
			CricketTeam ct = new CricketTeam();
			ct.setTeamName("RCB");
			ct.setCaptain("KOHLI");
			cricketSession.saveOrUpdate(ct);
			tx.commit();
			
			
			//test update
			tx = session.beginTransaction();
			Query query = session.createQuery("from Stock where stockId = '45'");
			Stock stockUpdate = (Stock)query.list().get(0);
			stockUpdate.setStockName("update record");
			session.saveOrUpdate(stockUpdate);
			tx.commit();
			
			//test delete
			tx = session.beginTransaction();
			
			Query query1 = session.createQuery("from Stock where stockId = '46'");
			Stock stockDelete = (Stock)query1.uniqueResult();
			session.delete(stockDelete);
			tx.commit();*/

		} catch (RuntimeException e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				// log.error("Couldn’t roll back transaction", rbe);
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}