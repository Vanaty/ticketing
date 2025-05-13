<%@page import="mg.itu.entity.*"%>
<%@page import="mg.itu.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.exception.ValidatorException" %>
<%
ValidatorException ve = (ValidatorException)request.getAttribute("validation");
Vol vol = (Vol) request.getAttribute("vol");
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
        <div class="col-12 col-md-8 col-lg-6 mx-auto">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h4 class="card-title text-center mb-4">Formulaire de Programmation de Promotion</h4>
                    <!-- Formulaire pour programmer une promotion -->
                    <form action="<%= request.getContextPath()%>/promotion/config" method="post">
                        <input type="hidden" name="prom.idVol" value="<%= vol.getId() %>">
                        
                        <!-- Date de début -->
                        <div class="form-group">
                            <label for="departureDate">Date de début</label>
                            <input type="date" class="form-control" id="departureDate" name="prom.dateDebut" required>
                            <% if (!ve.getInputError("prom.dateDebut").isEmpty()) { %>
                            <div class="text-danger mt-2">
                                <i class="fas fa-exclamation-circle me-1"></i> Veuillez sélectionner une date valide.
                            </div>
                            <% } %>
                        </div>

                        <!-- Date de fin -->
                        <div class="form-group">
                            <label for="departureDate">Date de fin</label>
                            <input type="date" class="form-control" id="departureDate" name="prom.dateFin" required>
                            <% if (!ve.getInputError("prom.dateFin").isEmpty()) { %>
                            <div class="text-danger mt-2">
                                <i class="fas fa-exclamation-circle me-1"></i> Veuillez sélectionner une date valide.
                            </div>
                            <% } %>
                        </div>

                        <!-- Réduction -->
                        <div class="form-group">
                            <label for="discount">Réduction</label>
                            <input type="number" min="0" class="form-control" id="discount" name="prom.pourcentageReduction" required>
                            <% if (!ve.getInputError("prom.pourcentageReduction").isEmpty()) { %>
                            <div class="text-danger mt-2">
                                <i class="fas fa-exclamation-circle me-1"></i> Veuillez entrer un pourcentage valide.
                            </div>
                            <% } %>
                        </div>

                        <!-- Nombre de places -->
                        <div class="form-group">
                            <label for="seatNumber">Nombre de places</label>
                            <input type="number" min="0" class="form-control" id="seatNumber" name="prom.nbrSiege" required>
                            <% if (!ve.getInputError("prom.nbrSiege").isEmpty()) { %>
                            <div class="text-danger mt-2">
                                <i class="fas fa-exclamation-circle me-1"></i> Veuillez entrer un nombre valide de places.
                            </div>
                            <% } %>
                        </div>

                        <!-- Button to submit -->
                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary btn-lg">Valider</button>
                        </div>
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
<!-- End Container fluid  -->
<!-- ============================================================== -->
<jsp:include page="../inc/footer.jsp" />
