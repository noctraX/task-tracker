package com.projects;

// Jackson
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

// File
import java.io.File;

// List
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

// program usage()
import static com.projects.Main.usage;

public class Commands {

    // ObjectMapper
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    // *ObjectMapper

    // Generate id function
    public static int generateId(List<Task> taskList){
        Random r = new Random();

        boolean unique = false;
        int id = 10000;
        while(!unique){
            boolean exists = false;
            id = r.nextInt(10000, 1000000);

            for(Task task:taskList){
                if(id == task.getId()){
                    exists = true;
                    break;
                }
            }
            if(!exists){
                unique = true;
            }
        }

        return id;
    }
    // *Generate id function

    // Verify id points to an actual task
    public static boolean verifyId(int id,List<Task> taskList){
        for(Task task: taskList){
            if(id == task.getId()){
                return true;
            }
        }
        return false;
    }

    public static void addCommand(String[] args) {
        if(args.length != 2) {
            usage();
            return;
        }

        /* Taking existing data from file.json */
        List<Task> taskList = new ArrayList<>();

        File file = new File("file.json");

        try{
            // Verifying file exists
            if(!file.exists()) {
                file.createNewFile();
            }

            // Moving JSON data from file to list of tasks

            if(file.length() > 0){
                taskList = mapper.readValue(
                        file,
                        new TypeReference<List<Task>>() {}
                );
            }

            /* Add the new task to the list */
            // Random Id generated
            int id = generateId(taskList);
            // Task
            Task task = new Task(id, args[1], TaskStatus.TODO);
            taskList.add(task);

            // Save the list to the file.json file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("file.json"), taskList);

            // Task Added
            System.out.println("Task added. ");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateCommand(String[] args) {

        /* ERRORS HANDLING */

        // Correct Length
        if(args.length != 3){
            usage();
            return;
        }

        // Verifying the id is an actual integer.
        int id;
        try {
            id = Integer.parseInt(args[1]);
        }catch(NumberFormatException e) {
            System.out.println("The id must exist, must be an integer and point to an existing task. ");
            return;
        }

        // MAIN COMMANDS

        File file = new File("file.json");

        try{
            // Verifying if the file doesn't exist or is empty
            if(!file.exists() || file.length() == 0){
                System.out.println("You have not recorded a task yet. ");
                return;
            }

            // Importing tasks from file.json to a list of tasks

            List<Task> taskList = mapper.readValue(
                    file,
                    new TypeReference<List<Task>>() {}
            );

            // Verifying the id actually points to an existing task.
            if(!verifyId(id, taskList)){
                System.out.println("The id must point to an existing task. ");
                return;
            }

            // Update Completed
            for(Task t: taskList){
                if (t.getId() == id) {
                    t.setDescription(args[2]);
                    break;
                }
            }

            // Writing the updated data to file.json
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    file,
                    taskList
            );

            System.out.println("Task updated. ");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCommand(String[] args) {
        /* ERRORS HANDLING */

        // Correct Length
        if(args.length != 2){
            usage();
            return;
        }

        // Verifying the id is an actual integer.
        int id;
        try {
            id = Integer.parseInt(args[1]);
        }catch(NumberFormatException e) {
            System.out.println("The id must exist, must be an integer and point to an existing task. ");
            return;
        }

        // MAIN COMMANDS

        File file = new File("file.json");

        try{
            // Verifying if the file doesn't exist or is empty
            if(!file.exists() || file.length() == 0){
                System.out.println("You have not recorded a task yet. ");
                return;
            }

            // Importing tasks from file.json to a list of tasks

            List<Task> taskList = mapper.readValue(
                    file,
                    new TypeReference<List<Task>>() {}
            );

            // Verifying the id actually points to an existing task.
            if(!verifyId(id, taskList)){
                System.out.println("The id must point to an existing task. ");
                return;
            }

            // Delete Completed
            taskList.removeIf(t -> t.getId() == id);

            // Writing the updated data to file.json
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    file,
                    taskList
            );

            System.out.println("Task deleted. ");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void markInProgressCommand(String[] args) {
        /* ERRORS HANDLING */

        // Correct Length
        if(args.length != 2){
            usage();
            return;
        }

        // Verifying the id is an actual integer.
        int id;
        try {
            id = Integer.parseInt(args[1]);
        }catch(NumberFormatException e) {
            System.out.println("The id must exist, must be an integer and point to an existing task. ");
            return;
        }

        // MAIN COMMANDS

        File file = new File("file.json");

        try{
            // Verifying if the file doesn't exist or is empty
            if(!file.exists() || file.length() == 0){
                System.out.println("You have not recorded a task yet. ");
                return;
            }

            // Importing tasks from file.json to a list of tasks

            List<Task> taskList = mapper.readValue(
                    file,
                    new TypeReference<List<Task>>() {}
            );

            // Verifying the id actually points to an existing task.
            if(!verifyId(id, taskList)){
                System.out.println("The id must point to an existing task. ");
                return;
            }

            // Updating to in_progress
            for(Task t: taskList){
                if(t.getId() == id){
                    t.setStatus(TaskStatus.IN_PROGRESS);
                    break;
                }
            }

            // Writing the updated data to file.json
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    file,
                    taskList
            );

            System.out.println("Task updated to in_progress. ");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static void markDoneCommand(String[] args) {
        /* ERRORS HANDLING */

        // Correct Length
        if(args.length != 2){
            usage();
            return;
        }

        // Verifying the id is an actual integer.
        int id;
        try {
            id = Integer.parseInt(args[1]);
        }catch(NumberFormatException e) {
            System.out.println("The id must exist, must be an integer and point to an existing task. ");
            return;
        }

        // MAIN COMMANDS

        File file = new File("file.json");

        try{
            // Verifying if the file doesn't exist or is empty
            if(!file.exists() || file.length() == 0){
                System.out.println("You have not recorded a task yet. ");
                return;
            }

            // Importing tasks from file.json to a list of tasks

            List<Task> taskList = mapper.readValue(
                    file,
                    new TypeReference<List<Task>>() {}
            );

            // Verifying the id actually points to an existing task.
            if(!verifyId(id, taskList)){
                System.out.println("The id must point to an existing task. ");
                return;
            }

            // Updating to done
            for(Task t: taskList){
                if(t.getId() == id){
                    t.setStatus(TaskStatus.DONE);
                    break;
                }
            }

            // Writing the updated data to file.json
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    file,
                    taskList
            );

            System.out.println("Task updated to DONE. ");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void listCommand(String[] args) {
        /* ERRORS HANDLING */

        if(args.length == 1 || args.length == 2){

            File file = new File("file.json");

            try {

                // Verifying tasks exists
                if (!file.exists() || file.length() == 0) {
                    System.out.println("You have not recorded a task yet. ");
                    return;
                }

                // Importing file.json content
                List<Task> taskList = mapper.readValue(
                        file,
                        new TypeReference<List<Task>>() {}
                );

                if(args.length == 1) {
                    // Returning [ID] [STATUS] DESCRIPTION for every task
                    for(Task task:taskList){
                        System.out.printf(String.format("[ %-6s ] [ %-11s ] %s \n", task.getId(), task.getStatus(), task.getDescription()));
                    }
                }else{
                    // Verifying status is valid (TODO, DONE, IN_PROGRESS)
                    TaskStatus status;
                    try {
                        status = TaskStatus.valueOf(args[1].toUpperCase());
                    }catch (IllegalArgumentException e) {
                        System.out.println("Invalid task status. Valid statuses : [ todo, in_progress, done ]");
                        return;
                    }

                    for(Task task:taskList){
                       if(task.getStatus() == status){
                           System.out.printf("[ %-6s ] [ %s ] %s \n", task.getId(), task.getStatus(), task.getDescription());
                       }
                    }
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }else{
            usage();
        }
    }
}
