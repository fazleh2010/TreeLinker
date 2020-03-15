<!DOCTYPE html>
<html>
    <body>

        <h1>Question Answering System</h1>

        <?php
        $text = shell_exec("java -jar /Users/elahi/NetBeansProjects/new/final/TreeLinker/target/TreeLinker-1.1-SNAPSHOT.jar");
        $myArray = explode(PHP_EOL, $text);

        foreach ($myArray as $value) {
            echo "$value<br>";
        }

        $book = array(// associative array
            "title" => "Test: The Definitive Guide",
            "author" => "David Flanagan",
            "edition" => 6
        );

// can build string here instead of in script tags if you like
        $book_keys = '["' . implode('", "', array_keys($book)) . '"]';
        
        ?>
        <p id="demo"></p>

        <script type="text/javascript">
            var book_keys = <?php echo $book_keys; ?>; // output php string here
// ["title", "author", "edition"];

            var book = <?php echo '["' . implode('", "', $book) . '"]'; ?>;
            document.getElementById("demo").innerHTML =book;
// ["JavaScript: The Definitive Guide", "David Flanagan", "6"];
        </script>
        

    </body>
</html>