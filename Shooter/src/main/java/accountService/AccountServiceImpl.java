package accountService;

/**
 * Created with IntelliJ IDEA.
 * User: alexandr
 * Date: 12.10.13
 * Time: 10:45
 * To change this template use File | Settings | File Templates.
 */
import accountService.dao.UsersDAO;
import accountService.dataSets.UsersDataSet;
import base.Abonent;
import base.AccountService;
import base.MessageSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AccountServiceImpl implements Abonent, Runnable, AccountService {

    private Address AccountServiceAddress;
    private MessageSystem ms;

    public AccountServiceImpl(MessageSystem ms){
        this.ms = ms;
        this.AccountServiceAddress = new Address();
        ms.addService(this);
        ms.getAddressService().setAccountService(AccountServiceAddress);
    }

    public void run(){
        while(true)
        {
            ms.execForAbonent(this);
        }
    }
    public void setNewUserInDb(String name, String password) {
        try {
            Connection connection = MakeConnection.getConnection();
            UsersDAO usersDAO = new UsersDAO(connection);
            usersDAO.setNewUser(name, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Long getUserId(String name, String password){
        long id = ErrorUserLogin;
        try {
            Connection connection = MakeConnection.getConnection();
            UsersDAO userDAO = new UsersDAO(connection);
            UsersDataSet result = userDAO.getByName(name);
            if(result.getIsEmpty())
                id =  ErrorUserLogin;
            else {
                if ( result.getPassword().equals(password)) {
                    id = result.getId();
                } else {
                    id =  ErrorUserPassword;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Address getAddress() {
        return AccountServiceAddress;
    }

    public MessageSystem getMessageSystem(){
        return ms;
    }
}