CREATE ALIAS MYFUNCTION AS $$
String getTableContent(java.sql.Connection con) throws Exception {
    String resultValue=null;
    java.sql.ResultSet rs = con.createStatement().executeQuery(
    " SELECT * FROM TABLE_NAME");
       while(rs.next())
       {
        resultValue=rs.getString(1);
}
    return resultValue;
}
$$;