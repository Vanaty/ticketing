<%@page import="mg.itu.entity.*"%>
<%@page import="mg.itu.dao.*"%>
<%@page import="java.util.List"%>
<%@page import="mg.itu.exception.ValidatorException" %>
<%
ValidatorException ve = (ValidatorException)request.getAttribute("validation");
List<Avion> avions = (List<Avion>) request.getAttribute("avions");
List<VilleDesservie> villes = (List<VilleDesservie>) request.getAttribute("villes");

// Exemple de structure d'avions avec des types de sièges et des prix associés
// Vous pouvez récupérer cela dynamiquement depuis la base de données
Map<String, Double> seatPriceMap = new HashMap<>();
seatPriceMap.put("Economy", 100.0);
seatPriceMap.put("Business", 250.0);
seatPriceMap.put("FirstClass", 500.0);
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
                        <!-- Champs précédents ici -->
                        
                        <!-- Type de siège -->
                        <div class="form-group">
                            <label for="seatType">Type de siège</label>
                            <select class="form-control" id="seatType" name="vol.seatType" required>
                                <option value="Economy">Économie</option>
                                <option value="Business">Affaires</option>
                                <option value="FirstClass">Première classe</option>
                            </select>
                            <!-- Message d'erreur spécifique pour le type de siège -->
                            <% if (!ve.getInputError("vol.seatType").isEmpty()) { %>
                            <div class="text-danger" id="seatTypeError">
                                Veuillez sélectionner un type de siège.
                            </div>
                            <% } %>
                        </div>

                        <!-- Affichage du prix -->
                        <div class="form-group">
                            <label for="seatPrice">Prix</label>
                            <input type="text" class="form-control" id="seatPrice" name="vol.seatPrice" readonly>
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
</div>
<!-- ============================================================== -->
<!-- End Container fluid  -->
<!-- ============================================================== -->

<jsp:include page="../inc/footer.jsp" />

<script>
    // Ajouter un événement pour calculer le prix selon le type de siège
    document.getElementById('seatType').addEventListener('change', function() {
        var seatType = this.value;
        var seatPrice = 0;

        // Définir les prix selon le type de siège
        switch(seatType) {
            case 'Economy':
                seatPrice = 100.0;
                break;
            case 'Business':
                seatPrice = 250.0;
                break;
            case 'FirstClass':
                seatPrice = 500.0;
                break;
            default:
                seatPrice = 0;
        }

        // Afficher le prix dans le champ 'seatPrice'
        document.getElementById('seatPrice').value = seatPrice + " €";
    });
</script>
