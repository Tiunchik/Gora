package org.album.repositories;

import org.album.domains.Image;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class ImageRepository implements BaseRepository<Image> {

    private final Logger log = LoggerFactory.getLogger(ImageRepository.class);

    private final SQLiteDataSource pool;

    public ImageRepository(@Autowired SQLiteDataSource source) {
        this.pool = source;
    }

    @Override
    public Image save(Image image) {
        try (Connection cn = pool.getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(
                    "INSERT INTO image " +
                            "(im)" +
                            "VALUES (?)")) {
                st.setBytes(1, image.getImage());
                st.execute();
                return image;
            }
        } catch (SQLException ex) {
            log.error("Image save SQL", ex);
        }
        return null;
    }

    @Override
    public Image update(Image image) {
        try (Connection cn = pool.getConnection()) {
            try (PreparedStatement st = cn.prepareStatement(
                    "UPDATE image SET im = ? where id = ?")) {
                st.setBytes(1, image.getImage());
                st.setInt(2, image.getId());
                st.executeUpdate();
                return image;
            }
        } catch (SQLException ex) {
            log.error("Image update SQL", ex);
        }
        return null;
    }

    @Override
    public List<Image> findAll() {
        List<Image> answerList = new ArrayList<>(300);
        try (Connection cn = pool.getConnection();
             PreparedStatement st = cn.prepareStatement(
                     "SELECT * FROM image")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Image temp = new Image();
                temp.setId(rs.getInt("id"));
                temp.setImage(rs.getBytes("im"));
                answerList.add(temp);
            }
        } catch (SQLException ex) {
            log.error("Image FindAll SQL", ex);
        }
        return answerList;
    }

    @Override
    public Optional<Image> findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement st = cn.prepareStatement(
                     "SELECT * FROM image WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Image temp = new Image();
                temp.setId(rs.getInt("id"));
                temp.setImage(rs.getBytes("im"));
                return Optional.of(temp);
            }
        } catch (SQLException ex) {
            log.error("Image findById SQL", ex);
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement st = cn.prepareStatement(
                     "DELETE FROM image WHERE  id =?")) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            log.error("Image delete SQL", ex);
        }
    }

    @Override
    public List<Integer> getIdList() {
        List<Integer> answerList = new ArrayList<>(300);
        try (Connection cn = pool.getConnection();
             PreparedStatement st = cn.prepareStatement(
                     "SELECT id FROM image")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                answerList.add(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            log.error("Image Find All Id SQL", ex);
        }
        return answerList;
    }
}
