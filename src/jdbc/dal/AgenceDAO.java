package jdbc.dal;

import agence.Agence;
import jdbc.PersistenceManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO implements IDAO<Long, Agence> {

    private static final String FIND_ALL_QUERY ="SELECT * FROM agence";

    @Override
    public void create(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void update(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(Agence object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Agence findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Agence> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next() ) {
                        Agence agence = new Agence();
                        agence.setId( rs.getInt( "id" ) );
                        agence.setCode( rs.getInt( "code" ) );
                        agence.setAdresse( rs.getString( "adresse" ) );
                        list.add( agence );
                    }
                }
            }
        }
        return list;
    }
}
