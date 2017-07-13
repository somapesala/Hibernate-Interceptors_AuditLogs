package com.mkyong.common;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mkyong.interceptor.IAuditLog;

@Entity
//@FilterDef(name="stockRecordFilter", parameters=@ParamDef( name="stockRecordFilterParam", type="date" ) )
@Table(name = "cricket_team")
public class CricketTeam implements java.io.Serializable, IAuditLog{
	
	private Integer teamId;
	private String teamName;
	private String captain;
	
	
	public CricketTeam() {
	}
	
	public CricketTeam(int teamId,String teamName,String captain) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.captain = captain;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "team_id", unique = true, nullable = false)
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	@Column(name = "team_name", unique = true, nullable = false)
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Column(name = "captain", unique = true, nullable = false)
	public String getCaptain() {
		return captain;
	}
	public void setCaptain(String captain) {
		this.captain = captain;
	}
	public CricketTeam(String teamName,String captain) {
		this.teamName = teamName;
		this.captain = captain;
	}
	
	
	
	@Transient
//	@Override
	public Long getId(){
		return this.teamId.longValue();
	}
	
	@Transient
//	@Override
	public String getLogDeatil(){
		StringBuilder sb = new StringBuilder();
		sb.append(" teamId : ").append(teamId)
		.append(" teamName : ").append(teamName)
		.append(" captain: ").append(captain);

		return sb.toString();
	}

	@Override
	public String toString() {
		return "Stock [teamId=" + teamId + ", teamName=" + teamName + ", captain=" + captain + "]";
	}

}
