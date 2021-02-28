package com.polypro.dao;

import Helper.JDBCHelper;
import Model.Nhanvien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public void insert(Nhanvien model) {
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                model.getMaNV(),
                model.getMatKhau(),
                model.getHoTen(),
                model.getVaiTro());
    }

    public void update(Nhanvien model) {
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
        JDBCHelper.executeUpdate(sql,
                model.getMatKhau(),
                model.getHoTen(),
                model.getVaiTro(),
                model.getMaNV());
        
    
}
public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JDBCHelper.executeUpdate(sql, MaNV);
    }

    public List<Nhanvien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    public Nhanvien findById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<Nhanvien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<Nhanvien> select(String sql, Object... args) {
        List<Nhanvien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Nhanvien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
            //    rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private Nhanvien readFromResultSet(ResultSet rs) throws SQLException {
        Nhanvien model = new Nhanvien();
        model.setMaNV(rs.getString("MaNV"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setHoTen(rs.getString("HoTen"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
        return model;
    }
}
