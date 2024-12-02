-- Step 1: Create the table
CREATE TABLE tasks (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       due_date DATE,
                       status VARCHAR(50) CHECK (status IN ('Pending', 'Completed'))
);

-- Step 2: Insert 10 sample values into the table
INSERT INTO tasks (title, description, due_date, status) VALUES
                                                             ('Task 1', 'Complete the project report', '2024-12-10', 'Pending'),
                                                             ('Task 2', 'Review the codebase for bugs', '2024-12-12', 'Pending'),
                                                             ('Task 3', 'Prepare presentation slides', '2024-12-05', 'Completed'),
                                                             ('Task 4', 'Submit the assignment', '2024-12-08', 'Pending'),
                                                             ('Task 5', 'Organize team meeting', '2024-12-03', 'Completed'),
                                                             ('Task 6', 'Update project documentation', '2024-12-15', 'Pending'),
                                                             ('Task 7', 'Refactor the code for optimization', '2024-12-20', 'Pending'),
                                                             ('Task 8', 'Test the application thoroughly', '2024-12-14', 'Completed'),
                                                             ('Task 9', 'Check server performance', '2024-12-02', 'Pending'),
                                                             ('Task 10', 'Plan next sprint', '2024-12-09', 'Completed');