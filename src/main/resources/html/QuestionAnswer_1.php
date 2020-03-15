<!DOCTYPE html>
<html>
<body>

<h1>Question Answering System</h1>

<?php
$text = shell_exec("java -jar /Users/elahi/NetBeansProjects/new/final/TreeLinker/target/TreeLinker-1.1-SNAPSHOT.jar");
$list = explode("\n", $text);

print_r($list);

$myString = "Prepare yourself to be caught
You in the hood gettin' shot
We going throw hell of blows
got my whole frame froze";

$myArray = explode(PHP_EOL, $text);

foreach($myArray as $value){
  echo "$value<br>";
}

?>

</body>
</html>