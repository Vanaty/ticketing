<%@page import="mg.itu.entity.*"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.util.Utilitaire"%>
<%
    Vol volPromotion = (Vol) request.getAttribute("volPromotion");
    Vol vol = (Vol) request.getAttribute("vol");
    List<PricingRule> pricingrules = (List<PricingRule>) request.getAttribute("pricingrules");
    List<TypeSiege> typeS = (List<TypeSiege>) request.getAttribute("typeS");
%>

<jsp:include page="header.jsp" />

<!-- Page de détails du vol -->
<div class="container mt-4 content">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Détails du Vol</h4>
            <hr />
            <% if (volPromotion.isPromotionActive(LocalDate.now())) { %>
                <div class="alert alert-success text-center mb-4" role="alert">En Promotion</div>
            <% } %>
            <!-- Affichage des informations du vol -->
            <table class="table">
                <tr>
                    <th>ID</th>
                    <td><%= vol.getId() %></td>
                </tr>
                <tr>
                    <th>Avion</th>
                    <td><%= vol.getAvion().getModele() %></td>
                </tr>
                <tr>
                    <th>Ville de départ</th>
                    <td><%= vol.getVilleDepart().getVille() %></td>
                </tr>
                <tr>
                    <th>Ville d'arrivée</th>
                    <td><%= vol.getVilleArrive().getVille() %></td>
                </tr>
                <tr>
                    <th>Date de départ</th>
                    <td><%= vol.getDtDepart() %></td>
                </tr>
                <tr>
                    <th>Heure de départ</th>
                    <td><%= vol.getHeureDepart() %></td>
                </tr>
                <tr>
                    <th>Durée</th>
                    <td><%= vol.getDuree() %></td>
                </tr>
                <% for(PrixVol pv: vol.getPrixVols()) { %>
                    <tr>
                        <th><%= pv.getTypeSiege().getLibelle() %></th>
                        <td>
                            <%=  Utilitaire.formaterAr(pv.getPrix()) %>Ar
                            <% if (volPromotion.isPromotionActive(LocalDate.now())) { %>
                                <br />
                                <span class="text-success">
                                    Prix après promotion : <%= Utilitaire.formaterAr(pv.getPrixApresPromotion(volPromotion.getPromotionActive(LocalDate.now()))) %> Ar
                                </span>
                            <% } %>
                        </td>
                    </tr>
                <% } %>
            </table>
            
            <hr />
            <!-- Lien Modifier -->
            <form action="<%= request.getContextPath()%>/reservation/creation" method="post">
                <input type="hidden" name="res.idVol" value="<%= vol.getId() %>">
                
                <!-- Modal for error message -->
                <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
                    <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title text-danger" id="errorModalLabel">Erreur</h5>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <%= errorMessage %>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        document.addEventListener("DOMContentLoaded", function() {
                            var errorModal = document.getElementById('errorModal');
                            if (errorModal) {
                                var modal = new bootstrap.Modal(errorModal);
                                modal.show();
                            }
                        });
                    </script>
                <% } %>
                
                <!-- Dates -->
                <h5>Reservation</h5>
                <div class="form-row row">
                    <div class="form-group col-md-6">
                        <label class="form-label font-weight-bold" for="departureDateMin">Date</label>
                        <input type="datetime-local" class="form-control border-primary" id="departureDateMin" name="res.daty">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="form-label font-weight-bold" for="departureDateMin">Classe</label>
                        <select name="idTypeSiege" class="form-control border-primary" id="">
                            <% for(TypeSiege a: typeS) { %>
                            <option value="<%=a.getId()%>"><%=a.getLibelle()%></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <hr class="my-4" />
                <h5 class="text-primary">Personnes</h5>
                <div class="form-row row">
                    <% for(PricingRule a: pricingrules) { %>
                        <div class="form-group col-md-6">
                            <label class="form-label font-weight-bold" for=""><%= a.getLibelle() %> 
                                <span class="badge badge-success">(<%= a.getDiscountPercentage() %>)</span>
                            </label>
                            <input type="hidden" name="idPRs[]" value="<%= a.getId() %>">
                            <input type="number" min="0" class="form-control border-primary" id="" placeholder="Nbr des personnes" name="nbrs[]">
                        </div>
                    <% } %>
                </div>
                <!-- Boutons -->
                <div class="form-row row mt-4">
                    <div class="form-group col-md-6">
                        <button type="submit" class="btn btn-primary btn-block btn-lg">Réserver</button>
                    </div>
                    <div class="form-group col-md-6">
                        <button type="reset" class="btn btn-secondary btn-block btn-lg">Réinitialiser</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
