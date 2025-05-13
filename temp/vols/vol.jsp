<%@page import="mg.itu.entity.*"%>
<%@page import="mg.itu.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.exception.ValidatorException" %>
<%
ValidatorException ve = (ValidatorException)request.getAttribute("validation");
List<Avion> avions = (List<Avion>) request.getAttribute("avions");
List<VilleDesservie> villes = (List<VilleDesservie>) request.getAttribute("villes");
List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");
%>

<jsp:include page="../inc/header.jsp" />
<!-- Container fluid  -->
<!-- ============================================================== -->
<div class="container-fluid">
    <!-- ============================================================== -->
    <!-- Start Page Content -->
    <!-- ============================================================== -->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Formulaire de Programmation de Vol</h4>
                    <!-- Formulaire pour programmer un vol -->
                    <form action="<%= request.getContextPath()%>/vols" method="post">
                        <div class="form-group">
                            <label for="departureDate">Date de départ</label>
                            <input type="date" class="form-control" id="departureDate" name="vol.dtDepart" required>
                            <!-- Message d'erreur spécifique pour la date -->
                            <% if (!ve.getInputError("vol.dtDepart").isEmpty()) { %>
                            <div class="text-danger" id="departureDateError">
                                Veuillez sélectionner une date de départ valide.
                            </div>
                            <% } %>
                        </div>
                    
                        <div class="form-group">
                            <label for="departureTime">Heure de départ</label>
                            <input type="time" class="form-control" id="departureTime" name="vol.heureDepart" required>
                            <!-- Message d'erreur spécifique pour l'heure -->
                            <% if (!ve.getInputError("vol.heureDepart").isEmpty()) { %>
                            <div class="text-danger" id="departureTimeError">
                                Veuillez entrer une heure de départ valide.
                            </div>
                            <% } %>
                        </div>
                    
                        <div class="form-group">
                            <label for="duree">Durée de vol</label>
                            <input type="time" value="00:00" class="form-control" id="duree" name="vol.duree" required>
                            <!-- Message d'erreur spécifique pour la durée -->
                            <% if (!ve.getInputError("vol.duree").isEmpty()) { %>
                            <div class="text-danger" id="dureeError">
                                Veuillez entrer une durée de vol valide.
                            </div>
                            <% } %>
                        </div>
                    
                        <div class="form-group">
                            <label for="departureLocation">Lieu de départ</label>
                            <select class="form-control" id="departureLocation" name="vol.idVilleDepart" required>
                                <% for(VilleDesservie a: villes){ %>
                                    <option value="<%= a.getId() %>"><%= a.getVille() %></option>
                                <% } %>
                            </select>
                            <!-- Message d'erreur spécifique pour le lieu de départ -->
                            <% if (!ve.getInputError("vol.idVilleDepart").isEmpty()) { %>
                            <div class="text-danger" id="departureLocationError">
                                Veuillez sélectionner un lieu de départ.
                            </div>
                            <% } %>
                        </div>
                    
                        <div class="form-group">
                            <label for="destination">Destination</label>
                            <select class="form-control" id="destination" name="vol.idVilleArrive" required>
                                <% for(VilleDesservie a: villes){ %>
                                    <option value="<%= a.getId() %>"><%= a.getVille() %></option>
                                <% } %>
                            </select>
                            <!-- Message d'erreur spécifique pour la destination -->
                            <% if (!ve.getInputError("vol.idVilleArrive").isEmpty()) { %>
                            <div class="text-danger" id="destinationError">
                                Veuillez sélectionner une destination.
                            </div>
                            <% } %>
                        </div>
                    
                        <div class="form-group">
                            <label for="aircraft">Avion</label>
                            <select name="vol.idAvion" id="aircraft" class="form-control" required>
                                <% for(Avion a: avions){ %>
                                    <option value="<%= a.getId() %>"><%= a.getModele() %></option>
                                <% } %>
                            </select>
                            <% if (!ve.getInputError("vol.idAvion").isEmpty()) { %>
                                <div class="text-danger" id="aircraftError">
                                    Veuillez sélectionner un avion.
                                </div>
                            <% } %>
                        </div>

                        <div class="form-row row">
                            <% for(TypeSiege a: typeSieges){ %>
                            <div class="form-group col-md-6">
                                <label for="aircraft"><%= a.getLibelle() %></label>
                                <input type="hidden" name="idTS[]" value="<%= a.getId() %>">
                                <input type="number" min="0.0" value="0.0" name="prixs[]" id="aircraft" class="form-control">
                            </div>
                            <% } %>
                        </div>

                        <button type="submit" class="btn btn-primary">Programmer le vol</button>
                    </form>                    
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End Page Content -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Right sidebar -->
    <!-- ============================================================== -->
    <!-- .right-sidebar -->
    <!-- ============================================================== -->
    <!-- End Right sidebar -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Container fluid  -->
<!-- ============================================================== -->
<jsp:include page="../inc/footer.jsp" />
