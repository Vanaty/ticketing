<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #667eea, #764ba2);
        }
        .login-container {
            background: #fff;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 1rem;
            color: #333;
        }
        .input-group {
            margin-bottom: 1rem;
            text-align: left;
        }
        .input-group label {
            font-size: 0.9rem;
            color: #555;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
        }
        .login-btn {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background: #667eea;
            color: white;
            font-size: 1rem;
            cursor: pointer;
            transition: 0.3s;
        }
        .login-btn:hover {
            background: #5648a2;
        }
        .links {
            margin-top: 1rem;
            font-size: 0.9rem;
        }
        .links a {
            color: #667eea;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Connexion</h2>
        <form action="<%=request.getContextPath()%>/auth/login" method="post">
            <div class="input-group">
                <label for="email">Utilisateur</label>
                <input type="text" id="email" name="u.nom" placeholder="Entrez votre nom" required>
            </div>
            <div class="input-group">
                <label for="password">Mot de passe</label>
                <input type="password" id="password" name="u.password" placeholder="Entrez votre mot de passe" required>
            </div>
            <button type="submit" class="login-btn">Se connecter</button>
        </form>
        <div class="links">
            <a href="#">Mot de passe oublié ?</a> | <a href="#">Créer un compte</a>
        </div>
    </div>
</body>
</html>
