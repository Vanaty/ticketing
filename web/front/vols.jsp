<%@page import="mg.itu.entity.*"%>
<%@page import="java.util.List"%>
<%
List<Avion> avions = (List<Avion>) request.getAttribute("avions");
List<Vol> vols = (List<Vol>) request.getAttribute("vols");
List<VilleDesservie> villes = (List<VilleDesservie>) request.getAttribute("villes");
%>
<jsp:include page="header.jsp" />    
<div class="container mt-4 content">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Filtrer les vols</h4>
            <form action="<%= request.getContextPath()%>" method="get">
                <!-- Dates -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label for="departureDateMin">Date de départ min</label>
                        <input type="date" class="form-control" id="departureDateMin" name="vol.dtDepartMin">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="departureDateMax">Date de départ max</label>
                        <input type="date" class="form-control" id="departureDateMax" name="vol.dtDepartMax">
                    </div>
                </div>

                <!-- Heures -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label for="departureTimeMin">Heure de départ min</label>
                        <input type="time" value="00:00" class="form-control" id="departureTimeMin" name="vol.heureDepartMin">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="departureTimeMax">Heure de départ max</label>
                        <input type="time" value="23:59" class="form-control" id="departureTimeMax" name="vol.heureDepartMax">
                    </div>
                </div>

                <!-- Durée -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label for="dureeMin">Durée de vol min</label>
                        <input type="time" value="00:00" class="form-control" id="dureeMin" name="vol.dureeMin">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="dureeMax">Durée de vol max</label>
                        <input type="time" value="23:59" class="form-control" id="dureeMax" name="vol.dureeMax">
                    </div>
                </div>

                <!-- Lieux -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label for="departureLocation">Lieu de départ</label>
                        <select class="form-control" id="departureLocation" name="vol.idVilleDepart">
                            <option value="">...</option>
                            <% for(VilleDesservie a: villes){ %>
                                <option value="<%= a.getId() %>"><%= a.getVille() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="destination">Destination</label>
                        <select class="form-control" id="destination" name="vol.idVilleArrive">
                            <option value="">...</option>
                            <% for(VilleDesservie a: villes){ %>
                                <option value="<%= a.getId() %>"><%= a.getVille() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <!-- Avion -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label for="aircraft">Avion</label>
                        <select name="vol.idAvion" id="aircraft" class="form-control">
                            <option value="">...</option>
                            <% for(Avion a: avions){ %>
                                <option value="<%= a.getId() %>"><%= a.getModele() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>

                <!-- Boutons -->
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary btn-block">Rechercher</button>
                    </div>
                    <div class="form-group col-md-6">
                        <button type="reset" class="btn btn-secondary btn-block">Réinitialiser</button>
                    </div>
                </div>
            </form>

            <hr />

            <h2 class="text-center mb-4">Liste des Vols</h2>
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Avion</th>
                        <th>Ville de départ</th>
                        <th>Ville d'arrivée</th>
                        <th>Date de départ</th>
                        <th>Heure de départ</th>
                        <th>Durée</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Vol vol: vols){ %>
                        <tr>
                            <td><%= vol.getId() %></td>
                            <td><%= vol.getAvion().getModele() %></td>
                            <td><%= vol.getVilleDepart().getVille() %></td>
                            <td><%= vol.getVilleArrive().getVille() %></td>
                            <td><%= vol.getDtDepart() %></td>
                            <td><%= vol.getHeureDepart() %></td>
                            <td><%= vol.getDuree() %></td>
                            <td class="d-flex flex-column">
                                <!-- Bouton Voir plus -->
                                <a href="<%= request.getContextPath() %>/reservation/creation?id=<%= vol.getId() %>" class="btn btn-outline-info btn-sm mb-2" role="button">
                                    Reservation
                                </a>
                            </td>                                        
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />