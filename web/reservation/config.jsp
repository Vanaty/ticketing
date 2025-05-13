<%@page import="mg.itu.exception.ValidatorException" %>
<%
    ValidatorException ve = (ValidatorException) request.getAttribute("validation");
    mg.itu.entity.Config configuration = (mg.itu.entity.Config) request.getAttribute("configuration");
%>

<jsp:include page="../inc/header.jsp" />

<!-- Container fluid -->
<div class="container-fluid">
    <!-- ============================================================== -->
    <!-- Start Page Content -->
    <!-- ============================================================== -->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Configuration de Réservation</h4>
                    <!-- Formulaire pour programmer un vol -->
                    <form action="<%= request.getContextPath()%>/reservation/config" method="post">
                        <% if(configuration.getId() != null) { %>
                            <input type="hidden" name="res.id" value="<%=configuration.getId()%>">
                        <% } %>
                        <!-- Section pour la configuration de la réservation -->
                        <div class="form-group">
                            <label for="reservationBeforeHours">Réservation (avant le vol, en heures)</label>
                            <input type="number" value="<%= configuration.getReservationBeforeHours() %>" min="0" class="form-control" id="reservationBeforeHours" name="res.reservationBeforeHours" required aria-describedby="reservationBeforeHoursHelp">
                            <!-- Message d'erreur spécifique pour la durée -->
                            <% if (!ve.getInputError("res.reservationBeforeHours").isEmpty()) { %>
                                <div class="text-danger" id="reservationBeforeHoursError">
                                    Veuillez entrer une durée valide pour la réservation.
                                </div>
                            <% } %>
                            <small id="reservationBeforeHoursHelp" class="form-text text-muted">Indiquez combien d'heures avant le vol la réservation peut être effectuée.</small>
                        </div>

                        <!-- Section pour la configuration de l'annulation -->
                        <div class="form-group">
                            <label for="cancellationBeforeHours">Annulation (avant le vol, en heures)</label>
                            <input type="number" value="<%= configuration.getCancellationBeforeHours() %>" min="0" class="form-control" id="cancellationBeforeHours" name="res.cancellationBeforeHours" required aria-describedby="cancellationBeforeHoursHelp">
                            <!-- Message d'erreur spécifique pour la durée -->
                            <% if (!ve.getInputError("res.cancellationBeforeHours").isEmpty()) { %>
                                <div class="text-danger" id="cancellationBeforeHoursError">
                                    Veuillez entrer une durée valide pour l'annulation.
                                </div>
                            <% } %>
                            <small id="cancellationBeforeHoursHelp" class="form-text text-muted">Indiquez combien d'heures avant le vol l'annulation peut être effectuée.</small>
                        </div>

                        <!-- Bouton de soumission -->
                        <button type="submit" class="btn btn-primary">Enregistrer</button>
                    </form>                    
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End Page Content -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Container fluid -->
<!-- ============================================================== -->

<jsp:include page="../inc/footer.jsp" />
