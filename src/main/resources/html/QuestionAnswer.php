<!DOCTYPE html>
<html>
<body>

<h1>Question Answering System</h1>

<?php
$text = shell_exec("java -jar /Users/elahi/NetBeansProjects/new/final/TreeLinker/target/TreeLinker-1.1-SNAPSHOT.jar");
$myArray = explode(PHP_EOL, $text);

foreach($myArray as $value){
  echo "$value<br>";
}

?>

</body>
</html>