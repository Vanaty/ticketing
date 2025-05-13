<%@page import="mg.itu.entity.*"%>
<%@page import="java.time.LocalDate"%>
<%@page import="mg.itu.util.Utilitaire"%>
<%
    Vol volPromotion = (Vol) request.getAttribute("volPromotion");
    Vol vol = (Vol) request.getAttribute("vol");
%>

<jsp:include page="../inc/header.jsp" />

<!-- Page de détails du vol -->
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
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

                    <!-- Lien Modifier -->
                    <a href="<%= request.getContextPath() %>/vols/update?id=<%= vol.getId() %>" class="btn btn-outline-primary btn-sm mb-2" role="button">
                        Modifier
                    </a>

                    <!-- Lien Mettre en Promotion -->
                    <a href="<%= request.getContextPath() %>/promotion/config?id=<%= vol.getId() %>" class="btn btn-outline-success btn-sm mb-2" role="button">
                        Mettre en Promotion
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../inc/footer.jsp" />
