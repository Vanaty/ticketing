<%@page import="mg.itu.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.dao.*"%>
<%@page import="mg.itu.util.Utilitaire"%>
<%
List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
%>

<jsp:include page="../inc/header.jsp" />
<!-- Container fluid -->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Filtrer les reservations</h4>
                    <form action="<%= request.getContextPath()%>/reservation" method="get">
                        <!-- Dates -->
                        <div class="form-row row">
                            <div class="form-group col-md-6">
                                <label for="ureDateMin">Date min</label>
                                <input type="date" class="form-control" id="ureDateMin" name="datyMin">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="ureDateMax">Date max</label>
                                <input type="date" class="form-control" id="ureDateMax" name="datyMax">
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
                                    <th>Vol</th>
                                    <th>Places</th>
                                    <th>Places Annuler</th>
                                    <th>Prix</th>
                                    <th>Date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Reservation reservation: reservations){ %>
                                    <tr>
                                        <td><%= reservation.getId() %></td>
                                        <td><a href="<%= request.getContextPath() %>/vols/details?id=<%= reservation.getVol().getId() %>">VOL<%= reservation.getVol().getId() %></a></td>
                                        <td><%= reservation.getNbrPlaces() %></td>
                                        <td><%= reservation.getNbrPlacesAnnuller() %></td>
                                        <td><%= Utilitaire.formaterAr(reservation.getPrixTotal()) %> Ar</td>
                                        <td><%= reservation.getDaty() %></td>
                                        <td><%= reservation.getStatus() %></td>
                                        <td class="d-flex flex-column">
                                            <!-- Bouton Voir plus -->
                                            <a href="<%= request.getContextPath() %>/reservation/facture?id=<%= reservation.getId() %>" class="btn btn-outline-info btn-sm mb-2" role="button">
                                                Voir facture
                                            </a>
                                        
                                            <!-- Bouton Modifier -->
                                            <a href="<%= request.getContextPath() %>/reservation/update?id=<%= reservation.getId() %>" class="btn btn-outline-primary btn-sm mb-2" role="button">
                                                Modifier
                                            </a>
                                        
                                            <!-- Bouton -->
                                            <button class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#confirmModal<%= reservation.getId() %>">
                                                Annuler
                                            </button>

                                            <!-- Modal -->
                                            <div class="modal fade" id="confirmModal<%= reservation.getId() %>" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel<%= reservation.getId() %>" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmModalLabel<%= reservation.getId() %>">Confirmation</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Êtes-vous sûr de vouloir annuler cette réservation ?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                                                            <a href="<%= request.getContextPath() %>/reservation/annuler?id=<%= reservation.getId() %>" class="btn btn-danger">Confirmer</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
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
<!-- ============================================================== -->
<% 
// Affichage du message de succès ou d'erreur sous form de toast
if (request.getAttribute("message") != null) { 
    String message = (String) request.getAttribute("message");
    String messageType = "success"; // default to success
    try {
        messageType = message.split("/")[1]; // success or error
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div class="toast align-items-center text-bg-<%= "success".equals(messageType) ? "success" : "danger" %> border-0 show" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                <%= message %>
            </div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<% } %>
<jsp:include page="../inc/footer.jsp" />
