<%@page import="mg.itu.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.util.Utilitaire"%>
<%
    Reservation res = (Reservation) request.getAttribute("res");
%>
<jsp:include page="header.jsp" />
    <div class="container my-5" id="facture-content">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center">
                <h2 class="mb-0">Facture de Réservation</h2>
            </div>
            <div class="card-body">
                <div class="row mb-4">
                    <div class="col-md-6">
                        <h5 class="text-muted">Informations de la réservation</h5>
                        <div><strong>Numéro de Réservation:</strong> <%= res.getId() %></div>
                        <div><strong>Date:</strong> <%= res.getDaty() %></div>
                        <div><strong>Vol:</strong> <%= res.getVol().getVilleDepart().getVille() %> - <%= res.getVol().getVilleArrive().getVille() %></div>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <h5 class="text-muted">Récapitulatif</h5>
                        <div><strong>Nombre de Places:</strong> <%= res.getNbrPlaces() %></div>
                        <div><strong>Prix Total:</strong> <span class="text-primary fw-bold"><%= Utilitaire.formaterAr(res.getPrixTotal())%> Ar</span></div>
                    </div>
                </div>

                <h4 class="mt-4 mb-3 border-bottom pb-2">Détails de la Réservation</h4>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>Type de Siège</th>
                                <th>Règle de Tarification</th>
                                <th>Nombre de Personnes</th>
                                <th>Prix Normal</th>
                                <th>Pourcentage de Réduction</th>
                                <th>Prix Final</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for (ReservationDetail detail : res.getDetails()) { 
                            %>
                                <tr>
                                    <td><%= detail.getTypeSiege().getLibelle() %></td>
                                    <td><%= detail.getPricingRule().getLibelle() %></td>
                                    <td><%= detail.getNbrPersonnes() %></td>
                                    <td><%= Utilitaire.formaterAr(detail.getPrixNormale()) %> Ar</td>
                                    <td><%= detail.getPourcRed() %> %</td>
                                    <td class="fw-bold"><%= Utilitaire.formaterAr(detail.getPrix()) %> Ar</td>
                                </tr>
                            <% 
                                } 
                            %>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="5" class="text-end fw-bold">Total</td>
                                <td class="fw-bold text-primary"><%= Utilitaire.formaterAr(res.getPrixTotal())%> Ar</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="mt-4 text-center">
                    <p class="text-muted">Nous vous remercions pour votre réservation.</p>
                </div>
            </div>
            <div class="card-footer text-center">
                <button id="exportPdfBtn" class="btn btn-danger">
                    <i class="fas fa-file-pdf me-2"></i>Exporter en PDF
                </button>
                <a href="<%= request.getContextPath() %>/front" class="btn btn-secondary ms-2">
                    <i class="fas fa-arrow-left me-2"></i>Retour
                </a>
            </div>
        </div>
    </div>

    <script>
        document.getElementById('exportPdfBtn').addEventListener('click', function() {
            const btn = this;
            btn.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Génération en cours...`;
            btn.disabled = true;
            // Simuler une requête pour générer le PDF
            setTimeout(function() {
                btn.innerHTML = '<i class="fas fa-file-pdf me-2"></i>Exporter en PDF';
                btn.disabled = false;
                window.location.href = '<%= request.getContextPath() %>/reservation/pdf?id=<%= res.getId() %>';
            }, 2000);
        });
    </script>
<jsp:include page="footer.jsp" />