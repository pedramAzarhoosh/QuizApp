<?php

// Attempt to connect to the database using XAMPP with a custom port
$connection = mysqli_connect("localhost", "root", "", "quiz_db", 3308);

// Check if the connection was successful
if (!$connection) {
    die("Connection failed: " . mysqli_connect_error());
}

// Prepare the SQL statement
$statement = $connection->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct_option` FROM `math_quiz`");

// Execute the statement
$statement->execute();

// Bind the result to variables
$statement->bind_result($question, $option1, $option2, $option3, $option4, $correct_option);

$question_array = array();

// Fetch data and store it in an array
while ($statement->fetch()) {
    $temp = array();
    $temp['question'] = $question;
    $temp['option1'] = $option1;
    $temp['option2'] = $option2;
    $temp['option3'] = $option3;
    $temp['option4'] = $option4;
    $temp['correct_option'] = $correct_option;

    array_push($question_array, $temp);
}

// Close the statement and connection
$statement->close();
$connection->close();

// Output the result in JSON format
echo json_encode($question_array);

?>
