
# System OSM Restaurant  

Restaurant Management System in Java made by using Apache Netbeans and Xampp.


## Authors

- [@oussamaelhay](https://github.com/oussamaelhay)


## FAQ

## Comment télécharger ce projet sur mon ordinateur ?

Pour télécharger et configurer le projet Java sur votre ordinateur, suivez les étapes ci-dessous :

### 1. Télécharger le projet
1. ** Cloner le dépôt (si vous utilisez Git) **:
   - Ouvrez votre terminal ou invite de commande.
   - Naviguez jusqu’au dossier où vous souhaitez cloner le projet.
   - Exécutez la commande suivante :
     ```sh
     git clone https://github.com/oussamaelhay/System-OSM-resturant
     ```

2. **Télécharger le projet en tant que fichier ZIP :**:
   - Rendez-vous sur le site hébergeant le dépôt (par exemple GitHub, GitLab).
   - Accédez au dépôt du projet.
   - Cliquez sur le bouton `Download ZIP` .
   - Extrayez le fichier ZIP téléchargé à l’emplacement souhaité.

### 2. Configurer XAMPP
1. **Démarrer XAMPP**:
   - Ouvrez le panneau de contrôle de XAMPP.
   - Lancez les services Apache et MySQL.

2. **Importer la base de données**:
   - Ouvrez phpMyAdmin en allant sur `http://localhost/phpmyadmin` dans votre navigateur.
   - Créez une nouvelle base de données (ex., `mydatabase`).
   - Importez le fichier SQL ( `rms.sql`)  dans la base nouvellement créée
     - Cliquez sur le nom de la base dans phpMyAdmin.
     - Allez dans l’onglet `Import` .
     - Choisissez le fichier SQL puis cliquez sur `Go`.

### 3. Ouvrir le projet dans NetBeans
1. **Lancer NetBeans**.

2. **Ouvrir le projet**:
   - Allez dans `File` > `Open Project`.
   - Naviguez jusqu’au dossier où vous avez extrait le projet.
   - Sélectionnez le dossier du projet et ouvrez-le.

### 4. Configurer le projet dans NetBeans
1. **Ajouter la bibliothèque MySQL Connector/J Library**:
   - Faites un clic droit sur le projet dans le panneau `Projects`.
   - Sélectionnez  `Properties`.
   - Allez dans l’onglet `Libraries`.
   - Cliquez sur `Add JAR/Folder`.
   - Naviguez jusqu’au dossier où vous avez le fichier JAR de MySQL Connector/J si vous ne l’avez pas, téléchargez-le sur le [MySQL website](https://dev.mysql.com/downloads/connector/j/)).
   - Ajoutez le fichier JAR.

2. **Mettre à jour les paramètres de connexion à la base de données**:
   - Ouvrez la classe Java qui gère la connexion à la base de données (ex., `AdminPage.java`).
   - Assurez-vous que l’URL de la base, le nom d’utilisateur et le mot de passe correspondent à votre configuration XAMPP. Exemple :
     ```java
     String url = "jdbc:mysql://localhost:3306/rms";
     String user = "root"; // default username
     String password = ""; // default password for XAMPP
     ```

### 5. Exécuter le projet
1. ** Lancer le projet **:
   - Faites un clic droit sur le projet dans le panneau  `Projects`.
   - Sélectionnez `Run`.

Cela devrait démarrer votre application Java et la connecter à la base MySQL via XAMPP.

Si vous rencontrez des problèmes, vérifiez les points suivants :
- Assurez-vous que les services Apache et MySQL de XAMPP sont bien lancés.
- Vérifiez les paramètres de connexion dans le code Java (URL, nom d’utilisateur, mot de passe)..
- Assurez-vous que la bibliothèque MySQL Connector/J est bien ajoutée au projet.

En suivant ces étapes, vous pourrez télécharger, configurer et exécuter ce projet Java sur votre ordinateur.


## Retours

Si vous avez des retours, veuillez me contacter à l’adresse suivante : eloussamahayboubi@gmail.com


## Support

Pour toute demande de support, vous pouvez m’écrire à eloussamahayboubi@gmail.com ou me suivre sur [Instagram](https://www.instagram.com/osmanelhb).


