package com.polypro.dao;
import Helper.JDBCHelper;
import Model.Hocvien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class HocVienDAO {
public void insert(Hocvien model){
String sql="INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
JDBCHelper.executeUpdate(sql,
model.getMaKH(),
model.getMaNH(),
model.getDiem());
}
public void update(Hocvien model){
String sql="UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
JDBCHelper.executeUpdate(sql,
model.getMaKH(),
model.getMaNH(),
model.getDiem(),
model.getMaHV());
}
public void delete(Integer MaHV){
String sql="DELETE FROM HocVien WHERE MaHV=?";
JDBCHelper.executeUpdate(sql, MaHV);
}
public List<Hocvien> select(){
String sql="SELECT * FROM HocVien";
return select(sql);
}
public Hocvien findById(Integer mahv){
String sql="SELECT * FROM HocVien WHERE MaHV=?";
List<Hocvien> list = select(sql, mahv);
return list.size() > 0 ? list.get(0) : null;
}
private List<Hocvien> select(String sql, Object...args){
List<Hocvien> list = new ArrayList<>();
try {
ResultSet rs = null;
try {
rs = JDBCHelper.executeQuery(sql, args);
while(rs.next()){
Hocvien model=readFromResultSet(rs);
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
private Hocvien readFromResultSet(ResultSet rs) throws SQLException{
Hocvien model=new Hocvien();
model.setMaHV(rs.getInt("MaHV"));
model.setMaKH(rs.getInt("MaKH"));
model.setMaNH(rs.getString("MaNH"));
model.setDiem(rs.getDouble("Diem"));
return model;
}
}