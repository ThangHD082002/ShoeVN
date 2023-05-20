/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HandleWithSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Brand;
import model.Customer;
import model.Product;
import model.ProductGroup;
import model.Size;

/**
 *
 * @author Thắng đẹp trai
 */
public class MySqlProcess extends ConectMySQL {

    // lay ra cac nhom san pham trong ProductGroup
    public List<ProductGroup> getProductGroup() {
        List<ProductGroup> list = new ArrayList<>();
        String sql = "SELECT `productgroup`.`id`,\n"
                + "    `productgroup`.`name`\n"
                + "FROM `ttcs`.`productgroup`;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductGroup pr = new ProductGroup(rs.getInt("id"), rs.getString("name"));
                list.add(pr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // lay ra nhom san pham co id  = ?
    public ProductGroup getProductGroupById(int id) {
        String sql = "SELECT `productgroup`.`id`,\n"
                + "    `productgroup`.`name`\n"
                + "FROM `ttcs`.`productgroup`\n"
                + "where id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductGroup pr = new ProductGroup(rs.getInt("id"), rs.getString("name"));
                return pr;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // lay ra tat ca san pham
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT `product`.`id`,\n"
                + "    `product`.`name`,\n"
                + "    `product`.`price`,\n"
                + "    `product`.`img`,\n"
                + "    `product`.`pgId`\n"
                + "FROM `ttcs`.`product`;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setImg(rs.getString("img"));
                ProductGroup pr = getProductGroupById(rs.getInt("pgId"));
                p.setProductGroup(pr);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductsBy_PgId(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT `product`.`id`,\n"
                + "    `product`.`name`,\n"
                + "    `product`.`price`,\n"
                + "    `product`.`img`,\n"
                + "    `product`.`pgId`\n"
                + "FROM `ttcs`.`product`\n"
                + "where pgId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setImg(rs.getString("img"));
                ProductGroup pr = getProductGroupById(rs.getInt("pgId"));
                p.setProductGroup(pr);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // lay brand theo id
    public Brand getBrandById(int id) {
        String sql = "SELECT `brand`.`id`,\n"
                + "    `brand`.`name`,\n"
                + "    `brand`.`pgid`\n"
                + "FROM `ttcs`.`brand`\n"
                + "where id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                ProductGroup pg = getProductGroupById(rs.getInt("pgid"));
                b.setProductGroup(pg);
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // lay tat ca brand
    public List<Brand> getBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT `brand`.`id`,\n"
                + "    `brand`.`name`,\n"
                + "    `brand`.`pgid`\n"
                + "FROM `ttcs`.`brand`;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                ProductGroup pg = getProductGroupById(rs.getInt("pgid"));
                b.setProductGroup(pg);
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT `product`.`id`,\n"
                + "    `product`.`name`,\n"
                + "    `product`.`price`,\n"
                + "    `product`.`img`,\n"
                + "    `product`.`pgId`,\n"
                + "    `product`.`brandId`\n"
                + "FROM `ttcs`.`product`\n"
                + "where id = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setImg(rs.getString("img"));
                ProductGroup pr = getProductGroupById(rs.getInt("pgId"));
                Brand b = getBrandById(rs.getInt("brandId"));
                p.setProductGroup(pr);
                p.setBrand(b);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // lay ra danh sach san pham theo ma brand
    public List<Product> getProductByBrand_Id(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT `product`.`id`,\n"
                + "    `product`.`name`,\n"
                + "    `product`.`price`,\n"
                + "    `product`.`img`,\n"
                + "    `product`.`pgId`,\n"
                + "    `product`.`brandId`\n"
                + "FROM `ttcs`.`product`\n"
                + "where brandId = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setImg(rs.getString("img"));
                ProductGroup pr = getProductGroupById(rs.getInt("pgId"));
                p.setProductGroup(pr);
                Brand b = getBrandById(rs.getInt("brandId"));
                p.setBrand(b);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // tim user pas Admin
    public Admin getAdmin(String user, String pass) {
        String sql = "SELECT `admin`.`id`,\n"
                + "    `admin`.`user`,\n"
                + "    `admin`.`pass`\n"
                + "FROM `ttcs`.`admin`\n"
                + "where user = ? and pass = ? ;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Admin a = new Admin(rs.getInt("id"), rs.getString("user"), rs.getString("pass"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // tìm size sản phầm thông qua product id
    public List<Size> getSizesByProductId(int id) {
        List<Size> list = new ArrayList<>();
        String sql = "select  `size`.`nameSize`,`size`.`description`\n"
                + "from  `ttcs`.`product_size`,`ttcs`.`size`\n"
                + "where `size`.`nameSize` =  `product_size`.`nameSize` and `product_size`.`idProduct` = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Size s = new Size(rs.getString("nameSize"), rs.getInt("description"));
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Customer checkAccount(String sdt, String user, String pass) {
        String sql = "SELECT `customer`.`sdt`,\n"
                + "    `customer`.`name`,\n"
                + "    `customer`.`diaChi`,\n"
                + "    `customer`.`gender`,\n"
                + "    `customer`.`username`,\n"
                + "    `customer`.`password`,\n"
                + "    `customer`.`img`\n"
                + "FROM `ttcs`.`customer`\n"
                + "where sdt = ? and username = ? and password = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sdt);
            st.setString(2, user);
            st.setString(3, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer c = new Customer(
                        rs.getString("sdt"),
                        rs.getString("name"),
                        rs.getString("diaChi"),
                        rs.getString("gender"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("img")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // tim san pham thong qua ten
    public List<Product> getProductByName(String ten) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT `product`.`id`,\n"
                + "    `product`.`name`,\n"
                + "    `product`.`price`,\n"
                + "    `product`.`img`,\n"
                + "    `product`.`pgId`,\n"
                + "    `product`.`brandId`\n"
                + "FROM `ttcs`.`product`\n"
                + "where 1=1 ";

        sql += " and name like '%" + ten + "%';";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setImg(rs.getString("img"));
                ProductGroup pr = getProductGroupById(rs.getInt("pgId"));
                p.setProductGroup(pr);
                Brand b = getBrandById(rs.getInt("brandId"));
                p.setBrand(b);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
