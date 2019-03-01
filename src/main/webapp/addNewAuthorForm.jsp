 <%@ include file="entete.jsp" %>
 		<div class="card-panel teal lighten-1"><center> <span class="white-text text-darken-2">
         	<h5><b> Université Paris Dauphine </b> <br>
         		Examens collaboratifs</h5></span></center>
        </div>
         
         <div class="row">
           <form action="addNewAuthorServlet" method="post">
           <div class="col s6  offset-s3">
                <center>    <h5 class="light grey-text">Création d'un nouvel auteur</h5></center>
            	<div class="card"><br/>
                <center><span class="red-text text-darken-2">${erreur}</span></center>
                 <div class="row">
                 <div class="input-field col s6 offset-s3">
                 <i class="material-icons prefix">supervisor_account</i>     
                 	<input  id="mail" name="mail" type="text" required class="validate">
                 <label for="mail">Adresse mail</label>
                 </div> 
                 </div>
                
                <div class="row">
                <div class="input-field col s6 offset-s3">
                 <i class="material-icons prefix">lock</i>
                 <input id="password" name="password" type="password" required class="validate">
                 <label for="password">mot de passe</label>
                 </div>
                </div>  
                
                 <div class="row">
                <div class="input-field col s6 offset-s3">
                 <i class="material-icons prefix">lock</i>
                 <input id="confPassword" name="confPassword" type="password" required class="validate">
                 <label for="confPassword">Confirmation du mot de passe</label>
                 </div>
                </div>  
                
                 <div class = "row">
               <div class = "input-field col s12 offset-s3">
                 
                     <input id = "sexe" type = "radio" checked/>
                     <label>Homme</label>
                  
                  
                  
                     <input id = "sexe" type = "radio"/>
                     <label>Femme</label>
                 
               </div>
            </div>                
				<br>
              <div class="row"> 
                  <div class="col s6  offset-s4">
                       <button class="btn waves-effect waves-light" type="submit" name="action">Valider<i class="material-icons right">send</i></button>
                  </div>
              </div>
             </div>
           </div>
         </div>
         </form>
               
<%@ include file="piedPage.jsp" %>