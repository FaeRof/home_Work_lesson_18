package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String username = "root";
        String password = "gomeloboy8394";
        String url = "jdbc:mysql://localhost:3306/my_schema";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Scanner scanner = new Scanner(System.in);
        ResultSet resultSet=null;
        try (Connection connection = DriverManager.getConnection(url, username, password);
          Statement statement = connection.createStatement()) {

            System.out.println("\nPlease select operation by number: \n 1.Create a book database on this computer " +
                    "\n 2.Delete old and create new book database \n 3.Work with what is in the book database ");
            int x = Integer.parseInt(scanner.nextLine());
            if (x<4){
            switch (x) {


                case (1):
                    statement.execute("CREATE TABLE if not exists book (id MEDIUMINT not null  auto_increment," +
                            " name char(100) not null,page int DEFAULT 0,publication DATE,author char(100) not null, PRIMARY KEY (id));");
                    statement.execute("insert into book (name,page, publication,author) " +
                            "values" +
                            "('Inferno', 544, {d '2013-05-14'},'Dan Brown')," +
                            "('Solomon Kane',432,{d '1929-01-10'},'Robert E. Howard')," +
                            "('Lord of the Rings',1186,{d '1954-07-29'},'J.R.R.Tolkien')," +
                            "('Harry Potter and the Sorcerer`s Stone',336,{d '1997-06-26'},'Joanne Rowling')," +
                            "('And Then There Were None',256,{d '1939-10-16'},'Agatha Christie')," +
                            "('Twenty Thousand Leagues Under the Sea',244,{d '1870-06-20'},'Jules Verne')," +
                            "('Don Quixote',863,{d '1605-01-01'},'Miguel de Cervantes')," +
                            "('The Divine Comedy',798,{d '1472-04-11'},'Dante Alighieri')");
                    resultSet = statement.executeQuery("select * from book");
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        int page = resultSet.getInt(3);
                        String publication = resultSet.getString(4);
                        String author = resultSet.getString(5);
                        System.out.printf("%d. %s - %d.pages / %s - %s \n", id, name, page, publication, author);
                    }
                    break;
                case (2):
                    statement.execute("drop table if exists book");
                    statement.execute("CREATE TABLE if not exists book (id MEDIUMINT not null  auto_increment," +
                            " name char(100) not null,page int DEFAULT 0,publication DATE,author char(100) not null, PRIMARY KEY (id));");
                    statement.execute("insert into book (name,page, publication,author) " +
                            "values" +
                            "('Inferno', 544, {d '2013-05-14'},'Dan Brown')," +
                            "('Solomon Kane',432,{d '1929-01-10'},'Robert E. Howard')," +
                            "('Lord of the Rings',1186,{d '1954-07-29'},'J.R.R.Tolkien')," +
                            "('Harry Potter and the Sorcerer`s Stone',336,{d '1997-06-26'},'Joanne Rowling')," +
                            "('And Then There Were None',256,{d '1939-10-16'},'Agatha Christie')," +
                            "('Twenty Thousand Leagues Under the Sea',244,{d '1870-06-20'},'Jules Verne')," +
                            "('Don Quixote',863,{d '1605-01-01'},'Miguel de Cervantes')," +
                            "('The Divine Comedy',798,{d '1472-04-11'},'Dante Alighieri')");
                    resultSet = statement.executeQuery("select * from book");
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        int page = resultSet.getInt(3);
                        String publication = resultSet.getString(4);
                        String author = resultSet.getString(5);
                        System.out.printf("%d. %s - %d.pages / %s - %s \n", id, name, page, publication, author);
                    }
                    break;
                case (3):
                    resultSet = statement.executeQuery("select * from book");
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        int page = resultSet.getInt(3);
                        String publication = resultSet.getString(4);
                        String author = resultSet.getString(5);
                        System.out.printf("%d. %s - %d.pages / %s - %s \n", id, name, page, publication, author);
                    }
                    break;

            }
            }else if (x>=4){
                System.out.println("Unknown operation. Please, try again.");
                return;
            }




//
//            BufferedImage image = ImageIO.read(new File("F://Новая папка/JDBS/cover/book2.jpg"));
//            Blob blob = connection.createBlob();
//            try(OutputStream outputStream =blob.setBinaryStream(1)){
//                ImageIO.write(image,"jpg",outputStream);
//            }
//            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO book (name,page, img) VALUES (?,?,?)");
//            preparedStatement.setString(1,"Inferno");
//            preparedStatement.setInt(2,544);
//            preparedStatement.setBlob(3,blob);
//            preparedStatement.execute();


            String bookName;
            System.out.println("\nConnected database successfully...");


            System.out.println("\nPlease select operation by number: \n 1.Create \n 2.Update \n 3.Read \n 4.Delete ");
            int work = Integer.parseInt(scanner.nextLine());
            switch (work) {
                case (1):

                    /**
                     ************************ Create *********************************
                     */

                    System.out.println("\nPlease add info about new book by Enter: " +
                            "\n 1.name " +
                            "\n 2.page " +
                            "\n 3.publication " +
                            "\n 4.author ");
                    String name = scanner.nextLine();
                    int page = Integer.parseInt(scanner.nextLine());
                    String publication = scanner.nextLine();
                    String author = scanner.nextLine();

                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT book (name, page, publication, author)" +
                            "VALUES (?, ?, ?, ?)");

                    System.out.println("\n\n Trying to add new book... ");

                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, page);
                    preparedStatement.setString(3, publication);
                    preparedStatement.setString(4, author);

                    preparedStatement.executeUpdate();

                    System.out.println("\n book was added successfully! ");
                    break;

                case (2):

                    /**
                     ************************ Update *********************************
                     */
                    System.out.println("\nEditing modes(select by number): " +
                            "\n 1.Write the new name referring to the old name " +
                            "\n 2.Write a page by book name" +
                            "\n 3.Write a publication by book name" +
                            "\n 4.Write a author by book name");
                    int updateIndex = Integer.parseInt(scanner.nextLine());

                    switch (updateIndex) {

                        case (1):
                            System.out.println("You have selected the mode: 1.Write the new name referring to the old name " +
                                    "\nPlease, enter the name of the book you want to change");
                            bookName = scanner.nextLine();
                            System.out.println("Please, enter the new name of the book you want to change");
                            String newBookName = scanner.nextLine();

                            preparedStatement = connection.prepareStatement("UPDATE book " +
                                    "SET name = ? " +
                                    "WHERE name LIKE ?");

                            System.out.println("\n\n Trying to set new book name... ");
                            preparedStatement.setString(1, newBookName);
                            preparedStatement.setString(2, bookName);
                            preparedStatement.executeUpdate();
                            System.out.println("\n the name of the book was set successfully! ");
                            break;

                        case (2):
                            System.out.println("You have selected the mode: 2.Write a page by book name " +
                                    "\nPlease, enter the name of the book you want to change");
                            bookName = scanner.nextLine();
                            System.out.println("Please, enter the new page");
                            String newPage = scanner.nextLine();

                            preparedStatement = connection.prepareStatement("UPDATE book " +
                                    "SET page = ? " +
                                    "WHERE name LIKE ?");

                            System.out.println("\n\n Trying to set new page... ");
                            preparedStatement.setString(1, newPage);
                            preparedStatement.setString(2, bookName);
                            preparedStatement.executeUpdate();
                            System.out.println("\n page was set successfully! ");
                            break;
                        case (3):
                            System.out.println("You have selected the mode: 3.Write a publication by book name " +
                                    "\nPlease, enter the name of the book you want to change");
                            bookName = scanner.nextLine();
                            System.out.println("Please, enter the new publication");
                            String newPublication = scanner.nextLine();

                            preparedStatement = connection.prepareStatement("UPDATE book " +
                                    "SET publication = ? " +
                                    "WHERE name LIKE ?");

                            System.out.println("\n\n Trying to set new publication... ");
                            preparedStatement.setString(1, newPublication);
                            preparedStatement.setString(2, bookName);
                            preparedStatement.executeUpdate();
                            System.out.println("\n publication was set successfully! ");
                            break;
                        case (4):
                            System.out.println("You have selected the mode: 4.Write a author by book name " +
                                    "\nPlease, enter the name of the book you want to change");
                            bookName = scanner.nextLine();
                            System.out.println("Please, enter the new author of the book " + bookName);
                            String newAuthor = scanner.nextLine();

                            preparedStatement = connection.prepareStatement("UPDATE book " +
                                    "SET author = ? " +
                                    "WHERE name LIKE ?");

                            System.out.println("\n\n Trying to set new author... ");
                            preparedStatement.setString(1, newAuthor);
                            preparedStatement.setString(2, bookName);
                            preparedStatement.executeUpdate();
                            System.out.println("\n author was set successfully! ");
                            break;
                        default:
                            System.out.println("Unknown index. Please try again");
                    }

                    break;

                case (3):

                    /**
                     ************************ Read *********************************
                     */
                    System.out.println("\nReading modes(select by number): " +
                            "\n 1.Read full table book: " +
                            "\n 2.Read books and page more than 'X' (range of page)");
                    int readIndex = Integer.parseInt(scanner.nextLine());

                    switch (readIndex) {
                        case (1):
                            System.out.println("You have selected the mode: 1.Read full table book: ");
                            String sqlReadAll = "SELECT *" +
                                    "FROM book ";

                            resultSet = statement.executeQuery(sqlReadAll);

                            while (resultSet.next()) {
                                String nameR = resultSet.getString("name");
                                int pageR = resultSet.getInt("page");
                                String publicationR = resultSet.getString("publication");
                                String authorR = resultSet.getString("author");
                                System.out.println("Name: " + nameR + ", " +
                                        " Page: " + pageR + ", " +
                                        " Publication: " + publicationR + ", " +
                                        " Author: " + authorR + ".");
                            }
                            break;
                        case (2):
                            System.out.println("You have selected the mode: 2.Read books and page more than 'X'(range of page): " +

                                    "\nPlease indicate the low limit of the page ");
                            int pageLowLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("Please indicate the top limit of the page ");
                            int pageTopLimit = Integer.parseInt(scanner.nextLine());

                            preparedStatement = connection.prepareStatement("SELECT Name, page " +
                                    "FROM book " +
                                    "WHERE page BETWEEN ? AND ? " +
                                    "ORDER BY page DESC ");

                            preparedStatement.setInt(1, pageLowLimit);
                            preparedStatement.setInt(2, pageTopLimit);
                            resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) {
                                String nameR2 = resultSet.getString("name");
                                int pageR2 = resultSet.getInt("Page");
                                System.out.println("Name: " + nameR2 + ", " +
                                        " Page: " + pageR2 + ".");
                            }
                            break;
                        default:
                            System.out.println("Unknown index. Please, try again.");
                    }
                            break;


                    case(4):
                        /**
                         ************************ Delete *********************************
                         */
                        System.out.println("\nDeleting modes(select by number): " +
                                "\n 1.Delete by name: " +
                                "\n 2.Delete book by range of page  ");

                        int deleteIndex =  Integer.parseInt(scanner.nextLine());
                        switch (deleteIndex){
                            case (1):
                                System.out.println("You have selected the mode: 1.Delete by name: " +
                                        "\nPlease indicate the book name ");
                                String deleteName = scanner.nextLine();

                                preparedStatement = connection.prepareStatement("DELETE FROM book " +
                                        "WHERE name = ?");
                                System.out.println("\n Trying to delete... ");
                                preparedStatement.setString(1, deleteName);
                                preparedStatement.executeUpdate();
                                System.out.println("\n book was deleted successfully! ");
                                break;
                            case (2):
                                System.out.println("You have selected the mode: 2.Delete books by range of page " +
                                        "\nPlease indicate the low limit of the page ");
                                String deleteLowRangePage = scanner.nextLine();
                                System.out.println("Please indicate the top limit of the page ");
                                String deleteTopRangePage = scanner.nextLine();

                                preparedStatement = connection.prepareStatement("DELETE FROM book " +
                                        "WHERE page BETWEEN ? AND ?");
                                System.out.println("\n Trying to delete... ");
                                preparedStatement.setString(1, deleteLowRangePage);
                                preparedStatement.setString(2, deleteTopRangePage);
                                preparedStatement.executeUpdate();
                                System.out.println("\n books were deleted successfully! ");
                                break;
                            default: System.out.println("Unknown index. Please, try again");
                        }
                        break;
                    default:
                        System.out.println("Unknown operation. Please, try again.");

                        connection.close();
                        statement.close();
                }

        }scanner.close();
        resultSet.close();
    }

}
