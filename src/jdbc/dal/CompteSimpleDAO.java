package jdbc.dal;


import compte.Compte;
import compte.CompteSimple;
import jdbc.PersistenceManager;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CompteSimpleDAO implements IDAO<Long, CompteSimple> {

    private static final String INSERT_QUERY ="INSERT INTO compteSimple(idCompte, decouvert) VALUES(?,?)";
    private static final String FIND_QUERY = "SELECT * FROM compteSimple WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compteSimple";

    @Override
    public void create(CompteSimple compteSimple) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, compteSimple.getIdCompte() );
                ps.setDouble( 2, compteSimple.getDecouvert() );
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        compteSimple.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(CompteSimple object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public CompteSimple findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CompteSimple> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }
}
