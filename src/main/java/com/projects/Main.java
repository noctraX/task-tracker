package com.projects;

// IMPORTS

// Args Var

public class Main {
    public static void main(String[] args){
        int args_length = args.length;

        // ERRORS HANDLING

        if(args_length == 0 || args_length > 3){
            usage();
        }else{
            switch(args[0]){
                case "add":
                    Commands.addCommand(args);
                    break;

                case "update":
                    Commands.updateCommand(args);
                    break;

                case "delete":
                    Commands.deleteCommand(args);
                    break;

                case "mark-in-progress":
                    Commands.markInProgressCommand(args);
                    break;

                case "mark-done":
                    Commands.markDoneCommand(args);
                    break;

                case "list":
                    Commands.listCommand(args);
                    break;

                default:
                    System.out.println("Unknown command. ");
                    usage();
                    break;
            }
        }
    }

    public static int usage(){
        System.out.println("""
                    Usage:
                        task-tracker add "<task description>"
                        task-tracker update <id> "<new task description>"
                        task-tracker delete <id>
                        task-tracker mark-in-progress <id>
                        task-tracker mark-done <id>
                        task-tracker list
                        task-tracker list <status>
                    
                    Commands:
                        add               Add a new task
                        update            Update an existing task by ID
                        delete            Delete a task by ID
                        mark-in-progress   Mark a task as in progress
                        mark-done          Mark a task as done
                        list               List all tasks or filter by status
                  
                    Statuses:
                        todo
                        in-progress
                        done
                  
                    Examples:
                        task-tracker add "Buy groceries"
                        task-tracker update 1 "Buy groceries and cook dinner"
                        task-tracker delete 1
                        task-tracker mark-in-progress 1
                        task-tracker mark-done 1
                        task-tracker list
                        task-tracker list done
                        task-tracker list todo
                        task-tracker list in-progress
                    """);
        return 1;
    }
}