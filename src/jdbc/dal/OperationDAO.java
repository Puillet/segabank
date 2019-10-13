package jdbc.dal;

import jdbc.PersistenceManager;
import operation.Operation;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO implements IDAO<Long, Operation> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM operation";
    private static final String INSERT_QUERY ="INSERT INTO operation(montant,transaction,idCompte) VALUES(?,?,?)";

    @Override
    public void create(Operation operation) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setDouble( 1, operation.getMontant() );
                ps.setObject( 2, operation.getTransaction() );
                ps.setInt(3, operation.getIdCompte());
                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        operation.setId( rs.getInt( 1 ) );
                    }
                }
            }
        }
    }

    @Override
    public void update(Operation object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public void remove(Operation object) throws SQLException, IOException, ClassNotFoundException {

    }

    @Override
    public Operation findById(Long aLong) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Operation> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Operation> liste = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try (Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery(FIND_ALL_QUERY)) {

                while(rs.next()){

                    Operation operation = new Operation(rs.getDouble("montant"), rs.getInt("idCompte"), Operation.Transaction.fromString(rs.getString("transaction")) );
                    liste.add(operation);
                }
            }
        }
        return liste;
    }
}
