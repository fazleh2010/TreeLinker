<!DOCTYPE html>
<html>
    <body>

        <h1>Question Answering System</h1>

        <?php
        $text = shell_exec("java -jar /Users/elahi/NetBeansProjects/new/final/TreeLinker/target/TreeLinker-1.1-SNAPSHOT.jar");
        $myArray = explode(PHP_EOL, $text);

        $arrayToDisplay = array();

        foreach ($myArray as $value) {
            $arrayToDisplay[$value] = "answer";
        }

        $questions = '["' . implode('", "', $arrayToDisplay) . '"]';

 

echo json_encode($arrayToDisplay);


        ?>
        <p id="demo"></p>
        
         <h4>Search terms</h4>
                        <!--Make sure the form has the autocomplete function switched off:-->
                        <form autocomplete="off" id="form_id"> 
                            <div class="autocomplete" style="width:300px;"> 
                                <input id="myInput" type="text" placeholder="Search.."> 
                            </div> 
                            <input id="submit" type="submit"> 
                        </form> 

        <script type="text/javascript">
           


            window.termUrls = new Map();
            window.termUrls = <?php echo json_encode($arrayToDisplay, JSON_FORCE_OBJECT); ?>;
            let arr = <?php echo $questions; ?>;
            
            //let arr = Array.from(termUrls.keys());
            document.getElementById("demo").innerHTML =window.termUrls;
            
            
            window.valueOfTextField = "";
            window.text = "";
            document.getElementById("myInput").style.borderColor = "blue";

            autocomplete(document.getElementById("myInput"), arr);


            function autocomplete(inp, arr) {
                /*the autocomplete function takes two arguments,
                 the text field element and an array of possible autocompleted values:*/
                var currentFocus;
                /*execute a function when someone writes in the text field:*/
                inp.addEventListener("input", function (e) {
                    var a, b, i, val = this.value;
                    /*close any already open lists of autocompleted values*/
                    closeAllLists();
                    if (!val) {
                        return false;
                    }
                    currentFocus = -1;
                    /*create a DIV element that will contain the items (values):*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append the DIV element as a child of the autocomplete container:*/
                    this.parentNode.appendChild(a);
                    /*for each item in the array...*/
                    for (i = 0; i < arr.length; i++) {
                        window.text = arr[i];
                        /*check if the item starts with the same letters as the text field value:*/
                        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                            /*create a DIV element for each matching element:*/
                            b = document.createElement("DIV");
                            /*make the matching letters bold:*/
                            b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                            b.innerHTML += arr[i].substr(val.length);
                            /*insert a input field that will hold the current array item's value:*/
                            b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                            /*execute a function when someone clicks on the item value (DIV element):*/
                            b.addEventListener("click", function (e) {
                                /*insert the value for the autocomplete text field:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                window.text = this.getElementsByTagName("input")[0].value;

                                /*close the list of autocompleted values,
                                 (or any other open lists of autocompleted values:*/
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                });
                /*execute a function presses a key on the keyboard:*/
                inp.addEventListener("keydown", function (e) {
                    var x = document.getElementById(this.id + "autocomplete-list");
                    if (x)
                        x = x.getElementsByTagName("div");
                    if (e.keyCode == 40) {
                        /*If the arrow DOWN key is pressed,
                         increase the currentFocus variable:*/
                        currentFocus++;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 38) { //up
                        /*If the arrow UP key is pressed,
                         decrease the currentFocus variable:*/
                        currentFocus--;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 13) {
                        /*If the ENTER key is pressed, prevent the form from being submitted,*/
                        e.preventDefault();
                        if (currentFocus > -1) {
                            /*and simulate a click on the "active" item:*/
                            if (x)
                                x[currentFocus].click();
                        }
                    }
                });
                function addActive(x) {
                    /*a function to classify an item as "active":*/
                    if (!x)
                        return false;
                    /*start by removing the "active" class on all items:*/
                    removeActive(x);
                    if (currentFocus >= x.length)
                        currentFocus = 0;
                    if (currentFocus < 0)
                        currentFocus = (x.length - 1);
                    /*add class "autocomplete-active":*/
                    x[currentFocus].classList.add("autocomplete-active");
                }
                function removeActive(x) {
                    /*a function to remove the "active" class from all autocomplete items:*/
                    for (var i = 0; i < x.length; i++) {
                        x[i].classList.remove("autocomplete-active");
                    }
                }
                function closeAllLists(elmnt) {
                    /*close all autocomplete lists in the document,
                     except the one passed as an argument:*/
                    var x = document.getElementsByClassName("autocomplete-items");
                    for (var i = 0; i < x.length; i++) {
                        if (elmnt != x[i] && elmnt != inp) {
                            x[i].parentNode.removeChild(x[i]);
                        }
                    }
                }
                document.addEventListener("click", function (e) {
                    //document.getElementById("myInput").value=window.valueOfTextField;
                    //document.getElementById('form_id').action = window.location.href = window.valueOfTextField; //Will set it
                    closeAllLists(e.target);
                });

                document.getElementById("form_id").addEventListener("submit", myFunction);
                function myFunction() {
                    window.valueOfTextField = window.termUrls.get(window.text);
                    if (document.getElementById("myInput").value == "") {
                        alert("search box is empty")
                    } else {
                        document.getElementById('form_id').action = window.location.href = window.valueOfTextField; //Will set it
                    }
                }


            }

        </script>


    </body>
</html>