package jdbc.dal;

import compte.Compte;
import jdbc.PersistenceManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO implements IDAO<Long, Compte> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";
    private static final String INSERT_QUERY ="INSERT INTO compte(solde,idAgence) VALUES(?,?)";

    @Override
    public void create(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, compte.getSolde() );
                ps.setInt( 2, compte.getIdAgence() );
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        compte.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void update(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(Compte object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Compte findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next() ) {
                        Compte compte = new Compte();
                        compte.setId( rs.getInt( "id" ) );
                        compte.setSolde( rs.getInt( "solde" ) );
                        compte.setIdAgence( rs.getInt( "idAgence" ) );
                        list.add( compte );
                    }
                }
            }
        }
        return list;
    }
}
