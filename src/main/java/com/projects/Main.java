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
                        task-cli add "<task description>"
                        task-cli update <id> "<new task description>"
                        task-cli delete <id>
                        task-cli mark-in-progress <id>
                        task-cli mark-done <id>
                        task-cli list
                        task-cli list <status>
                    
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
                        task-cli add "Buy groceries"
                        task-cli update 1 "Buy groceries and cook dinner"
                        task-cli delete 1
                        task-cli mark-in-progress 1
                        task-cli mark-done 1
                        task-cli list
                        task-cli list done
                        task-cli list todo
                        task-cli list in-progress
                    """);
        return 1;
    }
}