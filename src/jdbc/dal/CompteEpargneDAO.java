package jdbc.dal;

import compte.Compte;
import compte.CompteEpargne;
import jdbc.PersistenceManager;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CompteEpargneDAO implements IDAO<Long, CompteEpargne>{

    private static final String INSERT_QUERY ="INSERT INTO compteEpargne(idCompte, tauxInteret) VALUES(?,?)";

    @Override
    public void create(CompteEpargne compteEpargne) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, compteEpargne.getIdCompte() );
                ps.setDouble( 2, compteEpargne.getTauxInteret() );
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        compteEpargne.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void update(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(CompteEpargne object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public CompteEpargne findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<CompteEpargne> findAll() throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

}


