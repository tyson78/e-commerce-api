package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        List<Category> categoryList = new ArrayList<>();

        String sql = """
                SELECT * FROM categories;
                """;

        try (Connection connection = getConnection()) {
            PreparedStatement s = connection.prepareStatement(sql);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                categoryList.add( new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String sql = """
                SELECT * FROM categories
                WHERE category_id = ?;
                """;

        Category result = null;

        try (Connection connection = getConnection()) {
            PreparedStatement s = connection.prepareStatement(sql);
            s.setInt(1, categoryId);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                result = new Category(rs.getInt("category_id"), rs.getString("name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        Category result = null;

        String sql = """
                INSERT INTO categories (`name`, `description`)
                VALUES (?, ?);
                """;

        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());

            ps.executeUpdate(); // returns number of records that were affected

            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    result = new Category(rs.getInt(1), category.getName(), category.getDescription());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String sql = """
                UPDATE categories
                SET `name` = ?, `description` = ?
                WHERE category_id = ?;
                """;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, categoryId);

            ps.executeUpdate(); // returns number of records that were affected

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String sql = """
                DELETE FROM categories
                WHERE category_id = ?;
                """;
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, categoryId);

            ps.executeUpdate();
            // Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement
            // or an SQL statement that returns nothing, such as an SQL DDL statement.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
