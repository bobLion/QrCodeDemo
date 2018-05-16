package database.greendao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.VehicleEntity;

public class SQLConnect {
	private Connection con = null;
	private Statement stmt=null;
	private String ipString;
	private String keyString;
	private List<VehicleEntity> vehicleEntities = new ArrayList<>();

	public SQLConnect(String ip, String key) {
		ipString = ip;		
		keyString = key;	
	}
	public boolean CreateLink(){
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager
					.getConnection(
							"jdbc:jtds:sqlserver://"+ipString+":1433/VEHECLE_MANAGE_DATA;charset=gbk",
							"sa", keyString);
			stmt = con.createStatement();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<VehicleEntity> select(String tmbh){
		try{
			String sqlString = "select * from VEHICLE WHERE TMBH='"+ tmbh +"';";
			ResultSet rs = stmt.executeQuery(sqlString);
			while (rs.next()) {
				VehicleEntity vehicleEntity = new VehicleEntity();
				vehicleEntity.setId(Integer.valueOf(rs.getString("ID")));
				vehicleEntity.setVehicleNum(rs.getString("CPHM"));
				vehicleEntity.setVehicleWeight(rs.getString("CLZZ"));
				vehicleEntity.setIsCheck(Integer.valueOf(rs.getString("SFJR")));
				vehicleEntity.setVehicleDepartment(rs.getString("CLDW"));
				vehicleEntity.setCheckInDate(rs.getString("JRSJ"));
				vehicleEntity.setCheckOutDate(rs.getString("JCSJ"));
				vehicleEntity.setDriverName(rs.getString("JSRXM"));
				vehicleEntity.setQrCode(rs.getString("TMBH"));
				vehicleEntities.add(vehicleEntity);
			}
			rs.close();
		} catch (Exception e) {
		}
		return vehicleEntities;
	}
	public boolean ins_upd_del(VehicleEntity vehicleEntity) {
		StringBuffer sb = new StringBuffer();

		String sql = sb.append("insert into VEHICLE(CPHM,CLZZ,SFJR,JRSJ,JCSJ,JSRXM,CLDW,TMBH) values(")
				.append("N'"+vehicleEntity.getVehicleNum()+"',")
				.append("N'"+ vehicleEntity.getVehicleWeight()+"',")
				.append("'"+vehicleEntity.getIsCheck()+"',")
				.append("'"+vehicleEntity.getCheckInDate()+"',")
				.append("'"+vehicleEntity.getCheckOutDate()+"',")
				.append("N'"+vehicleEntity.getDriverName()+"',")
				.append("N'"+vehicleEntity.getVehicleDepartment()+"',")
				.append("'"+vehicleEntity.getQrCode()+"'")
				.append(");").toString();
//		String sql = "insert into VEHICLE(CPHM,CLZZ,SFJR,JRSJ,JCSJ,JSRXM,CLDW,TMBH) values(N'沪A12345',N'10吨','0','20180508','20180508',N'夏新明',N'上海市政','03900540');";
		try {
			int re = stmt.executeUpdate(sql);
			if (re > 0) {
				return true;
			}
		} catch (SQLException e) {
		}
		return false;
	}	
	public void close() {
		try {
			if(null != con){
				con.close();
			}
			if(null != stmt){
				stmt.close();
			}
		} catch (SQLException e) {
		}
	}
}
