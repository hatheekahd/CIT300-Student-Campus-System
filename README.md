# CIT300 Student Record and Campus Route Management System

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1
**Language:** Java (console application, Eclipse / VS Code)

## Project Description
A menu-driven Java console application that manages university student records and campus routes. It demonstrates the practical use of linked lists, stacks, queues, binary search trees, hashing and graphs.

## Group Members and Contributions

| Member | Name | Student ID | Assigned Responsibility | Individual Contribution |
|---|---|---|---|---|
| Member 1 | MAH. Hatheek Ahamed | 23DA2-1128 | Linked list and student-record management | `Student` model class, `StudentLinkedList` (add, update, delete, search, display), `Main.java` menu integration, repository setup |
| Member 2 | MAM. Afkah | 23DA2-1026 | Stack and queue | `ActionStack` (recent actions / undo history), `ServiceQueue` (service requests in order of arrival) |
| Member 3 | TM. Rahmy | 23DA2-0641 | BST and hashing | `StudentBST` (records organised by Student ID), `StudentHashTable` (separate chaining for fast ID search) |
| Member 4 | SM. Nasmir | 23DA2-1039 | Graph | `CampusGraph` (adjacency list, add/remove locations and roads, display network, BFS and DFS) |

**All members:** integration, validation, testing, debugging, documentation and GitHub collaboration.

## Data Structures Used

| Requirement | Class | Package |
|---|---|---|
| Linked list (student records) | `StudentLinkedList` | `linkedlist` |
| Stack (recent actions) | `ActionStack` | `stackqueue` |
| Queue (service requests) | `ServiceQueue` | `stackqueue` |
| BST (organise/search by Student ID) | `StudentBST` | `tree` |
| Hashing (Student ID search) | `StudentHashTable` | `hashing` |
| Graph (campus network, BFS/DFS) | `CampusGraph` | `graph` |

## Project Structure

```
src/
 ├─ model/       Student.java
 ├─ linkedlist/  StudentLinkedList.java
 ├─ stackqueue/  ActionStack.java, ServiceQueue.java
 ├─ tree/        StudentBST.java
 ├─ hashing/     StudentHashTable.java
 ├─ graph/       CampusGraph.java
 └─ app/         Main.java (menu), test classes
```

## How to Run
1. Clone the repository:
   `git clone https://github.com/hatheekahd/CIT300-Student-Campus-System.git`
2. Open the project in Eclipse (File > Import > Existing Projects into Workspace) or VS Code.
3. Run `src/app/Main.java`.
4. Use the console menu (options 1 to 16).

## Menu Options
1. Add Student Record
2. Update Student Record
3. Delete Student Record
4. Display All Records using Linked List
5. Add Service Request to Queue
6. Process Next Service Request
7. Display Recent Actions using Stack
8. Display Students using BST
9. Search Student using Hashing
10. Add Campus Location
11. Remove Campus Location
12. Add Campus Connection/Road
13. Remove Campus Connection/Road
14. Display Campus Connections
15. Traverse Campus Locations using BFS or DFS
16. Exit

## Features
- Add, update, delete, search and display student records
- Input validation: invalid marks (0 to 100), duplicate Student IDs, missing records, empty input
- Service request queue (first come, first served)
- Recent actions history using a stack
- Student records organised in a BST (sorted by Student ID)
- Fast Student ID search using a hash table
- Campus graph (adjacency list): add/remove locations and roads, display connections, BFS and DFS traversal
- Handling of duplicate locations, missing locations and unavailable connections

## Git Workflow
Each member worked on their own branch and merged into `main` through pull requests:

| Branch | Member |
|---|---|
| `feature/linked-list` | MAH. Hatheek Ahamed |
| `feature/stack-queue` | MAM. Afkah |
| `feature/bst-hashing` | TM. Rahmy |
| `feature/graph` | SM. Nasmir |
| `feature/main-menu` | MAH. Hatheek Ahamed |
| `feature/readme` | MAH. Hatheek Ahamed |