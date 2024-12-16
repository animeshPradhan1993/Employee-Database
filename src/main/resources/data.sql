
INSERT INTO ROLE VALUES ('1', 'ADMIN');
INSERT INTO ROLE VALUES ('2', 'USER');
INSERT INTO ROLE VALUES ('3', 'MANAGER');
INSERT INTO PROJECT VALUES ('1', 'ABN1');
INSERT INTO PROJECT VALUES ('2', 'ABN2');
INSERT INTO PROJECT VALUES ('3', 'ABN3');

INSERT INTO EMPLOYEE
    (ID,
    FIRST_NAME ,
    SURNAME ,
    PASSWORD,
    ROLE) VALUES('1','Animesh', 'Pradhan', 'YWRtaW4=','1');

CREATE ALIAS DELETE_ROLE AS '
     import java.sql.*;
     @CODE
     void DELETEROLE(Connection conn, String role) throws SQLException {
         var sql1 = "UPDATE EMPLOYEE_PROJECT SET EMPLOYEE_ID = 1 WHERE EMPLOYEE_ID IN(SELECT ID FROM EMPLOYEE WHERE ROLE = ?) ";
         var sql2 = "Delete FROM EMPLOYEE WHERE ROLE =?";
         var sql3 = "DELETE FROM ROLE WHERE ID =?";
         try (PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
             stmt1.setString(1, role);
             stmt1.execute();

         }
         try (PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
                      stmt2.setString(1, role);
                      stmt2.execute();

                  }
                  try (PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
                               stmt3.setString(1, role);
                               stmt3.execute();

                           }

     }
 ';
