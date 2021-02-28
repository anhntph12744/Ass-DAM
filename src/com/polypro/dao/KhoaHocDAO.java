package com.polypro.dao;
import Helper.JDBCHelper;
import Model.Khoahoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class KhoaHocDAO {
public void insert(Khoahoc model){
String sql="INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?,?)";
JDBCHelper.executeUpdate(sql,
model.getMaCD(),
model.getHocPhi(),
model.getThoiLuong(),
model.getNgayKG(),
model.getGhiChu(),
model.getMaNV());
}
public void update(Khoahoc model){
String sql="UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHEREMaKH=?";
JDBCHelper.executeUpdate(sql,
model.getMaCD(),
model.getHocPhi(),
model.getThoiLuong(),
model.getNgayKG(),
model.getGhiChu(),
model.getMaNV(),
model.getMaKH());
}
public void delete(Integer MaKH){
String sql="DELETE FROM KhoaHoc WHERE MaKH=?";
JDBCHelper.executeUpdate(sql, MaKH);
}
public List<Khoahoc> select(){
String sql="SELECT * FROM KhoaHoc";
return select(sql);
}
public Khoahoc findById(Integer makh){
String sql="SELECT * FROM KhoaHoc WHERE MaKH=?";
List<Khoahoc> list = select(sql, makh);
return list.size() > 0 ? list.get(0) : null;
}
private List<Khoahoc> select(String sql, Object...args){
List<Khoahoc> list=new ArrayList<>();
try {
ResultSet rs = null;
try {
rs = JDBCHelper.executeQuery(sql, args);
while(rs.next()){
Khoahoc model = readFromResultSet(rs);
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
private Khoahoc readFromResultSet(ResultSet rs) throws SQLException{
Khoahoc model=new Khoahoc();
model.setMaKH(rs.getInt("MaKH"));
model.setHocPhi(rs.getDouble("HocPhi"));
model.setThoiLuong(rs.getInt("ThoiLuong"));
model.setNgayKG(rs.getDate("NgayKG"));
model.setGhiChu(rs.getString("GhiChu"));
model.setMaNV(rs.getString("MaNV"));
model.setNgayTao(rs.getDate("NgayTao"));
model.setMaCD(rs.getString("MaCD"));
return model;
}
}