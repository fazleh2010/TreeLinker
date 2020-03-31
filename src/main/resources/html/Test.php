<!DOCTYPE html>
<html>

    <head>
        <style>
            .button {
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }
        </style>
    </head>

    <body>

        <h2>Ask a question</h2>

        <?php
        $text = shell_exec("java -jar /home/elahi/NetBeansProjects/TreeLinker/target/TreeLinker-1.4.1-SNAPSHOT.jar");
        $myArray = explode(PHP_EOL, $text);

        $questions = array();
        $answers = array();


        $x = 0;

        foreach ($myArray as $value) {
            list($ques, $ans) = array_pad(explode("=", $value), 2, null);
            $questions[$x] = $ques;
            $answers[$x] = $ans;
            echo $ques.'  '.$ans.'  '.$x.'<br/>'; 
            $x++;
        }

        $questions = '["' . implode('", "', $questions) . '"]';
        $answers = '["' . implode('", "', $answers) . '"]';
        ?>


    </body>
</html>