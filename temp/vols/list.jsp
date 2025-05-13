<%@page import="mg.itu.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.dao.*"%>
<%
List<Avion> avions = (List<Avion>) request.getAttribute("avions");
List<Vol> vols = (List<Vol>) request.getAttribute("vols");
List<VilleDesservie> villes = (List<VilleDesservie>) request.getAttribute("villes");
%>

<jsp:include page="../inc/header.jsp" />
<!-- Container fluid -->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Filtrer les vols</h4>
                    <form action="<%= request.getContextPath()%>/vols" method="get">
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
                    <h4 class="card-title">Résultats de la recherche</h4>
                    <div class="table-responsive">
                        <table class="table user-table no-wrap">
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
                                            <a href="<%= request.getContextPath() %>/vols/details?id=<%= vol.getId() %>" class="btn btn-outline-info btn-sm mb-2" role="button">
                                                Voir plus
                                            </a>
                                        
                                            <!-- Bouton Modifier -->
                                            <a href="<%= request.getContextPath() %>/vols/update?id=<%= vol.getId() %>" class="btn btn-outline-primary btn-sm mb-2" role="button">
                                                Modifier
                                            </a>
                                        
                                            <!-- Bouton Supprimer -->
                                            <a href="<%= request.getContextPath() %>/vols/delete?id=<%= vol.getId() %>" class="btn btn-outline-danger btn-sm" role="button">
                                                Supprimer
                                            </a>
                                        </td>                                        
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../inc/footer.jsp" />
