package com.polypro.dao;
import Helper.JDBCHelper;
import Model.Nguoihoc;
import Helper.JDBCHelper;
import Model.Nguoihoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class NguoiHocDAO {
public void insert(Nguoihoc model){
String sql="INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
JDBCHelper.executeUpdate(sql,
model.getMaNH(),
model.getHoTen(),
model.getNgaySinh(),
model.getGioiTinh(),
model.getDienThoai(),
model.getEmail(),
model.getGhiChu(),
model.getMaNV());
}
public void update(Nguoihoc model){
String sql="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?,MaNV=? WHERE MaNH=?";
JDBCHelper.executeUpdate(sql,
model.getHoTen(),
model.getNgaySinh(),
model.getGioiTinh(),
model.getDienThoai(),
model.getEmail(),
model.getGhiChu(),
model.getMaNV(),
model.getMaNH());
}
public void delete(String id){
String sql="DELETE FROM NguoiHoc WHERE MaNH=?";
JDBCHelper.executeUpdate(sql, id);
}
public List<Nguoihoc> select(){
String sql="SELECT * FROM NguoiHoc";
return select(sql);
}
public List<Nguoihoc> selectByKeyword(String keyword){
String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
return select(sql, "%"+keyword+"%");
}
public List<Nguoihoc> selectByCourse(Integer makh){
String sql="SELECT * FROM NguoiHoc WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
return select(sql, makh);
}
public Nguoihoc findById(String manh){
String sql="SELECT * FROM NguoiHoc WHERE MaNH=?";
List<Nguoihoc> list = select(sql, manh);
return list.size() > 0 ? list.get(0) : null;
}
private List<Nguoihoc> select(String sql, Object...args){
List<Nguoihoc> list=new ArrayList<>();
try {
ResultSet rs = null;
try {
rs = JDBCHelper.executeQuery(sql, args);
while(rs.next()){
Nguoihoc model=readFromResultSet(rs);
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
private Nguoihoc readFromResultSet(ResultSet rs) throws SQLException{
Nguoihoc model=new Nguoihoc();
model.setMaNH(rs.getString("MaNH"));
model.setHoTen(rs.getString("HoTen"));
model.setNgaySinh(rs.getDate("NgaySinh"));
model.setGioiTinh(rs.getBoolean("GioiTinh"));
model.setDienThoai(rs.getString("DienThoai"));
model.setEmail(rs.getString("Email"));
model.setGhiChu(rs.getString("GhiChu"));
model.setMaNV(rs.getString("MaNV"));
model.setNgayDK(rs.getDate("NgayDK"));
return model;
}
}