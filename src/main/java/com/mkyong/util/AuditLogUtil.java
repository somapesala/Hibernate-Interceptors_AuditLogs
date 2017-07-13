package com.mkyong.util;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import com.mkyong.common.AuditLog;
import com.mkyong.interceptor.IAuditLog;
import com.mkyong.persistence.HibernateUtil;

public class AuditLogUtil{
	
	public static void LogIt(String action,IAuditLog entity, Connection conn ){
		
		Session tempSession = HibernateUtil.getSessionFactory().openSession(conn);
		
		  Date dNow = new Date( );
	      SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yy HH:mm:ss");
	      
		try {
			AuditLog auditRecord = new AuditLog(action,entity.getLogDeatil()
					, ft.format(dNow.getTime()),entity.getId(), entity.getClass().toString());
			tempSession.save(auditRecord);
			tempSession.flush();
			
		} finally {	
			tempSession.close();
		}
	} 
}