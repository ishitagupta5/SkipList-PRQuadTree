import java.util.regex.Pattern;
// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or unmodified.
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
// - I have not discussed coding details about this project with
// anyone other than the my partner, instructor, ACM/UPE tutors
// or the TAs assigned to this course.
// I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     */
    public CommandProcessor() {
        // I added the parameter myself
        data = new Database();
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions.
     * These actions are performed on specified objects and include insert,
     * remove,
     * regionsearch, search, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        if (command.equals("insert")) {
            String name = arr[1].trim();
            int x = Integer.parseInt(arr[2].trim());
            int y = Integer.parseInt(arr[3].trim());
            data.insert(new Point(name, x, y));

        }
        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove")) {
            // checks the number of white space delimited strings in the line
            int numParam = arr.length - 1;
            if (numParam == 1) {
                String nameToRemove = arr[1].trim();
                data.remove(nameToRemove);
            }
            else if (numParam == 2) {
                int x = Integer.parseInt(arr[1].trim());
                int y = Integer.parseInt(arr[2].trim());
                data.remove(x, y);
            }
        }

        // }
        else if (command.equals("regionsearch")) {
            // calls the regionsearch method for a set of coordinates
            // the string integers in the line will be trimmed of whitespace
            int x = Integer.parseInt(arr[1].trim());
            int y = Integer.parseInt(arr[2].trim());
            int w = Integer.parseInt(arr[3].trim());
            int h = Integer.parseInt(arr[4].trim());
            data.regionsearch(x, y, w, h);
        }
        else if (command.equals("search")) {
            // calls the search method for a name of object
            String name = arr[1].trim();
            data.search(name);
        }
        else if (command.equals("duplicates")) {
            data.duplicates();

        }
        else if (command.equals("dump")) {
            // calls the dump method for the database, takes no parameters
            // (see the dump() JavaDoc in the Database class for more
            // information)
            data.dump();
        }
        else {
        }
    }
}
