 <%@ include file="entete.jsp" %>
 		<div class="card-panel teal lighten-1"><center> <span class="white-text text-darken-2">
         	<h5><b> Université Paris Dauphine </b> <br>
         		Examens collaboratifs</h5></span></center>
        </div>
         
         <div class="row">
         <br>
           <form action="ReponseLibreServlet" method="post">
           <div class="col s10  offset-s1">
                <center>    <h5 class="light grey-text">Réponse à une question libre</h5></center>
            	<div class="card small"><br/>
                <center><span class="red-text text-darken-2">${erreur}</span></center>
                
                <br>
                <div class="row">
       			   <div class="input-field col s10 offset-s1">
            	   		<textarea id="textarea2" name="textarea2" class="materialize-textarea" data-length="120"></textarea>
            			<label for="textarea2">Quelle est la profession de Tom Cruise ? </label>
          		   </div>
        		</div>
				<br>
                  
                  <div class="col s4  offset-s4">
                       <button class="btn waves-effect waves-light" type="submit" name="action">Valider<i class="material-icons right">send</i></button>
                  </div>
                  
              </div>
             </div>
           </div>
         </div>
         </form>
               
<%@ include file="piedPage.jsp" %>