package com.banking.user.linebyline.abend;

import java.sql.*;

public class TxtColumnNameAndDataDao {

    private static Connection connection;
    private static Statement statement;

    public static boolean DatabaseConnection(){
        boolean result = false;

        try{
            TxtColumnNameAndDataDao.setConnection(DriverManager.getConnection("jdbc:sqlserver://DEVSQL2K8:1433;DatabaseName=SOFTZONE","softusr", "Soft135Usr"));
            result = true;
        }
        catch (SQLException e){
            String errorMessage = "Database Connection is not available! Details: " + e.getMessage();
            System.out.println(errorMessage);
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public static void insertTable(TxtColumnNameAndData txtColumnNameAndData){

        String sql = "INSERT INTO APP_SERVER_LOG_DATA " +
                "VALUES ("+
					txtColumnNameAndData.getId()+
                "'"+
                txtColumnNameAndData.getApplid()+"','" +
                txtColumnNameAndData.getTransaction()+"','" +
                txtColumnNameAndData.getProgram()+"','" +
                txtColumnNameAndData.getTasks()+"','" +
                txtColumnNameAndData.getAvgResponseTime()+"','" +
                txtColumnNameAndData.getMaxResponseTime()+"','" +
                txtColumnNameAndData.getAvgDispatchTime()+"','" +
                txtColumnNameAndData.getAvgDispatchCount()+"','" +
                txtColumnNameAndData.getAvgUserCpuTime()+"','" +
                txtColumnNameAndData.getAvgSuspendTime()+"','" +
                txtColumnNameAndData.getMaxSuspendTime()+"','" +
                txtColumnNameAndData.getAvgDispwaitTime()+"','" +
                txtColumnNameAndData.getDate()+"')";

        System.out.println(sql);

        try{
            setStatement(TxtColumnNameAndDataDao.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY));
            getStatement().executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        TxtColumnNameAndDataDao.connection = connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void setStatement(Statement statement) {
        TxtColumnNameAndDataDao.statement = statement;
    }
}
