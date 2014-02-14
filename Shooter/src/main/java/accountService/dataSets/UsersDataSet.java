package accountService.dataSets;

/**
 * Created with IntelliJ IDEA.
 * User: vanik
 * Date: 11.12.13
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class UsersDataSet {
    private long id;
    private String name;
    private String password;

    private boolean isEmpty;

    public UsersDataSet(long id, String name, String password, boolean isEmpty) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isEmpty = isEmpty;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public boolean getIsEmpty() {
        return isEmpty;
    }
}
