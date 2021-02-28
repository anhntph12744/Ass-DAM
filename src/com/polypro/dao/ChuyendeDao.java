package com.polypro.dao;
import Model.Chuyende;
import Helper.JDBCHelper;
import Model.Chuyende;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ChuyendeDao {
public void insert(Chuyende model){
String sql="INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
JDBCHelper.executeUpdate(sql,
model.getMaCD(),
model.getTenCD(),
model.getHocPhi(),
model.getThoiLuong(),
model.getHinh(),
model.getMoTa());
}
public void update(Chuyende model){
String sql="UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
JDBCHelper.executeUpdate(sql,
model.getTenCD(),
model.getHocPhi(),
model.getThoiLuong(),
model.getHinh(),
model.getMoTa(),
model.getMaCD());
}
public void delete(String MaCD){
String sql="DELETE FROM ChuyenDe WHERE MaCD=?";
JDBCHelper.executeUpdate(sql, MaCD);
}
public List<Chuyende> select(){
String sql="SELECT * FROM ChuyenDe";
return select(sql);
}
public Chuyende findById(String macd){
String sql="SELECT * FROM ChuyenDe WHERE MaCD=?";
List<Chuyende> list = select(sql, macd);
return list.size() > 0 ? list.get(0) : null;
}
private List<Chuyende> select(String sql, Object...args){
List<Chuyende> list=new ArrayList<>();
try {
ResultSet rs = null;
try {
rs = JDBCHelper.executeQuery(sql, args);
while(rs.next()){
Chuyende model=readFromResultSet(rs);
list.add(model);
}
}
finally{
rs.getStatement().getConnection().close();
}
}
catch (SQLException ex) {
throw new RuntimeException(ex);
}
return list;
}
private Chuyende readFromResultSet(ResultSet rs) throws SQLException{
Chuyende model=new Chuyende();
model.setMaCD(rs.getString("MaCD"));
model.setHinh(rs.getString("Hinh"));
model.setHocPhi(rs.getDouble("HocPhi"));
model.setMoTa(rs.getString("MoTa"));
model.setTenCD(rs.getString("TenCD"));
model.setThoiLuong(rs.getInt("ThoiLuong"));
return model;
}
}