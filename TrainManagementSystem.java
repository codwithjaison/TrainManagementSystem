import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TrainManagementSystem
{
    private static final String JDBC_URL="jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME="scott";
    private static final String PASSWORD="tiger";

    static int train_no=0;
    static String train_name="";
    static String train_source="";
    static String train_destination="";
    static Double train_ticket=0.0;
    static int train_couches=0;
    static int train_journey=0;

    public static void main(String args[]) throws Exception
     {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Scanner sc=new Scanner(System.in);
         
        try(Connection connection=DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD))
          {
          
              while(true)
                {
                   System.out.println("Connection to database");
                   System.out.println("\t\t\t\t Peration Menu \n");
                   System.out.println("\t\t\t1->Create (Insert) Operation \n");
                   System.out.println("\t\t\t2->Read (Select) Operation \n");
                   System.out.println("\t\t\t3->Update Operation \n");
                   System.out.println("\t\t\t4->Delete Operation \n");
                   System.out.println("\t\t\t5->Break Operation \n");
                   System.out.println("\t\tSelect Operation 1 to 5=");
                   int op=sc.nextInt();

                     if(op==1)
                       {
                           System.out.println("\tInsert Operation=");
                           System.out.print("\tEnter train_no=");
                           train_no=sc.nextInt();
                           System.out.print("\tEnter train_name=");
                           train_name=sc.next();
                           System.out.print("\tEnter train_source=");
                           train_source=sc.next();
                           System.out.print("\tEnter train_ticket=");
                           train_ticket=sc.nextDouble();
                           System.out.print("\tEnter train_couches=");
                           train_couches=sc.nextInt();
                           System.out.print("\tEnter train_journey=");
                           train_journey=sc.nextInt();

                           insertRecord(connection,train_no,train_name,train_source,train_destination,train_ticket,train_couches,train_journey);
                       }  
                     else if(op==2)
                           {
                              selectRecord(connection);
                           }
                          else if(op==3)
                               {
                                  System.out.println("Update Operation=");
                                  System.out.println("\tEnter train_no=");
                                  train_no=sc.nextInt();
                                  System.out.println("\tEnter train_name=");
                                  train_name=sc.next();
                                  updateRecord(connection,train_no,train_name);
                               }
                              else if(op==4)
                                   {
                                       System.out.println("\t Delete Operation");
                                       System.out.println("\tEnter train_no=");
                                       train_no=sc.nextInt();
                                       deleteRecord(connection,train_no);
                                   }                  
                                  else if(op==5)
                                        {
                                           break;
                                        }
                                       else
                                         {
                                            System.out.println("Invalid Operation allow is 1 to 5");
                                         }
                }
                   
          }
         catch(SQLException e)
          {
            e.printStackTrace();
          }
     }
 
   private static void insertRecord(Connection connection,int train_no,String train_name,String train_source,String train_destination,Double train_ticket,int train_couches,int train_journey) throws SQLException
      {
         String insertSql="INSERT INTO train(train_no,train_name,train_source,train_destination,train_ticket,train_couches,train_journey) values(?,?,?,?,?,?,?)";
          
           try(PreparedStatement pstmt=connection.prepareStatement(insertSql))
              {
                 pstmt.setInt(1,train_no);
                 pstmt.setString(2,train_name);
                 pstmt.setString(3,train_source);
                 pstmt.setString(4,train_destination);
                 pstmt.setDouble(5,train_ticket);
                 pstmt.setInt(6,train_couches);
                 pstmt.setInt(7,train_journey);
                 int rowsInserted=pstmt.executeUpdate();
                 System.out.println(rowsInserted+"row(s)inserted");
              }
      }

  private static void selectRecord(Connection connection) throws SQLException
      {
         String selectSql="SELECT * FROM train";
          try(Statement stmt=connection.createStatement();
              ResultSet rs=stmt.executeQuery(selectSql))
             {
                System.out.println("Records:");
                while(rs.next())
                  {
                      int train_no=rs.getInt("train_no");
                      String train_name=rs.getString("train_name");
                      String train_source=rs.getString("train_source");
                      String train_destination=rs.getString("train_destination");
                      double train_ticket=rs.getDouble("train_ticket");
                      int train_couches=rs.getInt("train_couches");
                      int train_journey=rs.getInt("train_journey");
                      
                      System.out.println("\t\t\t\ttrain_no:"+train_no+"\t\t\ttrain_name:"+train_name+"\t\t\ttrain_source:"+train_source+"\t\t\ttrain_destination"+train_destination+"\t\t\ttrain_ticket"+train_ticket+"\t\t\ttrain_couches"+train_couches+"\t\t\ttrain_journey"+train_journey);
                  }
                 System.out.println();
             }
      }

   private static void updateRecord(Connection connection,int train_no,String train_name) throws SQLException
        {
           String UpdateSql="UPDATE train SET train_name=? WHERE train_no=?";
            try(PreparedStatement pstmt=connection.prepareStatement(UpdateSql))
               {
                  pstmt.setString(1,train_name);
                  pstmt.setInt(2,train_no);

                  int rowsUpdate=pstmt.executeUpdate();
                  System.out.println(rowsUpdate+"row(s) update");
               }
        }
   private static void deleteRecord(Connection connection,int train_no)throws SQLException
    {
       String deleteSql="DELETE FROM train WHERE train_no=?";
       try(PreparedStatement pstmt=connection.prepareStatement(deleteSql))
        {
           pstmt.setInt(1,train_no);
           int rowsDeleted=pstmt.executeUpdate();
           System.out.println(rowsDeleted+"row(s) deleted");
        }
    }

}