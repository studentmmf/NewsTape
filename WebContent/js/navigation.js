nav();


function nav() {

    var nav = document.getElementById("navigation");
    var aprev = document.createElement("a");
    var anext = document.createElement("a");
    anext.setAttribute("href", "#href");
    aprev.setAttribute("href", "#href");
    aprev.innerHTML = "Предыдущая";    
    anext.innerHTML =  "Следующая";
    var countOfPages = document.getElementById("pages").innerText;
    var start1 = document.getElementById("page").innerText;
    
    if(countOfPages != 1 && start1 != 1) {
    	nav.appendChild(aprev);
    }
    if(countOfPages != 1 && start1 != countOfPages) {
    	nav.appendChild(anext);
    }
    
    
    anext.onclick = function () {
    	var start = document.getElementById("page").innerText;
        console.log(start);
        start++;          
        $.post("readServlet", {start: start},  function(responseText) {

            $('#ajaxGetUserServletResponse').html(responseText);
            $("#page").html((start));
        })
        
       }
    aprev.onclick = function () {
    	var start = document.getElementById("page").innerText;
        console.log(start);
        start--;        
        $.post("readServlet", {start: start},  function(responseText) {

            $('#ajaxGetUserServletResponse').html(responseText);
            $("#page").html((start));
        })
        
       }
    
}