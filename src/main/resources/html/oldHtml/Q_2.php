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
        
        $fruits = array('apple', 'orange', 'mango', 'banana', 'strawberry');
        ?>
        <h4>Search terms</h4>
        <form autocomplete="off" id="form_id"> 
            <div class="autocomplete" style="width:300px;"> 
                <input id="myInput" type="text" placeholder="Search.."> 
            </div> 
            <input id="submit" type="submit"> 
        </form> 
        <script type="text/javascript" src="js/tbx2rdf_atc_en.js"></script>


    </body>
</html>