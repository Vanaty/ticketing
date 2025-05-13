<%@page import="mg.itu.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.util.Utilitaire"%>
<%
    Reservation res = (Reservation) request.getAttribute("res");
%>
<jsp:include page="header.jsp" />
    <div class="container" style="max-width: 800px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 10px; background-color: #f9f9f9;">
        <header style="text-align: center; margin-bottom: 20px;">
            <h1 style="font-family: Arial, sans-serif; color: #333;">Facture de Réservation</h1>
        </header>
        <main>
            <p><strong>Numéro de Réservation:</strong> <%= res.getId() %></p>
            <p><strong>Date:</strong> <%= res.getDaty() %></p>
            <p><strong>Vol:</strong> <%= res.getVol().getVilleDepart().getVille() %> - <%= res.getVol().getVilleArrive().getVille() %></p>
            <p><strong>Nombre de Places:</strong> <%= res.getNbrPlaces() %></p>
            <p><strong>Prix Total:</strong> <%= Utilitaire.formaterAr(res.getPrixTotal())%> Ar</p>

            <h2>Détails de la Réservation</h2>
            <table border="1">
                <thead>
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
                            <td><%= Utilitaire.formaterAr(detail.getPrix()) %> Ar</td>
                        </tr>
                    <% 
                        } 
                    %>
                </tbody>
            </table>
        </main>
    </div>
<jsp:include page="footer.jsp" />