package jdbc.dal;


import compte.Compte;
import compte.ComptePayant;
import jdbc.PersistenceManager;

import javax.management.Query;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComptePayantDAO implements IDAO<Long, ComptePayant> {

    private static final String INSERT_QUERY ="INSERT INTO comptePayant(idCompte) VALUES(?)";
    private static final String FIND_QUERY = "SELECT * FROM comptePayant WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM comptePayant";

    @Override
    public void create(ComptePayant comptePayant) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, comptePayant.getIdCompte() );
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        comptePayant.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void update(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(ComptePayant object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public ComptePayant findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ComptePayant> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}
