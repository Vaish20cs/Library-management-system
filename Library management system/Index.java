//package com.jdbc.library;

import java.sql.*;
import java.sql.Connection;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.awt.*;
import java.text.*;
import javax.swing.table.DefaultTableModel;

//Design Elements.

import javax.swing.border.Border;

final class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}

//End Design Elements.

class login implements ActionListener {
    JFrame F;
    JLabel labeluser, labelpwd, labelbg, stu_l, stuname_l, stubrn_l;
    JPanel panel_login, background, stud_form;
    JTextField userField, stu_t, stuname_t, stubrn_t;
    JPasswordField pwdField;
    JButton login, save_login, newStudent;

    // issue book
    JPanel issue_book;
    JButton issueBook, issue;
    JLabel bstu_l, book_l, issuedate_l, duedate_l;
    JTextField bstu_t, book_t, issuedate_t, duedate_t;
    String issuedate_tString;
    // end issue book

    // new book
    JPanel new_book;
    JButton newBook, save_book;
    JLabel bid_l, bname_l, pub_l, pubdate_l, categ_l, no_of_copy_l;
    JTextField bid_t, bname_t, pub_t, pubdate_t, categ_t, no_of_copy_t;
    // end new book

    // display label
    int flag = 0;
    JLabel error;
    // end display label

    // return book
    JPanel return_book;
    JButton returnBook, returnbtn;
    JLabel sturet_l, bookret_l, return_l;
    JTextField sturet_t, bookret_t, return_t;
    String return_tString;
    // end return book

    //statistics
    JTable j;
    JButton statistics;
    JPanel stati;
    JScrollPane sp;

    //end statistics

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    Connection temp_conn = null;
    Statement temp_stmt = null;
    ResultSet temp_rs = null;

    String nameString, staffidString, loginid_staffString, pwdString;

    login() {
        F = new JFrame("Swing");
        F.setSize(850, 500);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // login
        panel_login = new JPanel();
        panel_login.setSize(500, 500);
        panel_login.setVisible(true);

        labeluser = new JLabel("Username");
        labeluser.setBounds(150, 80, 80, 25);
        panel_login.add(labeluser);

        userField = new JTextField();
        userField.setBounds(150, 100, 200, 30);
        panel_login.add(userField);

        labelpwd = new JLabel("Password");
        labelpwd.setBounds(150, 135, 80, 25);
        panel_login.add(labelpwd);

        pwdField = new JPasswordField();
        pwdField.setBounds(150, 155, 200, 30);
        panel_login.add(pwdField);

        login = new JButton("Login");
        login.addActionListener(this);
        login.setBounds(212, 210, 75, 30);
        panel_login.add(login);

        // background
        background = new JPanel();
        background.setSize(500, 500);
        background.setVisible(false);
        background.setLayout(null);

        /*
         * labelbg = new JLabel("Background");
         * labelbg.setBounds(150, 80, 80, 25);
         * background.add(labelbg);
         */
        panel_login.setLayout(null);
        F.setVisible(true);
        F.add(panel_login);
        F.add(background);

        // Background Panel.

        newStudent = new JButton("New Student");
        newStudent.addActionListener(this);
        newStudent.setBounds(20, 20, 150, 30);
        background.add(newStudent);

        issueBook = new JButton("Issue Book");
        issueBook.addActionListener(this);
        issueBook.setBounds(340, 20, 150, 30);
        background.add(issueBook);

        // End Background Panel.

        // New Student Form.

        stud_form = new JPanel();
        stud_form.setSize(500, 300);
        stud_form.setLocation(20, 60);
        stud_form.setVisible(false);
        stud_form.setLayout(null);

        stu_l = new JLabel("Student ID :");
        stu_l.setBounds(70, 80, 200, 30);
        stud_form.add(stu_l);

        stu_t = new JTextField();
        stu_t.setBounds(150, 80, 200, 30);
        stud_form.add(stu_t);

        stuname_l = new JLabel("Name :");
        stuname_l.setBounds(100, 120, 200, 30);
        stud_form.add(stuname_l);

        stuname_t = new JTextField();
        stuname_t.setBounds(150, 120, 200, 30);
        stud_form.add(stuname_t);

        stubrn_l = new JLabel("Branch :");
        stubrn_l.setBounds(80, 160, 200, 30);
        stud_form.add(stubrn_l);

        stubrn_t = new JTextField();
        stubrn_t.setBounds(150, 160, 200, 30);
        stud_form.add(stubrn_t);

        save_login = new JButton("Save");
        save_login.addActionListener(this);
        save_login.setBounds(212, 210, 75, 30);
        stud_form.add(save_login);

        background.add(stud_form);

        // End Student Form.

        // Issue Book.

        issue_book = new JPanel();
        issue_book.setSize(500, 300);
        issue_book.setLocation(20, 60);
        issue_book.setLayout(null);
        issue_book.setVisible(false);

        bstu_l = new JLabel("Student ID :");
        bstu_l.setBounds(70, 80, 200, 30);
        issue_book.add(bstu_l);

        bstu_t = new JTextField();
        bstu_t.setBounds(150, 80, 200, 30);
        issue_book.add(bstu_t);

        book_l = new JLabel("Book ID :");
        book_l.setBounds(70, 120, 200, 30);
        issue_book.add(book_l);

        book_t = new JTextField();
        book_t.setBounds(150, 120, 200, 30);
        issue_book.add(book_t);

        issuedate_l = new JLabel("Issue Date :");
        issuedate_l.setBounds(80, 160, 200, 30);
        issue_book.add(issuedate_l);

        issuedate_t = new JTextField();
        issuedate_t.setBounds(150, 160, 200, 30);
        issuedate_t.setEditable(false);
        issue_book.add(issuedate_t);

        duedate_l = new JLabel("Due Date :");
        duedate_l.setBounds(80, 200, 200, 30);
        issue_book.add(duedate_l);

        duedate_t = new JTextField();
        duedate_t.setBounds(150, 200, 200, 30);
        issue_book.add(duedate_t);

        issue = new JButton("Issue");
        issue.addActionListener(this);
        issue.setBounds(212, 250, 75, 30);
        issue_book.add(issue);

        background.add(issue_book);

        // End Issue Book.

        // New Book.

        new_book = new JPanel();
        new_book.setSize(500, 350);
        new_book.setLocation(20, 40);
        new_book.setVisible(false);
        new_book.setLayout(null);

        newBook = new JButton("New Book");
        newBook.addActionListener(this);
        newBook.setBounds(180, 20, 150, 30);
        background.add(newBook);

        bid_l = new JLabel("Book ID :");
        bid_l.setBounds(70, 80, 200, 30);
        new_book.add(bid_l);

        bid_t = new JTextField();
        bid_t.setBounds(150, 80, 200, 30);
        new_book.add(bid_t);

        bname_l = new JLabel("Book Name :");
        bname_l.setBounds(70, 120, 200, 30);
        new_book.add(bname_l);

        bname_t = new JTextField();
        bname_t.setBounds(150, 120, 200, 30);
        new_book.add(bname_t);

        pub_l = new JLabel("Publisher :");
        pub_l.setBounds(70, 160, 200, 30);
        new_book.add(pub_l);

        pub_t = new JTextField();
        pub_t.setBounds(150, 160, 200, 30);
        new_book.add(pub_t);

        pubdate_l = new JLabel("Published Date :");
        pubdate_l.setBounds(50, 200, 200, 30);
        new_book.add(pubdate_l);

        pubdate_t = new JTextField();
        pubdate_t.setBounds(150, 200, 200, 30);
        new_book.add(pubdate_t);

        categ_l = new JLabel("Category :");
        categ_l.setBounds(70, 240, 200, 30);
        new_book.add(categ_l);

        categ_t = new JTextField();
        categ_t.setBounds(150, 240, 200, 30);
        new_book.add(categ_t);

        no_of_copy_l = new JLabel("No. of Copies :");
        no_of_copy_l.setBounds(50, 280, 200, 30);
        new_book.add(no_of_copy_l);

        no_of_copy_t = new JTextField();
        no_of_copy_t.setBounds(150, 280, 200, 30);
        new_book.add(no_of_copy_t);

        save_book = new JButton("Save");
        save_book.addActionListener(this);
        save_book.setBounds(212, 320, 75, 30);
        new_book.add(save_book);

        background.add(new_book);

        // End New Book.

        // display label
        error = new JLabel("Login Unsuccessful!!!");
        error.setBounds(190, 310, 200, 30);
        error.setVisible(false);
        panel_login.add(error);
        // end display label

        // Return Book.

        return_book = new JPanel();
        return_book.setSize(500, 300);
        return_book.setLocation(20, 60);
        return_book.setVisible(false);
        return_book.setLayout(null);

        returnBook = new JButton("Return Book");
        returnBook.addActionListener(this);
        returnBook.setBounds(500, 20, 150, 30);
        background.add(returnBook);

        sturet_l = new JLabel("Student ID :");
        sturet_l.setBounds(70, 80, 200, 30);
        return_book.add(sturet_l);

        sturet_t = new JTextField();
        sturet_t.setBounds(150, 80, 200, 30);
        return_book.add(sturet_t);

        bookret_l = new JLabel("Book ID :");
        bookret_l.setBounds(80, 120, 200, 30);
        return_book.add(bookret_l);

        bookret_t = new JTextField();
        bookret_t.setBounds(150, 120, 200, 30);
        return_book.add(bookret_t);

        return_l = new JLabel("Return Date :");
        return_l.setBounds(60, 160, 200, 30);
        return_book.add(return_l);

        return_t = new JTextField();
        return_t.setBounds(150, 160, 200, 30);
        return_t.setEditable(false);
        return_book.add(return_t);

        returnbtn = new JButton("Return");
        returnbtn.addActionListener(this);
        returnbtn.setBounds(212, 210, 75, 30);
        return_book.add(returnbtn);

        background.add(return_book);

        // End Return Book.

        //Statistics.

        stati = new JPanel();
        stati.setSize(500, 300);
        stati.setLocation(20,60);
        stati.setVisible(false);
        stati.setLayout(null);

        statistics = new JButton("Statistics");
        statistics.addActionListener(this);
        statistics.setBounds(660, 20, 150, 30);
        background.add(statistics);

        String col[]={"Book_ID","Book_name","Category","Availability"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        j = new JTable(tableModel);
        sp = new JScrollPane(j);

        sp.setBounds(80,20,500,300);
        stati.add(sp);
        
        //End Statistics.
    }

    

    public void actionPerformed(ActionEvent e) {

        // login
        if (e.getSource() == login) {
            flag = 0;
            String user_ = userField.getText();
            String pwd_ = new String(pwdField.getPassword());
            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                // Execute statements
                stmt.execute("select * from admin");
                rs = stmt.getResultSet();
                while (rs.next()) {
                    nameString = rs.getString("Name");
                    pwdString = rs.getString("Password");
                    staffidString = rs.getString("Staffid");
                    if (pwdString.equals(pwd_) && user_.equals(nameString)) {
                        System.out.println("Login Successful!!!");
                        flag = 1;
                        panel_login.setVisible(false);
                        error.setVisible(false);
                        background.setVisible(true);
                        break;
                    }
                }
                if (flag == 0) {
                    error.setVisible(true);
                }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {
                error.setVisible(true);
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception b) {
                    }
                }
            }
        }
        // end login

        // book issue
        if (e.getSource() == issueBook) {
            stud_form.setVisible(false);
            issue_book.setVisible(true);
            new_book.setVisible(false);
            return_book.setVisible(false);
            stati.setVisible(false);


            // system date
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            issuedate_tString = dateFormat.format(date);
            issuedate_t.setText(issuedate_tString);
            // end system date
        }
        if (e.getSource() == issue) {
            flag = 0;
            String bstu_tString = bstu_t.getText();
            String book_tString = book_t.getText();
            String duedate_tString = duedate_t.getText();

            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                stmt.execute("select * from book");
                rs = stmt.getResultSet();
                while (rs.next()) {
                    String temp_bookid = rs.getString("Book_ID");
                    int temp_avail = Integer.parseInt(rs.getString("availability"));
                    if (book_tString.equals(temp_bookid) && temp_avail > 0) {
                        String sql = "insert into book_issue values(" + book_tString + ",'" + bstu_tString + "','"
                                + issuedate_tString + "','" + duedate_tString + "');";
                        stmt.executeUpdate(sql);
                        System.out.println("Inserted Succesfully");

                        sql = "update book set availability=" + (temp_avail - 1)
                                + " where Book_ID='" + temp_bookid + "';";
                        stmt.executeUpdate(sql);

                        bstu_t.setText("");
                        book_t.setText("");
                        issuedate_t.setText("");
                        duedate_t.setText("");
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    System.out.println("Book issued successfully");
                } else {
                    System.out.println("Book issue unsuccesful");
                }

                // Execute statements

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {
                System.out.println("Book issue unsuccesful");
                System.out.print(b.getMessage());
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception b) {
                    }
                }
            }
            issue_book.setVisible(false);
        }
        // book issue

        // new Student
        if (e.getSource() == newStudent) {
            stud_form.setVisible(true);
            issue_book.setVisible(false);
            new_book.setVisible(false);
            return_book.setVisible(false);
            stati.setVisible(false);

        }
        if (e.getSource() == save_login) {
            String stu_tString = stu_t.getText();
            String stuname_tString = stuname_t.getText();
            String stubrn_tString = stubrn_t.getText();

            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                // Execute statements
                String sql = "insert into student values(" + stu_tString + ",'" + stuname_tString + "','"
                        + stubrn_tString
                        + "');";
                stmt.executeUpdate(sql);
                System.out.println("Inserted Succesfully");

                stu_t.setText("");
                stuname_t.setText("");
                stubrn_t.setText("");

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {

                System.out.print(b.getMessage());
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception b) {
                    }
                }
            }
            stud_form.setVisible(false);
        }
        // end new Student

        // new book
        if (e.getSource() == newBook) {
            stud_form.setVisible(false);
            issue_book.setVisible(false);
            new_book.setVisible(true);
            return_book.setVisible(false);
            stati.setVisible(false);


        }
        if (e.getSource() == save_book) {
            String bid_tString = bid_t.getText();
            String bname_tString = bname_t.getText();
            String pub_tString = pub_t.getText();
            String pubdate_tString = pubdate_t.getText();
            String categ_tString = categ_t.getText();
            String no_of_copy_tString = no_of_copy_t.getText();

            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                // Execute statements
                String sql = "insert into book values(" + bid_tString + ",'" + bname_tString + "','"
                        + pub_tString + "','" + pubdate_tString + "','" + categ_tString + "'," + no_of_copy_tString
                        + ","
                        + no_of_copy_tString
                        + ");";
                stmt.executeUpdate(sql);
                System.out.println("Inserted Succesfully");

                bid_t.setText("");
                bname_t.setText("");
                pub_t.setText("");
                pubdate_t.setText("");
                categ_t.setText("");
                no_of_copy_t.setText("");

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {

                System.out.print(b.getMessage());
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception b) {
                    }
                }
            }
            new_book.setVisible(false);
        }
        // end new book

        // returnBook
        if (e.getSource() == returnBook) {
            stud_form.setVisible(false);
            issue_book.setVisible(false);
            new_book.setVisible(false);
            return_book.setVisible(true);
            stati.setVisible(false);


            // system date
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return_tString = dateFormat.format(date);
            return_t.setText(return_tString);
            // end system date

        }
        if (e.getSource() == returnbtn) {
            flag = 0;
            String sturet_tString = sturet_t.getText();
            String bookret_tString = bookret_t.getText();

            String temp_bookid = "", temp_stu_id = "";

            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                temp_conn = DriverManager.getConnection(url, userName, password);
                temp_stmt = temp_conn.createStatement();
                temp_rs = null;

                stmt.execute("select * from book_issue");
                rs = stmt.getResultSet();
                while (rs.next()) {
                    temp_bookid = rs.getString("Book_ID");
                    temp_stu_id = rs.getString("Student_ID");

                    if (bookret_tString.equals(temp_bookid) && sturet_tString.equals(temp_stu_id)) {
                        String sql = "delete from book_issue where Student_ID=" + sturet_tString + ";";
                        stmt.executeUpdate(sql);
                        System.out.println("Deleted Succesfully");

                        // rs.first();
                        stmt = null;
                        bstu_t.setText("");
                        book_t.setText("");
                        issuedate_t.setText("");
                        duedate_t.setText("");
                        return_book.setVisible(false);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    System.out.println("Book returned successfully");

                    temp_stmt.execute("select * from book");
                    temp_rs = temp_stmt.getResultSet();
                    while (temp_rs.next()) {
                        temp_bookid = temp_rs.getString("Book_ID");
                        if (temp_bookid.equals(bookret_tString))
                            break;
                    }
                    int temp_avail = Integer.parseInt(temp_rs.getString("availability"));

                    String sql = "update book set availability=" + (temp_avail + 1)
                            + " where Book_ID='" + temp_bookid + "';";
                    temp_stmt.executeUpdate(sql);
                } else {
                    System.out.println("Book return unsuccesful");
                }

                // Execute statements

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {
                System.out.println("Book return unsuccesful");
                System.out.print(b.getMessage());
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                    temp_stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                        temp_conn.close();
                    } catch (Exception b) {
                    }
                }
            }

        }
        // end returnBook

        // statistics
        if(e.getSource()==statistics)
        {
            stud_form.setVisible(false);
            issue_book.setVisible(false);
            new_book.setVisible(false);
            return_book.setVisible(false);
            stati.setVisible(true);
            ArrayList<ArrayList<String>> x = new ArrayList<ArrayList<String>>();
            try {
                String userName = "root";
                String password = "root";
                String url = "jdbc:mysql://localhost:3306/test";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, userName, password);
                stmt = conn.createStatement();

                // Execute statements
                stmt.execute("select * from book");
                rs = stmt.getResultSet();

                String col[]={"Book_ID","Book_name","Category","Availability"};
                DefaultTableModel tableModel = new DefaultTableModel(col, 0);
                j = new JTable(tableModel);

                while (rs.next()) {
                    
                    String temp_bookid = rs.getString("Book_ID");
                    String temp_bookname=rs.getString("Book_name");
                    String category=rs.getString("Category");
                    String avail=rs.getString("Availability");

                    x.add(new ArrayList<String>(Arrays.asList(temp_bookid,temp_bookname,category,avail)));
                }
                for(int i = 0; i < x.size(); i++){
                    for(int j = 0; j <x.get(i).size(); j++){
                        System.out.print(x.get(i).get(j));
                    }
                    System.out.println();
                }
                for(int i=0;i<x.size();i++)
                {
                    tableModel.addRow(x.get(i));
                }

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            } catch (Exception b) {

                System.out.print(b.getMessage());
                System.err.println("Cannot connect to database server");
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) {
                    }
                    stmt = null;
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception b) {
                    }
                }
            }

        }
        // end statistics

    }
}

public class Index {

    public static void main(String args[]) {

        new login();

    }
}