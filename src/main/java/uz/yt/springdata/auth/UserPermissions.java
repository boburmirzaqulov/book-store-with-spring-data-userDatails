package uz.yt.springdata.auth;

public enum UserPermissions {
    READ_BOOK("BOOK:READ"),
    CREATE_BOOK("BOOK:CREATE"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    UPDATE("UPDATE"),
    READ("READ"),
    READ_DELIVERY("DELIVERY:READ"),
    CREATE_DELIVERY("DELIVERY:CREATE"),
    CREATE_ORDER_ITEMS("ORDER_ITEMS:CREATE"),
    CREATE_ORDERS("ORDERS:CREATE"),
    READ_ORDERS("ORDERS:READ");

    private final String name;


    UserPermissions(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
