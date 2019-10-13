package jdbc.dal;

import compte.Compte;
import jdbc.PersistenceManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO implements IDAO<Integer, Compte> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id = ?";
    private static final String INSERT_QUERY ="INSERT INTO compte(solde,idAgence) VALUES(?,?)";
    private static final String UPDATE_QUERY ="UPDATE compte SET solde=?, idAgence=? WHERE id=?";
    private static final String REMOVE_QUERY ="DELETE FROM compte WHERE id=?";

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
    public void update(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, compte.getSolde() );
                ps.setInt(2, compte.getIdAgence());
                ps.setInt(3, compte.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void remove(Compte compte) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( REMOVE_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setInt( 1, compte.getId() );
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Compte findById(Integer integer) throws SQLException, IOException, ClassNotFoundException {
        Compte compte = null;
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(FIND_QUERY)){
                ps.setInt(1, integer);
                try (ResultSet rs  = ps.executeQuery()){
                    if(rs.next()){
                        compte = new Compte(rs.getDouble(2));
                        compte.setId(rs.getInt(1));
                        compte.setIdAgence(rs.getInt(3));
                    }
                }
            }
        }
        return compte;
    }

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Compte> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
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
